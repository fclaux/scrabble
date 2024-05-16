package scrabble.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.controller.GameMaster;
import scrabble.gui.Console;
import scrabble.model.BagOfTiles;
import scrabble.model.GameBoard;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.util.IndexOutOfRackException;

public class ScrabbleTest {
	
	public static final int STOP_VALUE = -1;
	
	public static void main(String[] args) throws IndexOutOfRackException {
		Console.separator();
		Console.message("|   Bienvenue dans notre magnifique jeu de scrabble !   |");
		Console.message("|   développé par Maxence                               |");
		Console.message("|   et par Dorian                                       |");
		Console.message("|   et par Florian                                      |");
		Console.separator();
		
		GameBoard gameBoard = new GameBoard();
		BagOfTiles bagOfTiles = new BagOfTiles();
		Player player1 = new Player("Dorian");
		Scanner scanner = new Scanner(System.in);
		GameMaster gameMaster = new GameMaster();

		
		Console.title("  La grille");
		Console.message(gameBoard.showGameBoard());
		
		Console.title("  On initialise le sac de jeton et on le mélange");
		showRemainingTilesInBag(bagOfTiles);
		Console.message(bagOfTiles.display());
		Console.message("On mélange...");
		bagOfTiles.shuffle();
		Console.message(bagOfTiles.display());
		
		
		Console.message("Le joueur pioche");
		player1.draw(bagOfTiles);
		
		showRemainingTilesInBag(bagOfTiles);
		
		
		Console.message("Rack J1 :");
		Console.message(player1.getRack().display());
		
		List<Integer> indices = playerChooseTileForChange(scanner);
        
		player1.exchangeTiles(bagOfTiles, indices);
		showRemainingTilesInBag(bagOfTiles);
		
		Console.message("Rack J1 :");
		Console.message(player1.getRack().display());
		
		gameMaster.putLetterFromRackToGameBoard(player1.getRack(), 1, 8, 8);
		Console.message(gameMaster.getGameBoard().showGameBoard());
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

	private static void showRemainingTilesInBag(BagOfTiles bagOfTiles) {
		Console.message("Il reste "+bagOfTiles.getRemainingTilesCount()+" jetons dans la pioche !");
	}
	


}
