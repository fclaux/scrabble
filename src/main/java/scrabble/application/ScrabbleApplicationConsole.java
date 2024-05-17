package scrabble.application;

import java.util.ArrayList;
import java.util.List;

import scrabble.controller.GameMaster;
import scrabble.gui.Console;
import scrabble.model.GameBoard;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.util.IndexOutOfBoardException;
import scrabble.util.IndexOutOfRackException;

public class ScrabbleApplicationConsole {
	private static final int STOP_VALUE = 0;
	
	public static void main(String[] args) {
		GameMaster gameMaster = new GameMaster();
		GameBoard gameBoard = gameMaster.gameBoard(); 
		Player player = gameMaster.player();
        //Rack rack = player.getRack();

		
		
		gameMaster.start();
		gameBoard.display();
		
		//rack.display();
		//gameMaster.playerExchangeTiles(playerChooseTileForChange());
		
		placeTiles(gameMaster,player);
		gameBoard.display();
	}
	
	
	
	private static List<Integer> playerChooseTileForChange() {
		
		Console.title("Pour remplacez des éléments, entrez les indices un à un puis écrivez "+ STOP_VALUE +" pour arrêter");
		int input;
		List<Integer> indices = new ArrayList<>();
		do {
			input = Console.askInt(STOP_VALUE, Rack.MAX_TILES);
			if(input != STOP_VALUE) {
				if (indices.contains(input-1)) {
					Console.message("ERREUR, veuillez rentrer un nombre différents de ceux déja entrés  : ");
				}
				else {
					indices.add(input-1);
				}
			}
			
		} while (input != STOP_VALUE);
		return indices;
	}
	
	private static void placeTiles(GameMaster gameMaster, Player player){
        boolean placing = true;
        Rack rack = player.getRack();

        while (placing) {
            Console.message("Votre chevalet: ");
            rack.display();


            String input = Console.askString("Entrez l'indice de la lettre dans votre chevalet et ses coordonnées (ligne, colonne) séparés par des espaces (ex: 1 5 7) ou 'v' pour valider le placement :");
            if (input.equalsIgnoreCase("v")) {
                if (gameMaster.validatePlacement()) {
                    Console.message("Placement validé!");
                    placing = false;
                } else {
                    Console.message("Placement invalide. Réessayez. 1");
                }

            } else {
                try {
                    String[] parts = input.split(" ");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Entrée invalide. Format attendu: <indice> <ligne> <colonne>");
                    }
                    int rackIndex = Integer.parseInt(parts[0]) - 1;
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);
                    gameMaster.putLetterFromRackToGameBoard(rack, rackIndex, row, col);
                } catch (IndexOutOfRackException e) {
                    Console.message(e.getMessage());
                } catch (IndexOutOfBoardException e) {
                    Console.message("Entrée invalide. Réessayez. 2");
                } 
            }
        }
    
    }

}
