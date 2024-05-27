package scrabble.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrabble.model.Cell;
import scrabble.model.GameBoard;
import scrabble.model.Move;
import scrabble.model.Tile;

public class ScoreCounter {

    public static int calculateScoreForMoves(List<Move> moves, GameBoard gameBoard) throws IndexOutOfBoardException {
        List<List<Cell>> words = getWordsFromMoves(moves, gameBoard);
        int totalScore = 0;

        for (List<Cell> word : words) {
            totalScore += calculateScoreForWord(word);
        }

        return totalScore;
    }

    private static List<List<Cell>> getWordsFromMoves(List<Move> moves, GameBoard gameBoard) throws IndexOutOfBoardException {
        List<List<Cell>> words = new ArrayList<>();
        Set<Cell> processedCells = new HashSet<>();

        for (Move move : moves) {
            if (!processedCells.contains(gameBoard.cell(move.row(), move.col()))) {
                List<Cell> horizontalWord = getWord(move, gameBoard, true);
                if (horizontalWord.size() > 1) {
                    words.add(horizontalWord);
                    processedCells.addAll(horizontalWord);
                }

                List<Cell> verticalWord = getWord(move, gameBoard, false);
                if (verticalWord.size() > 1) {
                    words.add(verticalWord);
                    processedCells.addAll(verticalWord);
                }
            }
        }

        return words;
    }

    private static List<Cell> getWord(Move move, GameBoard gameBoard, boolean isHorizontal) throws IndexOutOfBoardException {
        List<Cell> word = new ArrayList<>();
        int row = move.row();
        int col = move.col();

        while ((isHorizontal ? col > 0 : row > 0) && 
               !gameBoard.cell(isHorizontal ? row : row - 1, isHorizontal ? col - 1 : col).isEmpty()) {
            if (isHorizontal) {
                col--;
            } else {
                row--;
            }
        }

        while ((isHorizontal ? col < GameBoard.SIZE_GRID : row < GameBoard.SIZE_GRID) && 
               !gameBoard.cell(row, col).isEmpty()) {
            word.add(gameBoard.cell(row, col));
            if (isHorizontal) {
                col++;
            } else {
                row++;
            }
        }

        return word;
    }

    private static int calculateScoreForWord(List<Cell> cells) {
        int totalScore = 0;
        int wordMultiplier = 1;
        int tileScore;

        for (Cell cell : cells) {
            Tile tile = cell.tile();
            if(tile.isJoker()) {
            	tileScore = 0;
            }
            else{
            	tileScore = tile.letter().value();
            }
         
            switch (cell.effect()) {
                case DOUBLE_LETTER:
                    tileScore *= 2;
                    break;
                case TRIPLE_LETTER:
                    tileScore *= 3;
                    break;
                case DOUBLE_WORD:
                	wordMultiplier *= 2;
                    break;
                case STARS:
                    wordMultiplier *= 2; 	
                    break;
                case TRIPLE_WORD:
                    wordMultiplier *= 3;
                    break;
                default:
                    break;
            }

            totalScore += tileScore;
        }

        return totalScore * wordMultiplier;
    }
}
