package scrabble.controller;

import scrabble.model.BagOfTiles;
import scrabble.model.GameBoard;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Tile;

public class GameMaster {
	
	private GameBoard gameBoard;
	private BagOfTiles bagOfTiles;
	private Player player;
	
	public GameMaster() {
		this.gameBoard = new GameBoard();
		this.bagOfTiles = new BagOfTiles();
		this.player = new Player("Joueur1");
	}
	
	public Tile putLetterFromRackToGameBoard(Rack rack, int rackIndex, int ligne, int colonne) throws IndexOutOfBoundsException {
        Tile tileToPut = rack.removeTile(rackIndex);
        if (tileToPut != null) {
            gameBoard().addTile(tileToPut, ligne, colonne);
        } else {
        	throw new IndexOutOfBoundsException("Invalid "+ rackIndex + " Index");
        }
        return tileToPut;
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
	


	
	

}

