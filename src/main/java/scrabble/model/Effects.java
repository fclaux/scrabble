package scrabble.model;

public enum Effects {
	NONE(" "),
	STARS("\u2605"),
	DOUBLE_LETTER("\u25B3"),
	TRIPLE_LETTER("\u25B2"),
	DOUBLE_WORD("\u25A1"),
	TRIPLE_WORD("\u25A0");
	
	private final String unicode;
	
	Effects(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode() {
        return unicode;
    }
}