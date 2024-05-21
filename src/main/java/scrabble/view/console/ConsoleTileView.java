package scrabble.view.console;

import scrabble.gui.Console;
import scrabble.gui.ConsoleColor;
import scrabble.model.Tile;
import scrabble.view.interfaces.TileView;

public class ConsoleTileView implements TileView {
	
	@Override
	public void display(Tile tile) {
		display(tile, false);
		
	}

	public void display(Tile tile, boolean isGameBoardTile) {
		StringBuilder tileString = new StringBuilder();
		if (isGameBoardTile) {
            tileString.append(ConsoleColor.CYAN_BOLD);
        }
		
		tileString.append(formatTile(tile)); 
		
		if (isGameBoardTile) {
            tileString.append(ConsoleColor.RESET); 
        }
		
		Console.message(tileString.toString(), false);
	}
	
	private String formatTile(Tile tile) {
        String[] subscriptDigits = {"\u2080", "\u2081", "\u2082", "\u2083", "\u2084", "\u2085", "\u2086", "\u2087", "\u2088", "\u2089", "\u2081\u2080"};
        if (tile.isJoker()) {
            return String.format("%-3s", tile.letter() + subscriptDigits[0]);
        } else {
            return String.format("%-3s", tile.letter() + subscriptDigits[tile.letter().getValue()]);
        }
    }

	
}
