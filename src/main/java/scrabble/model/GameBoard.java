package scrabble.model;

import scrabble.util.IndexOutOfBoardException;

public class GameBoard {
	public static final int SIZE_GRID = 15;
	public static final int MIDDLE_CASE = calculateMiddleCase(SIZE_GRID);
	
	private Cell[][] cells;
	
	public GameBoard() {
        this.cells = new Cell[SIZE_GRID][SIZE_GRID];
        initializeEmptyBoard();
    }
	
	private static int calculateMiddleCase(int size) {
        return size / 2 + (size % 2 == 0 ? 0 : 1);
    }
	
	private void initializeEmptyBoard() {
		Effects[][] effectsGrid15 = {
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
	            cells[i][j] = new Cell(effectsGrid15[i][j]);
	        }
	    }
    }

	public void addTile(Tile tile, int row, int column) {
		this.cells[row-1][column-1].tile(tile);
	}
	
	public Cell cell(int row, int column) throws IndexOutOfBoardException {
		if(row > SIZE_GRID || row <= 0) {
			throw new IndexOutOfBoardException("Invalid row");	
		}
		else if(column > SIZE_GRID || column <= 0){
			throw new IndexOutOfBoardException("Invalid column");
		}
		return this.cells[row-1][column-1];

		
		
	}
	public Tile tile(int row, int column) {
        try {
			return cell(row,column).tile();
		} 
		 catch (IndexOutOfBoardException e) {
			e.printStackTrace();
		}
		return null;
    }	
	
}