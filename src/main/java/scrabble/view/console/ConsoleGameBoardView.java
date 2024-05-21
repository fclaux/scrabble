package scrabble.view.console;

import scrabble.gui.Console;
import scrabble.model.Cell;
import scrabble.model.GameBoard;
import scrabble.util.IndexOutOfBoardException;
import scrabble.view.interfaces.GameBoardView;

public class ConsoleGameBoardView implements GameBoardView {

	@Override
	public void display(GameBoard gameBoard) {

		final String ROW_SEPARATOR = "   " + Console.SEPARATOR_LINE + "\n";
		
		Console.message("     ", false);
	    for (int i = 1; i <= GameBoard.SIZE_GRID; i++) {
	    	Console.message(String.format("%-4d", i), false);
	    }
	    Console.lineReturn();
        for (int i = 1; i <= GameBoard.SIZE_GRID; i++) {
        	Console.message(ROW_SEPARATOR, false);
        	Console.message(String.format("%2d ", i), false);
            for (int j = 1; j <= GameBoard.SIZE_GRID; j++) {
                Cell cell = null;
				try {
					cell = gameBoard.getCell(i,j);
				} catch (IndexOutOfBoardException e) {
					e.printStackTrace();
				}
                ConsoleCellView cellView = new ConsoleCellView();
                
                Console.message("|", false);
                cellView.display(cell);
            }
            Console.message("|", true);
        }
        Console.message(ROW_SEPARATOR, false);
	}

}
