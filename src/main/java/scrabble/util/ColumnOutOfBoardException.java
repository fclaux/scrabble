package scrabble.util;

@SuppressWarnings("serial")
public class ColumnOutOfBoardException extends Exception {
	public ColumnOutOfBoardException(String message) {
		super(message);
	}
}
