package scrabble.view.console;

import scrabble.gui.Console;
import scrabble.model.BagOfTiles;
import scrabble.model.Tile;

public class ConsoleBagOfTilesView{

	public void display(BagOfTiles bagOfTiles) {
		ConsoleTileView tileView = new ConsoleTileView();
		for (Tile tile : bagOfTiles.tiles()) {
            tileView.display(tile);
            Console.space();
        }
		Console.lineReturn();
	}
}
