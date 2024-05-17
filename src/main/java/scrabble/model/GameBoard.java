package scrabble.model;

import scrabble.gui.Console;
import scrabble.gui.ConsoleColor;
import scrabble.util.IndexOutOfBoardException;

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

	public void addTile(Tile tile, int row, int column) {
		this.cells[row-1][column-1].setTile(tile);
	}
	
	public Cell getCell(int row, int column) throws IndexOutOfBoardException {
		if(row > SIZE_GRID || row <= 0) {
			throw new IndexOutOfBoardException("Invalid row");	
		}
		else if(column > SIZE_GRID || column <= 0){
			throw new IndexOutOfBoardException("Invalid column");
		}
		return this.cells[row-1][column-1];

		
		
	}
	public Tile getTile(int row, int column) {
        try {
			return getCell(row,column).getTile();
		} 
		 catch (IndexOutOfBoardException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	
	public void display() {
		StringBuilder temp = new StringBuilder();
		final String ROW_SEPARATOR = "   " + Console.SEPARATOR_LINE + "\n";
		
		temp.append("     ");
	    for (int i = 1; i <= GameBoard.SIZE_GRID; i++) {
	        temp.append(String.format("%-4d", i));
	    }
	    temp.append("\n");
        for (int i = 1; i <= GameBoard.SIZE_GRID; i++) {
        	temp.append(ROW_SEPARATOR);
        	temp.append(String.format("%2d ", i+1));
            for (int j = 1; j <= GameBoard.SIZE_GRID; j++) {
                Cell cell;
				try {
					cell = this.getCell(i,j);
				} catch (IndexOutOfBoardException e) {
					e.printStackTrace();
					continue;
				}
				if (!cell.isEmpty()) {
                	temp.append("|"+ ConsoleColor.CYAN_BOLD.getCode() + String.format("%-3s",cell.getTile().display()) + ConsoleColor.RESET.getCode());
                }
                else {
                	String symbol = cell.getEffect().getUnicode();
                    ConsoleColor color;
                    switch (cell.getEffect()) {
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
        Console.message(temp.toString()); 
	}
	
	
	
}