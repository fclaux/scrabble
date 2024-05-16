package scrabble.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.controller.GameMaster;
import scrabble.gui.Console;
import scrabble.gui.ConsoleColor;
import scrabble.model.*;

public class ScrabbleApplicationConsole {
	private static final int STOP_VALUE = -1;
	
	public static void main(String[] args) {
		GameMaster gameMaster = new GameMaster();
		gameMaster.start();
		
		showGameBoard(gameMaster.gameBoard());
	}
	
	private static void showGameBoard(GameBoard gameboard) {
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
                if (!gameboard.getCell(i,j).isEmpty()) {
                	temp.append("|"+ ConsoleColor.CYAN_BOLD.getCode() + String.format("%-3s",gameboard.getCell(i,j).getTile().display()) + ConsoleColor.RESET.getCode());
                }
                else {
                	String symbol = gameboard.getCell(i,j).getEffect().getUnicode();
                    ConsoleColor color;
                    switch (gameboard.getCell(i,j).getEffect()) {
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
	
	private static List<Integer> playerChooseTileForChange(Scanner scanner) {
		Console.title("  Pour remplacez des éléments, entrez les indices un à un puis écrivez "+ STOP_VALUE +" pour arrêter");
		int input;
		List<Integer> indices = new ArrayList<>();
		do {
			Console.message("Entrez la place de l'élément que vous voulez retirez : ");
			input = scanner.nextInt();
			
			if ((input < 1 || input > Rack.MAX_TILES)&&(input!=STOP_VALUE)) {
				Console.message("ERREUR, veuillez rentrer un nombre entre 1 et "+ Rack.MAX_TILES +" : ");
			}
			else if (indices.contains(input-1)) {
				Console.message("ERREUR, veuillez rentrer un nombre différents de ceux déja entrés  : ");
			}
			else {
				indices.add(input-1);
			}
		} while (input != STOP_VALUE);
		return indices;
	}

}
