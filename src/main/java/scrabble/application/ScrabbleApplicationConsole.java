package scrabble.application;

import scrabble.controller.ConsoleGameController;
import scrabble.util.IndexOutOfBoardException;

public class ScrabbleApplicationConsole {
	
	public static void main(String[] args) throws IndexOutOfBoardException {
		ConsoleGameController consoleGameController = new ConsoleGameController();
		
		consoleGameController.startGame();
	} 
}
