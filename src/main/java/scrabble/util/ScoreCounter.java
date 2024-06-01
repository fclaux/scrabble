package scrabble.util;

import java.util.List;

import scrabble.controller.ConsoleGameController;
import scrabble.gui.Console;
import scrabble.model.Cell;
import scrabble.model.Effects;
import scrabble.model.GameBoard;
import scrabble.model.Move;
import scrabble.model.Tile;

public class ScoreCounter {
	public static final int SCRABBLE = 50;

    public int calculateScoreForMoves(List<Move> moves, GameBoard gameBoard) throws IndexOutOfBoardException {
        List<List<Cell>> words = ConsoleGameController.findWordsFromMoves(moves, gameBoard);
        int totalScore = 0;

        for (List<Cell> word : words) {
            totalScore += calculateScoreForWord(word);
        }
        if (moves.size() == 7) {
        	totalScore += SCRABBLE;
        }
        return totalScore;
    }



    private int calculateScoreForWord(List<Cell> cells) {
        int totalScore = 0;
        int wordMultiplier = 1;
        int tileScore;

        for (Cell cell : cells) {
            Tile tile = cell.tile();
            if(tile.isJoker()) {
                tileScore = 0;
            } else {
                tileScore = tile.letter().value();
            }
        	
        		switch (cell.effect()) {
                
                case DOUBLE_LETTER:
                    tileScore *= 2;
                    Console.message("Lettre double", true);
                    cell.setEffect(Effects.NONE);
                    break;
                case TRIPLE_LETTER:
                    tileScore *= 3;
                    Console.message("Lettre tripple", true);
                    cell.setEffect(Effects.NONE);
                    break;
                case DOUBLE_WORD:
                    wordMultiplier *= 2;
                    Console.message("Mot double", true);
                    cell.setEffect(Effects.NONE);
                    break;
                case STARS:
                    wordMultiplier *= 2;
                    Console.message("Étoile", true);
                    cell.setEffect(Effects.NONE);
                    break;
                case TRIPLE_WORD:
                    wordMultiplier *= 3;
                    Console.message("Mot tripple", true);
                    cell.setEffect(Effects.NONE);
                    break;
                default:
                    break;
            
        	}
            

            totalScore += tileScore;

        }

        return totalScore * wordMultiplier;
    }
}
