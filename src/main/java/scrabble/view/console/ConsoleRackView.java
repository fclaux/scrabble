package scrabble.view.console;

import scrabble.gui.Console;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.view.interfaces.RackView;

public class ConsoleRackView implements RackView {

	@Override
	public void display(Rack rack) {
		ConsoleTileView tileView = new ConsoleTileView();
		for (Tile tile : rack.getTiles()) {
            tileView.display(tile);
            Console.space();
        }
		Console.lineReturn();
	}
}
