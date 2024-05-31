package scrabble.util;

import java.util.ArrayList;
import java.util.List;

import scrabble.gui.Console;
import scrabble.model.Cell;
import scrabble.model.GameBoard;
import scrabble.model.Move;
import scrabble.model.Tile;

public class ScoreCounter {

    public int calculateScoreForMoves(List<Move> moves, GameBoard gameBoard) throws IndexOutOfBoardException {
        List<List<Cell>> words = findWordsFromMoves(moves, gameBoard);
        int totalScore = 0;

        for (List<Cell> word : words) {
            totalScore += calculateScoreForWord(word);
        }

        return totalScore;
    }

    private List<List<Cell>> findWordsFromMoves(List<Move> moves, GameBoard gameBoard) throws IndexOutOfBoardException {
        List<List<Cell>> words = new ArrayList<>();

        for (Move move : moves) {
            List<Cell> horizontalWord = getWord(move, gameBoard, true);
            if (horizontalWord.size() > 1 && !words.contains(horizontalWord)) {
                words.add(horizontalWord);
            }

            List<Cell> verticalWord = getWord(move, gameBoard, false);
            if (verticalWord.size() > 1 && !words.contains(verticalWord)) {
                words.add(verticalWord);
            }
        }
        Console.message(""+words, true);
        Console.message("moves"+moves,false);

        return words;
    }

    private List<Cell> getWord(Move move, GameBoard gameBoard, boolean isHorizontal) throws IndexOutOfBoardException {
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
/*/
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
/*/
            totalScore += tileScore;
        }

        return totalScore * wordMultiplier;
    }
}
