package scrabble.application;

import scrabble.controller.ConsoleGameController;

public class ScrabbleApplicationConsole {

	
	public static void main(String[] args) {
		ConsoleGameController gameController = new ConsoleGameController();
		
		gameController.startGame();
	}
	
	

}
