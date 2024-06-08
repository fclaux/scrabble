package scrabble.view.javafx;

import javafx.scene.layout.GridPane;
import scrabble.model.*;
import scrabble.util.IndexOutOfBoardException;

public class GameBoardFX extends GridPane {
    private GameBoard gameBoard;
    private CellFX[][] cellFXGrid;

    public GameBoardFX(GameBoard gameBoard) throws IndexOutOfBoardException {
        this.gameBoard = gameBoard;
        this.cellFXGrid = new CellFX[GameBoard.SIZE_GRID][GameBoard.SIZE_GRID];

        initializeGrid();
    }

    private void initializeGrid() throws IndexOutOfBoardException {
        for (int row = 0; row < GameBoard.SIZE_GRID; row++) {
            for (int col = 0; col < GameBoard.SIZE_GRID; col++) {
                Cell cell = gameBoard.cell(row+1, col+1);
                CellFX cellFX = new CellFX(cell);
                cellFXGrid[row][col] = cellFX;
                add(cellFX.getStackPane(), col, row);
            }
        }
    }

    public void updateCell(int row, int col) throws IndexOutOfBoardException {
        Cell cell = gameBoard.cell(row+1, col+1);
        CellFX cellFX = new CellFX(cell);
        cellFXGrid[row][col] = cellFX;
        add(cellFX.getStackPane(), col, row);
    }

    public void updateBoard() throws IndexOutOfBoardException {
        for (int row = 0; row < GameBoard.SIZE_GRID; row++) {
            for (int col = 0; col < GameBoard.SIZE_GRID; col++) {
                updateCell(row, col);
            }
        }
    }
}
