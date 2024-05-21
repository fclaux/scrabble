package scrabble.view.console;

import scrabble.gui.Console;
import scrabble.model.GameBoard;
import scrabble.model.Rack;

public class ConsoleMenuView {
	public void displayMenu(GameBoard gameBoard, Rack rack) {
		
		ConsoleRackView rackView = new ConsoleRackView();
		ConsoleGameBoardView gameBoardView = new ConsoleGameBoardView();
		
		gameBoardView.display(gameBoard);
		
		Console.message("Voici votre rack : ", true);
		rackView.display(rack);
        Console.title("Que souhaitez vous faire ?");
        Console.message("1. Jouer", true);
        Console.message("2. Échanger des jetons", true);
        Console.message("3. Quitter", true);
        Console.message("Choisissez une option : ", false);
    }
}
