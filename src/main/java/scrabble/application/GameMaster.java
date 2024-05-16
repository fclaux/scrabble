package scrabble.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.model.BagOfTiles;
import scrabble.model.GameBoard;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.util.IndexOutOfRackException;

public class GameMaster {
	
	private static final int STOP_VALUE = -1;
	
	private GameBoard gameBoard;
	private BagOfTiles bagOfTiles;
	private Player player;
	
	public GameMaster() {
		this.gameBoard = new GameBoard();
		this.bagOfTiles = new BagOfTiles();
		this.player = new Player("Joueur1");
	}
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
				
		Console.message(getGameBoard().showGameBoard());
				
		this.bagOfTiles.shuffle();
				
		player.draw(bagOfTiles);
						
				
		Console.message("Rack J1 :");
		Console.message(this.player.getRack().display());
				
		List<Integer> indices = playerChooseTileForChange(scanner);
		        
		this.player.exchangeTiles(bagOfTiles, indices);
				
		Console.message("Rack J1 :");
		Console.message(this.player.getRack().display());
				
		
	}
	
	private static List<Integer> playerChooseTileForChange(Scanner scanner) {
		Console.title("  Pour remplacez des éléments, entrez les indices un à un puis écrivez "+ STOP_VALUE +" pour arrêter");
		int input;
		List<Integer> indices = new ArrayList<>();
		do {
			Console.message("Entrez la place de l'élément que vous voulez retirez : ");
			input = scanner.nextInt();
			
			if ((input < 1 || input > Rack.MAX_TILES)&&(input!=STOP_VALUE)) {
				Console.message("ERREUR, veuillez rentrer un nombre entre 1 et "+ Rack.MAX_TILES +" : ");
			}
			else if (indices.contains(input-1)) {
				Console.message("ERREUR, veuillez rentrer un nombre différents de ceux déja entrés  : ");
			}
			else {
				indices.add(input-1);
			}
		} while (input != STOP_VALUE);
		return indices;
	}
	
	public Tile putLetterFromRackToGameBoard(Rack rack, int rackIndex, int ligne, int colonne) throws IndexOutOfRackException {
        Tile tileToPut = rack.removeTile(rackIndex);
        if (tileToPut != null) {
            getGameBoard().addTile(tileToPut, ligne, colonne);
        } else {
        	throw new IndexOutOfRackException("Invalid "+ rackIndex + " Index");
        }
        return tileToPut;
    }

	public GameBoard getGameBoard() {
		return gameBoard;
	}
	
	

}

