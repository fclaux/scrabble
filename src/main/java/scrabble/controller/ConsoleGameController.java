package scrabble.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.model.*;
import scrabble.util.IndexOutOfBoardException;
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
    
    private static final int STOP_VALUE = 0;

    public ConsoleGameController() {
        this.player = new Player("Joueur 1");
        this.gameBoard = new GameBoard();
        this.bagOfTiles = new BagOfTiles();
        this.menuView = new ConsoleMenuView();
        this.gameBoardView = new ConsoleGameBoardView();
        this.rackView = new ConsoleRackView();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
    	Player player1 = this.player;
    	shuffleBagOfTiles();
    	fillPlayerRack(player1);
        boolean running = true;
        while (running) {
            menuView.displayMenu(this.gameBoard, player1.getRack());
            int choice = getInput();
            switch (choice) {
                case 1:
                    play();
                    fillPlayerRack(player1);
                    break;
                case 2:
                    exchangeTiles(player1);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    Console.message("Choix invalide. Veuillez réessayer.", true);
            }
        }
        Console.message("Merci d'avoir joué !",true);
    }

    private int getInput() {
        while (!scanner.hasNextInt()) {
            Console.message("Veuillez entrer un nombre valide.", true);
            scanner.next(); 
        }
        return scanner.nextInt();
    }

    private void play() {
 	
        gameBoardView.display(gameBoard);
        
        List<Move> playerMoves = getPlayerMoves();
        
        applyMoves(playerMoves);
        
    }
    
    private void applyMoves(List<Move> playerMoves) {
        if (isMovesValid(playerMoves)) {
        	Console.message("Votre coup est valide ! ", true);
            addTilesToBoard(playerMoves);
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
            this.player.addTileInRack(tile);
        }
    }
    
    private List<Move> getPlayerMoves() {
    	
        int indice; 
        int row;
        int col;
        List<Move> moves = new ArrayList<>();
        
        do {
        	Rack rack = this.player.getRack();
			Console.title("Pour placer des tuiles, entrez les indices un à un avec les positions puis écrivez "+ STOP_VALUE +" pour arrêter");
			this.rackView.display(rack);
			Console.message("Veuillez entrer l'indice de la tuile : ",false);
			indice = Console.askInt(STOP_VALUE, rack.size());
			if(indice != STOP_VALUE) {
				Tile tile = rack.removeTile(indice-1);
				Console.message("Veuillez entrer la ligne : ",false);
				row = Console.askInt(1, GameBoard.SIZE_GRID);
				Console.message("Veuillez entrer la colonne : ",false);
				col = Console.askInt(1, GameBoard.SIZE_GRID);
				
				if (tile.isJoker()) {
					char jokerLetter = Console.askJokerLetter();
                    tile.setLetter(Letters.valueOf(String.valueOf(jokerLetter)));
				}
				
				moves.add(new Move(row, col, tile));
			}
			
		} while (indice != STOP_VALUE);
        
        return moves;
    }
    
    private boolean isMovesValid(List<Move> playerMoves) {
        boolean tileOnStars = false;
        boolean haveNearTile = false;
        boolean tileOnOccupiedCell = false;
        boolean allInSameRow = true;
        boolean allInSameCol = true;

        if (playerMoves.size()<2) {
        	Console.message("vous devez jouer au moins 2 lettres",true);
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
                	Console.message("Une cellulue ("+row+" , "+col+") est déja occupée", true);
                    tileOnOccupiedCell = true;
                }    

                if (row > 1) {
                    if (!this.gameBoard.getCell(row - 1, col).isEmpty()) {
                        Console.message("Au moins une tuile est connectée", true);
                        haveNearTile = true;
                    }
                }

                if (row < GameBoard.SIZE_GRID - 1) {
                    if (!this.gameBoard.getCell(row + 1, col).isEmpty()) {
                        Console.message("Au moins une tuile est connectée", true);
                        haveNearTile = true;
                    }
                }

                if (col > 1) {
                    if (!this.gameBoard.getCell(row, col - 1).isEmpty()) {
                        Console.message("Au moins une tuile est connectée", true);
                        haveNearTile = true;
                    }
                }

                if (col < GameBoard.SIZE_GRID - 1) {
                    if (!this.gameBoard.getCell(row, col + 1).isEmpty()) {
                        Console.message("Au moins une tuile est connectée", true);
                        haveNearTile = true;
                    }
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
    	int input;
		List<Integer> indices = new ArrayList<>();
		Console.title("Pour remplacez des éléments, entrez les indices un à un puis écrivez "+ STOP_VALUE +" pour arrêter");
		do {
			Console.message("Indice de la tuile à remplacer : ", false);
			input = Console.askInt(STOP_VALUE, Rack.MAX_TILES);
			if(input != STOP_VALUE) {
				if (indices.contains(input-1)) {
					Console.message("ERREUR, veuillez rentrer un nombre différents de ceux déja entrés. ", false);
				}
				else {
					indices.add(input-1);
				}
			}
			
		} while (input != STOP_VALUE);
		
		player.exchangeTiles(this.bagOfTiles, indices);
		
    }
    
    public int calculScoreForWord(List<Cell> cells) {
		int totalScore = 0;
        int wordMultiplier = 1;

        for (Cell cell : cells) {
            Tile tile = cell.getTile();
            int tileScore = tile.letter().getValue();

            switch (cell.getEffect()) {
                case DOUBLE_LETTER:
                    tileScore *= 2;
                    break;
                case TRIPLE_LETTER:
                    tileScore *= 3;
                    break;
                case DOUBLE_WORD:
                    wordMultiplier *= 2;
                    break;
                case TRIPLE_WORD:
                    wordMultiplier *= 3;
                    break;
                default:
                    break;
            }

            totalScore += tileScore;
        }

        return totalScore * wordMultiplier;
    }
}
