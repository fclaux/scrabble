package scrabble.util;

@SuppressWarnings("serial")
public class RowOutOfBoardException extends Exception {
	public RowOutOfBoardException(String message) {
		super(message);
	}
}
