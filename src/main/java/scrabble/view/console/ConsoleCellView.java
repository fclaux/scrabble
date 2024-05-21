package scrabble.view.console;

import scrabble.gui.Console;
import scrabble.gui.ConsoleColor;
import scrabble.model.Cell;
import scrabble.view.interfaces.CellView;

public class ConsoleCellView implements CellView {

	@Override
	public void display(Cell cell) {
		if (!cell.isEmpty()) {
        	ConsoleTileView tileView = new ConsoleTileView();
        	tileView.display(cell.getTile(), true);
        }
        else {
            String backgroundColor;
            switch (cell.getEffect()) {
                case STARS:
                	backgroundColor = ConsoleColor.YELLOW_BACKGROUND;
                    break;
                case TRIPLE_LETTER:
                	backgroundColor = ConsoleColor.RED_BACKGROUND;
                    break;
                case DOUBLE_LETTER:
                	backgroundColor = ConsoleColor.GREEN_BACKGROUND;
                    break;
                case DOUBLE_WORD:
                	backgroundColor = ConsoleColor.BLUE_BACKGROUND;
                    break;
                case TRIPLE_WORD:
                	backgroundColor = ConsoleColor.PURPLE_BACKGROUND;
                    break;
                case NONE:
                default:
                	backgroundColor = ConsoleColor.RESET;
                    break;
            }
            
            Console.message(backgroundColor +" " + cell.getEffect().getSymbol() + " "+ ConsoleColor.RESET, false);

	}
	}

}
