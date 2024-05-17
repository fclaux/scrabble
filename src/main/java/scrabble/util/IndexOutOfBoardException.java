package scrabble.util;

@SuppressWarnings("serial")
public class IndexOutOfBoardException extends Exception {
	public IndexOutOfBoardException(String message) {
		super(message);
	}
}
