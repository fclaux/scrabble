package scrabble.model;

public class GameBoard {
	private Cell[][] cells;
	private final int numRows;
	private final int numCols;
	
	public GameBoard(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.cells = new Cell[numRows][numCols];
        initializeEmptyBoard();
    }
	
	private void initializeEmptyBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                cells[i][j] = new Cell(Effects.NONE);
            }
        }
        cells[8][8] = new Cell(Effects.STARS);
    }
}