package scrabble.view.console;

import scrabble.gui.Console;
import scrabble.model.Player;

public class ConsolePlayerView{

	public void display(Player player) {
		Console.message("Nom = " + player.name(), true);
		Console.message("Score = " + player.score(), true);
	}
}
