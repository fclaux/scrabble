package scrabble.model;

public class GameBoard {
	public static final int SIZE_GRID = 15;
	
	private Cell[][] cells;
	
	public GameBoard() {
        this.cells = new Cell[SIZE_GRID][SIZE_GRID];
        initializeEmptyBoard();
    }
	
	private void initializeEmptyBoard() {
        for (int i = 0; i < SIZE_GRID; i++) {
            for (int j = 0; j < SIZE_GRID; j++) {
                cells[i][j] = new Cell(Effects.NONE);
            }
        }
        cells[7][7] = new Cell(Effects.STARS);
    }
}