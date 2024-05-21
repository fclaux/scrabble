package scrabble.view.console;

import scrabble.gui.Console;
import scrabble.model.Player;
import scrabble.view.interfaces.PlayerView;

public class ConsolePlayerView implements PlayerView {

	@Override
	public void display(Player player) {
		Console.message("Nom = " + player.getName(), true);
		Console.message("Score = " + player.getScore(), true);
	}

}
