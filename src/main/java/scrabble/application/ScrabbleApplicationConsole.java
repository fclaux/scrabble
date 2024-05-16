package scrabble.application;

import scrabble.controller.GameMaster;

public class ScrabbleApplicationConsole {
	
	public static void main(String[] args) {
		GameMaster gameMaster = new GameMaster();
		
		gameMaster.start();
	}

}
