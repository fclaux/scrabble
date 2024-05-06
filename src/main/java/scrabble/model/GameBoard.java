package scrabble.model;

import scrabble.gui.Console;

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
	
	public String showGameBoard() {
		StringBuilder temp = new StringBuilder();
    	temp.append("     1   2   3   4   5   6   7   8   9  10  11  12  13  14  15\n");
        for (int i = 0; i < 15; i++) {
        	temp.append("   "+Console.SEPARATOR_LINE + "\n");
        	temp.append(String.format("%2d ", i + 1));
            for (int j = 0; j < 15; j++) {
                if (this.cells[i][j].isOccupied()) {
                	temp.append("| " + this.cells[i][j].getTile().getLetter() +" ");
                }
                else {
                	String symbol = this.cells[i][j].getEffect().getUnicode();
                    String color;
                    switch (this.cells[i][j].getEffect()) {
                        case STARS:
                            color = "\u001B[43m";
                            break;
                        case TRIPLE_LETTER:
                            color = "\u001B[41m";
                            break;
                        case DOUBLE_LETTER:
                            color = "\u001B[42m";
                            break;
                        case DOUBLE_WORD:
                            color = "\u001B[44m";
                            break;
                        case TRIPLE_WORD:
                            color = "\u001B[45m";
                            break;
                        case NONE:
                        default:
                            color = "\u001B[0m";
                            break;
                    }
                    temp.append("|"+color +" " + symbol + " \u001B[0m");
                }
                
            }
            temp.append("|\n");
        }
        temp.append("   "+Console.SEPARATOR_LINE + "\n");
        return temp.toString();
    }
}