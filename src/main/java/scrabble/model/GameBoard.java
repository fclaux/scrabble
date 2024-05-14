package scrabble.model;

import scrabble.gui.Console;
import scrabble.gui.ConsoleColor;

public class GameBoard {
	public static final int SIZE_GRID = 15;
	
	private Cell[][] cells;
	
	public GameBoard() {
        this.cells = new Cell[SIZE_GRID][SIZE_GRID];
        initializeEmptyBoard();
    }
	
	private void initializeEmptyBoard() {
		Effects[][] effectsGrid = {
	        {Effects.TRIPLE_WORD, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.TRIPLE_WORD},
	        {Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE},
	        {Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE},
	        {Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER},
	        {Effects.NONE, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.NONE},
	        {Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE},
	        {Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE},
	        {Effects.TRIPLE_WORD, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.STARS, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.TRIPLE_WORD},
	        {Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE},
	        {Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE},
	        {Effects.NONE, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.NONE},
	        {Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER},
	        {Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE},
	        {Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_WORD, Effects.NONE},
	        {Effects.TRIPLE_WORD, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.NONE, Effects.TRIPLE_WORD, Effects.NONE, Effects.NONE, Effects.NONE, Effects.DOUBLE_LETTER, Effects.NONE, Effects.NONE, Effects.TRIPLE_WORD}
	   };

	    for (int i = 0; i < SIZE_GRID; i++) {
	        for (int j = 0; j < SIZE_GRID; j++) {
	            cells[i][j] = new Cell(effectsGrid[i][j]);
	        }
	    }
    }
	
	public String showGameBoard() {
		StringBuilder temp = new StringBuilder();
		final String ROW_SEPARATOR = "   " + Console.SEPARATOR_LINE + "\n";
		
		temp.append("     ");
	    for (int i = 1; i <= SIZE_GRID; i++) {
	        temp.append(String.format("%-4d", i));
	    }
	    temp.append("\n");
        for (int i = 0; i < SIZE_GRID; i++) {
        	temp.append(ROW_SEPARATOR);
        	temp.append(String.format("%2d ", i+1));
            for (int j = 0; j < SIZE_GRID; j++) {
                if (!this.cells[i][j].isEmpty()) {
                	temp.append("|"+ ConsoleColor.CYAN_BOLD.getCode() + String.format("%-3s",this.cells[i][j].getTile().display()) + ConsoleColor.RESET.getCode());
                }
                else {
                	String symbol = this.cells[i][j].getEffect().getUnicode();
                    ConsoleColor color;
                    switch (this.cells[i][j].getEffect()) {
                        case STARS:
                            color = ConsoleColor.YELLOW_BACKGROUND;
                            break;
                        case TRIPLE_LETTER:
                            color = ConsoleColor.RED_BACKGROUND;
                            break;
                        case DOUBLE_LETTER:
                            color = ConsoleColor.GREEN_BACKGROUND;
                            break;
                        case DOUBLE_WORD:
                            color = ConsoleColor.BLUE_BACKGROUND;
                            break;
                        case TRIPLE_WORD:
                            color = ConsoleColor.PURPLE_BACKGROUND;
                            break;
                        case NONE:
                        default:
                            color = ConsoleColor.RESET;
                            break;
                    }
                    temp.append("|"+color.getCode() +" " + symbol + " "+ ConsoleColor.RESET.getCode());
                }
            }
            temp.append("|\n");
        }
        temp.append(ROW_SEPARATOR);
        return temp.toString();
    }

	public void addTile(Tile tile, int row, int column) {
		this.cells[row-1][column-1].setTile(tile);
	}
	
	
}