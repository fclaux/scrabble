package scrabble.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.model.*;
import scrabble.util.IndexOutOfBoardException;
import scrabble.util.ScoreCounter;
import scrabble.view.console.ConsoleGameBoardView;
import scrabble.view.console.ConsoleMenuView;
import scrabble.view.console.ConsoleRackView;
import scrabble.view.interfaces.GameBoardView;
import scrabble.view.interfaces.RackView;

public class ConsoleGameController {
    private Player player;
    private GameBoard gameBoard;
    private BagOfTiles bagOfTiles;
    private ConsoleMenuView menuView;
    private GameBoardView gameBoardView;
    private RackView rackView;
    private Scanner scanner;
    private boolean firstExchange = true;

    private static final int STOP_VALUE = 0;

    public ConsoleGameController() {
        this.player = new Player("Joueur 1");
        this.gameBoard = new GameBoard();
        this.bagOfTiles = new BagOfTiles();
        this.menuView = new ConsoleMenuView();
        this.gameBoardView = new ConsoleGameBoardView();
        this.rackView = new ConsoleRackView();
        this.scanner = new Scanner(System.in);
        new ScoreCounter();
    }

    public void startGame() throws IndexOutOfBoardException {
        Player player1 = this.player;

        shuffleBagOfTiles();
        fillPlayerRack(player1);
        boolean running = true;
        while (running && !player1.getRack().isEmpty()) {
            menuView.displayMenu(this.gameBoard, player1.getRack());
            int choice = getInput();
            switch (choice) {
                case 1:
                    play();
                    
                    fillPlayerRack(player1);
                    break;
                case 2:
                    if (this.firstExchange) {
                        this.firstExchange = false;
                        exchangeTiles(player1);
                    } else {
                        Console.message("Vous avez déjà échangé des tuiles ce tour, choisissez une autre option", true);
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    Console.message("Choix invalide. Veuillez réessayer.", true);
            }
        }
        Console.message("Merci d'avoir joué !", true);
    }

    private int getInput() {
        while (!scanner.hasNextInt()) {
            Console.message("Veuillez entrer un nombre valide.", true);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void play() throws IndexOutOfBoardException {
        gameBoardView.display(gameBoard);

        List<Move> playerMoves = getPlayerMoves();
        
        applyMoves(playerMoves);
    }

    private void applyMoves(List<Move> playerMoves) throws IndexOutOfBoardException {
        if (isMovesValid(playerMoves)) {
            Console.message("Votre coup est valide ! ", true);
            this.firstExchange = true;
            addTilesToBoard(playerMoves);
            int score = ScoreCounter.calculateScoreForMoves(playerMoves, gameBoard);
            player.addScore(score);
            Console.message("Score total :" + player.getScore(), true);
            Console.message("Score pour ce coup :" + ScoreCounter.calculateScoreForMoves(playerMoves, gameBoard),true);
        } else {
            Console.message("Votre coup n'est pas valide ! ", true);
            returnTilesToRack(playerMoves);
        }
    }


    private void addTilesToBoard(List<Move> playerMoves) {
        for (Move move : playerMoves) {
            Tile tile = move.getTile();
            int row = move.getRow();
            int col = move.getCol();
            this.gameBoard.addTile(tile, row, col);
        }
    }

    private void returnTilesToRack(List<Move> playerMoves) {
        for (Move move : playerMoves) {
            Tile tile = move.getTile();
            if (tile.isJoker()) {
                tile.setLetter(Letters.JOKER);
            }
            this.player.addTileInRack(tile);
        }
    }
    
   
    

    private List<Move> getPlayerMoves() throws IndexOutOfBoardException {
    	Direction direction = Direction.HORIZONTAL;
        int row;
        int col;
        int nbLetters = 0;
        boolean firstOccurence = true;
        Rack rack = this.player.getRack();
        List<Move> moves = new ArrayList<>();
    	Console.title("Tour du joueur " + player.getName());
    	Tile tile = answerTile(player, rack);	
		if(tile  !=  null) {
			Console.message("Veuillez entrer la ligne : ",false);
			row = Console.askInt(1, GameBoard.SIZE_GRID);
			Console.message("Veuillez entrer la colonne : ",false);
			col = Console.askInt(1, GameBoard.SIZE_GRID);

	
			moves.add(new Move(row, col, tile));
			nbLetters += 1;
			
			while(tile  != null && !rack.isEmpty()) {
				if (firstOccurence) {
					direction = Console.askDirection();
					firstOccurence = false;
				}
				tile = answerTile(player, rack);
				
				if(tile != null) {
					if (direction == Direction.HORIZONTAL) {
						Cell cell = gameBoard.getCell(row, col + nbLetters);
						
						if(!cell.isEmpty()) {
							nbLetters += 1;
						}
						moves.add(new Move(row, col + nbLetters, tile));

					}
					else {
						Cell cell = gameBoard.getCell(row + nbLetters, col);

						if(!cell.isEmpty()) {
							nbLetters += 1;
						}
						
						moves.add(new Move(row + nbLetters,col , tile));				
					}
							
					nbLetters += 1;
				}
			}
				
				
		}
        return moves;

    }

    

private Tile answerTile(Player player, Rack rack) {
	this.rackView.display(rack);
	Console.message("Veuillez entrer l'indice de la tuile : ",false);
	int indice = Console.askInt(STOP_VALUE, rack.size());
	
	if(indice != STOP_VALUE) {
		
		Tile tile = rack.removeTile(indice-1);
		
		
		if (tile.isJoker()) {
			char jokerLetter = Console.askJokerLetter();
			tile.setLetter(Letters.valueOf(String.valueOf(jokerLetter)));
			
		}
		return tile;
	}
	return null;
	
}

    private boolean isMovesValid(List<Move> playerMoves) {
        boolean tileOnStars = false;
        boolean haveNearTile = false;
        boolean tileOnOccupiedCell = false;
        boolean allInSameRow = true;
        boolean allInSameCol = true;

        if (playerMoves.size() < 2) {
            Console.message("vous devez jouer au moins 2 lettres", true);
            return false;
        }

        int firstRow = playerMoves.get(0).getRow();
        int firstCol = playerMoves.get(0).getCol();

        for (Move move : playerMoves) {
            int row = move.getRow();
            int col = move.getCol();

            if (row != firstRow) {
                allInSameRow = false;
            }
            if (col != firstCol) {
                allInSameCol = false;
            }

            try {
                if (col == GameBoard.MIDDLE_CASE && row == GameBoard.MIDDLE_CASE) {
                    tileOnStars = true;
                }

                if (!this.gameBoard.getCell(row, col).isEmpty()) {
                    Console.message("Une cellule (" + row + " , " + col + ") est déjà occupée", true);
                    tileOnOccupiedCell = true;
                }

                if (row > 1 && !this.gameBoard.getCell(row - 1, col).isEmpty() ||
                    row < GameBoard.SIZE_GRID && !this.gameBoard.getCell(row + 1, col).isEmpty() ||
                    col > 1 && !this.gameBoard.getCell(row, col - 1).isEmpty() ||
                    col < GameBoard.SIZE_GRID && !this.gameBoard.getCell(row, col + 1).isEmpty()) {
                    haveNearTile = true;
                }
            } catch (IndexOutOfBoardException e) {
                e.printStackTrace();
            }
        }

        if (isFirstMove() && !tileOnStars) {
            Console.message("C'est le premier tour et vous ne jouez pas sur l'étoile", true);
            return false;
        }

        if (tileOnOccupiedCell) {
            Console.message("Vous voulez placer une tuile sur un espace occupé", true);
            return false;
        }

        if (!isFirstMove() && !haveNearTile) {
            Console.message("C'est pas le premier tour et aucune tuile n'est connectée", true);
            return false;
        }

        if (!(allInSameRow || allInSameCol)) {
            Console.message("Les tuiles doivent être alignées dans le même sens, soit vers le bas, soit vers la droite", true);
            return false;
        }

        return true;
    }

    private boolean isFirstMove() {
        for (int i = 1; i <= GameBoard.SIZE_GRID; i++) {
            for (int j = 1; j <= GameBoard.SIZE_GRID; j++) {
                try {
                    if (!gameBoard.getCell(i, j).isEmpty()) {
                        return false;
                    }
                } catch (IndexOutOfBoardException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private void shuffleBagOfTiles() {
        this.bagOfTiles.shuffle();
    }

    private void fillPlayerRack(Player player) {
        player.fillRack(this.bagOfTiles);
    }

    private void exchangeTiles(Player player) {
    	Rack rack = player.getRack();
    	ConsoleRackView rackView = new ConsoleRackView();
        int input;
        List<Integer> indices = new ArrayList<>();
        Console.title("Pour remplacez des éléments, entrez les indices un à un puis écrivez " + STOP_VALUE + " pour arrêter");
        Console.message("Rack de " + player.getName() + " : ", false);
    	rackView.display(rack);
        do {
            Console.message("Indice de la tuile à remplacer : ", false);
            input = Console.askInt(STOP_VALUE, Rack.MAX_TILES);
            if (input != STOP_VALUE) {
                if (indices.contains(input - 1)) {
                    Console.message("ERREUR, veuillez rentrer un nombre différent de ceux déjà entrés. ", false);
                } else {
                    indices.add(input - 1);
                }
            }
        } while (input != STOP_VALUE);

        player.exchangeTiles(this.bagOfTiles, indices);
    }
    
}
