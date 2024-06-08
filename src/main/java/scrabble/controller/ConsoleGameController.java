package scrabble.controller;

import java.util.ArrayList;
import java.util.List;

import scrabble.gui.Console;
import scrabble.model.Direction;
import scrabble.model.GameBoard;
import scrabble.model.Letters;
import scrabble.model.Move;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.util.IndexOutOfBoardException;
import scrabble.util.ScoreCounter;
import scrabble.view.console.ConsoleGameBoardView;
import scrabble.view.console.ConsoleMenuView;
import scrabble.view.console.ConsoleRackView;
import scrabble.view.interfaces.GameBoardView;
import scrabble.view.interfaces.RackView;

public class ConsoleGameController extends GameController {
    private ConsoleMenuView menuView;
    private GameBoardView gameBoardView;
    private RackView rackView;
    private boolean firstExchange = true;

    private static final int STOP_VALUE = 0;


    public ConsoleGameController() {
    	super();
        this.menuView = new ConsoleMenuView();
        this.gameBoardView = new ConsoleGameBoardView();
        this.rackView = new ConsoleRackView();
        this.scoreCounter = new ScoreCounter();
    }

    public void startGame() throws IndexOutOfBoardException {
        Player player1 = this.player;

        this.shuffleBagOfTiles();
        this.fillPlayerRack(player1);
        boolean running = true;
        while (running && !player1.rack().isEmpty()) {
            menuView.displayMenu(this.gameBoard, player1.rack());
            int choice = Console.askInt(1,3);
            switch (choice) {
                case 1:
                    play();
                    this.fillPlayerRack(player1);
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


    private void applyMoves(List<Move> playerMoves) throws IndexOutOfBoardException {
        if (isMovesValid(playerMoves)) {
            Console.message("Votre coup est valide ! ", true);
            this.firstExchange = true;
            this.addTilesToBoard(playerMoves);
            int score = scoreCounter.calculateScoreForMoves(playerMoves, gameBoard);
            player.addScore(score);
            Console.message("Score total :" + player.score(), true);
            Console.message("Score pour ce coup :" + score, true);
        } else {
            Console.message("Votre coup n'est pas valide ! ", true);
            this.returnTilesToRack(playerMoves);
        }
    }
    
    protected Direction askForDirection() {
        return Console.askDirection();

    }
    
    protected void placeAWord(Rack rack,Tile tile ,int nbLetters,List<Move> moves, int row, int col) throws IndexOutOfBoardException{
    	Direction direction = null;
        boolean firstOccurrence = true;
    	
    	
    	while (tile != null && !rack.isEmpty()) {
            if (firstOccurrence) {
            	direction = askForDirection();
            	placeATile(moves,direction,row,col,nbLetters,tile);
            	nbLetters = nbLetters + 1;
                firstOccurrence = false;
            }
            tile = answerTile(rack);

            if (tile != null) {
            	placeATile(moves,direction,row,col,nbLetters,tile);
            	nbLetters += 1;

            }
        }
    }


    private List<Move> getPlayerMoves() throws IndexOutOfBoardException {
        int row;
        int col;
        int nbLetters = 0;
        Rack rack = this.player.rack();
        List<Move> moves = new ArrayList<>();
        Console.title("Tour du joueur " + player.name());
        Tile tile = answerTile(rack);
        if (tile != null) {
            Console.message("Veuillez entrer la ligne : ", false);
            row = Console.askInt(1, GameBoard.SIZE_GRID);
            Console.message("Veuillez entrer la colonne : ", false);
            col = Console.askInt(1, GameBoard.SIZE_GRID);

            moves.add(new Move(row, col, tile));
            nbLetters += 1;
            
            tile = answerTile(rack);
            if (tile != null) {
       
            	placeAWord(rack,tile,nbLetters,moves,row,col);
                
            }
        }
        return moves;
    } 
    

    protected Tile answerTile(Rack rack) {
    	this.rackView.display(rack);
        Console.message("Veuillez entrer l'indice de la tuile ("+STOP_VALUE+" pour valider) : ", false);
		int indice = Console.askInt(STOP_VALUE, rack.size());
	
	    if (indice != STOP_VALUE) {
	        Tile tile = rack.removeTile(indice - 1);
	
	        if (tile.isJoker()) {
	            char jokerLetter = Console.askJokerLetter();
	            tile.letter(Letters.valueOf(String.valueOf(jokerLetter)));
	        }
	        return tile;
	    }
	    return null;
    
	}
    
    private boolean isMovesValid(List<Move> playerMoves) {
        boolean tileOnStars = false;
        boolean tileOnOccupiedCell = false;
        boolean allInSameRow = true;
        boolean allInSameCol = true;
        boolean haveNearTile = false;


        if (playerMoves.isEmpty()) {
            Console.message("vous devez jouer au moins 1 lettres si elle est adjacente à un mot déjà existant", true);
            return false;
        }

        
        int firstRow = playerMoves.get(0).row();
        int firstCol = playerMoves.get(0).col();

        for (Move move : playerMoves) {
            int row = move.row();
            int col = move.col();

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

                if (!this.gameBoard.cell(row, col).isEmpty()) {
                    Console.message("Une cellule (" + row + " , " + col + ") est déjà occupée", true);
                    tileOnOccupiedCell = true;
                }
                
                if (this.isAdjacentToExistingWord(row, col)) {
                        haveNearTile = true;
                    }


            } catch (IndexOutOfBoardException e) {
                e.printStackTrace();
            }
        }

        if (this.isFirstMove() && !tileOnStars) {
            Console.message("C'est le premier tour et vous ne jouez pas sur l'étoile", true);
            return false;
        }
        

        if (!this.isFirstMove() && !haveNearTile) {
            Console.message("C'est pas le premier tour et aucune tuile n'est connectée", true);
            return false;
        }

        
        if (this.isFirstMove() && playerMoves.size() == 1) {
        	Console.message("Vous devez placer au moin 2 tuiles lors du premier coup de la partie", true);
        	return false;
        }

        if (tileOnOccupiedCell) {
            Console.message("Vous voulez placer une tuile sur un espace occupé", true);
            return false;
        }

        if (!(allInSameRow || allInSameCol)) {
            Console.message("Les tuiles doivent être alignées dans le même sens, soit vers le bas, soit vers la droite", true);
            return false;
        }

        return true;
    }  

    private void exchangeTiles(Player player) {
        Rack rack = player.rack();
        Integer input;
        do {
            Console.message("Rack de " + player.name() + " : ", false);
            this.rackView.display(rack);
            Console.message("Indice de la tuile à remplacer (" + STOP_VALUE + ") pour arrêter : ", false);
            input = Console.askInt(STOP_VALUE, rack.size());
            if (input != STOP_VALUE) {
                Tile tile = rack.removeTile(input - 1);
                this.bagOfTiles.add(tile);
            }
        } while ((input != STOP_VALUE) && (!rack.isEmpty()));
        this.fillPlayerRack(player);
    }

    private void play() throws IndexOutOfBoardException {
        gameBoardView.display(gameBoard);

        List<Move> playerMoves = getPlayerMoves();
        
        applyMoves(playerMoves);
    }
    
   
    
    
}
