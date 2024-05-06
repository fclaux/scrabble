package scrabble.gui;

public class Console {
	
	private Console() {
	}

	public static void message(String text) {
		System.out.println(text);
	}

	public static final String SEPARATOR_LINE = "---------------------------------------------------------";

	public static void title(String text) {
		separateur();
		message(text);
		separateur();
	}
	
	public static void separateur() {
		message(SEPARATOR_LINE);
	}
}

