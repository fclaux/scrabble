package scrabble.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.model.*;

public class ScrabbleApplicationConsole {
	
	public static final int STOP_VALUE = -1;
	
	public static void main(String[] args) {

		
		GameBoard gameBoard = new GameBoard();
		BagOfTiles bagOfTiles = new BagOfTiles();
		Player player1 = new Player("Dorian");
		Scanner scanner = new Scanner(System.in);
		
		Console.message(gameBoard.showGameBoard());
		
		bagOfTiles.shuffle();
		
		player1.draw(bagOfTiles);
				
		
		Console.message("Rack J1 :");
		Console.message(player1.getRack().display());
		
		List<Integer> indices = playerChooseTileForChange(scanner);
        
		player1.exchangeTiles(bagOfTiles, indices);
		
		Console.message("Rack J1 :");
		Console.message(player1.getRack().display());
		
	}

	private static List<Integer> playerChooseTileForChange(Scanner scanner) {
		Console.title("  Pour remplacez des éléments, entrez les indices un à un puis écrivez -1 pour arrêter");
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


}
