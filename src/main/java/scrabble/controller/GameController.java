package scrabble.controller;

import java.util.List;

import scrabble.model.BagOfTiles;
import scrabble.model.Cell;
import scrabble.model.Direction;
import scrabble.model.GameBoard;
import scrabble.model.Letters;
import scrabble.model.Move;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.util.EmptyBagException;
import scrabble.util.IndexOutOfBoardException;
import scrabble.util.ScoreCounter;

public class GameController {
	protected GameBoard gameBoard;
	protected BagOfTiles bagOfTiles;
	protected Player player;
	protected Rack rack;
    protected ScoreCounter scoreCounter;

    public GameController() {
        this.player = new Player("Joueur 1");
        this.bagOfTiles = new BagOfTiles();
        this.rack = new Rack();
        this.gameBoard = new GameBoard();
        this.scoreCounter = new ScoreCounter();
    }

	 protected boolean isAdjacentToExistingWord(int row, int col) {
	        try {
	            return (!gameBoard.cell(row - 1, col).isEmpty() || !gameBoard.cell(row + 1, col).isEmpty() ||
	                    !gameBoard.cell(row, col - 1).isEmpty() || !gameBoard.cell(row, col + 1).isEmpty());
	        } catch (IndexOutOfBoardException e) {
	            return false;
	        }
	    }

	    protected void shuffleBagOfTiles() {
	        this.bagOfTiles.shuffle();
	    }
	    
	    protected boolean isFirstMove() {
	        for (int i = 1; i <= GameBoard.SIZE_GRID; i++) {
	            for (int j = 1; j <= GameBoard.SIZE_GRID; j++) {
	                try {
	                    if (!gameBoard.cell(i, j).isEmpty()) {
	                        return false;
	                    }
	                } catch (IndexOutOfBoardException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return true;
	    }

	    protected void fillPlayerRack(Player player) {
	        Rack playerRack = player.rack();
	        try {
	            while (!playerRack.isFull() && bagOfTiles.drawTile() != null) {
	                Tile tile = bagOfTiles.drawTile();
	                if (tile != null) {
	                    playerRack.addTile(tile);
	                }
	            }
	        } catch (EmptyBagException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public int jumpOverATile(int nbLetters) {
	         return nbLetters += 1;
	    }
	    
	    protected void placeATile(List<Move> moves, Direction direction, int row, int col, int nbLetters, Tile tile) throws IndexOutOfBoardException {
	    	if (direction == Direction.HORIZONTAL) {
	            Cell cell = gameBoard.cell(row, col + nbLetters);
	            
	            while(!cell.isEmpty()) {
	                nbLetters = jumpOverATile(nbLetters);
	                cell = gameBoard.cell(row + nbLetters, col);
	            }       
	            moves.add(new Move(row, col + nbLetters, tile));
	        } else {
	            Cell cell = gameBoard.cell(row + nbLetters, col);
	            
	            while(!cell.isEmpty()) {
	                nbLetters = jumpOverATile(nbLetters);
	                cell = gameBoard.cell(row + nbLetters, col);
	            }
	            moves.add(new Move(row + nbLetters, col, tile));
	        }
	    }
	    
	    protected void returnTilesToRack(List<Move> playerMoves) {
	        for (Move move : playerMoves) {
	            Tile tile = move.tile();
	            if (tile.isJoker()) {
	                tile.letter(Letters.JOKER);
	            }
	            this.player.addTileInRack(tile);
	        }
	    }
	    
	    protected void addTilesToBoard(List<Move> playerMoves) {
	        for (Move move : playerMoves) {
	            Tile tile = move.tile();
	            int row = move.row();
	            int col = move.col();
	            this.gameBoard.addTile(tile, row, col);
	        }
	    }
}
