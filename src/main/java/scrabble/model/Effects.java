package scrabble.model;

public enum Effects {
	NONE(" "),
	STARS("\u2605"),
	DOUBLE_LETTER("\u25B3"),
	TRIPLE_LETTER("\u25B2"),
	DOUBLE_WORD("\u25A1"),
	TRIPLE_WORD("\u25A0");
	
	private final String symbol;
	
	Effects(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}