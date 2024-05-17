package scrabble.controller;


import java.util.List;

import scrabble.model.BagOfTiles;
import scrabble.model.GameBoard;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.util.IndexOutOfBoardException;
import scrabble.util.IndexOutOfRackException;

public class GameMaster {
	
	private GameBoard gameBoard;
	private BagOfTiles bagOfTiles;
	private Player player;

	
	public GameMaster() {
		this.gameBoard = new GameBoard();
		this.bagOfTiles = new BagOfTiles();
		this.player = new Player("Joueur1");

	}
	
	public Tile putLetterFromRackToGameBoard(Rack rack, int rackIndex, int row, int column) throws IndexOutOfRackException,IndexOutOfBoardException {
		
		if(gameBoard.getCell(row, column).isEmpty()) {
			Tile tileToPut = rack.removeTile(rackIndex);
	        if (tileToPut != null) {
	            gameBoard().addTile(tileToPut, row, column);
	        } else {
	        	throw new IndexOutOfRackException("Invalid Index for the rack");
	        }
	        return tileToPut;
		}
		return null;
        
    }
	
	public boolean validatePlacement() {
		return true;
	}

	

	public GameBoard gameBoard() {
		return this.gameBoard;
	}
	
	public BagOfTiles bagOfTiles() {
		return this.bagOfTiles;
	}
	
	public Player player() {
		return this.player;
	}
	
	public void start() {
		this.bagOfTiles.shuffle();
		this.player.draw(bagOfTiles);
	}
	
	public void playerExchangeTiles(List<Integer> indices) {
		this.player.exchangeTiles(this.bagOfTiles, indices);
	}

	

	
	

}

