package scrabble.view.console;

import scrabble.gui.Console;
import scrabble.model.Rack;
import scrabble.model.Tile;

public class ConsoleRackView{

	public void display(Rack rack) {
		ConsoleTileView tileView = new ConsoleTileView();
		for (Tile tile : rack.tiles()) {
            tileView.display(tile);
            Console.space();
        }
		Console.lineReturn();
	}
}
