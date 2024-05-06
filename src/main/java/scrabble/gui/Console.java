package scrabble.gui;

public class Console {
	
	private Console() {
	}

	public static void message(String text) {
		System.out.println(text);
	}

	public static final String SEPARATOR_LINE = "-------------------------------------------------------------";

	public static void title(String text) {
		separator();
		message(text);
		separator();
	}
	
	public static void separator() {
		message(SEPARATOR_LINE);
	}
}

