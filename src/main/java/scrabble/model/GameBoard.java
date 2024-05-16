package scrabble.model;

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
	
	public Cell getCell(int row, int column) {
		return this.cells[row-1][column-1];
	}
	
	
}