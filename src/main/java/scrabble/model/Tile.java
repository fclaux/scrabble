package scrabble.model;

public class Tile {
	
	
	private Letters letter;
	private final boolean isJoker;
	
	public Tile(Letters letter) {
		if (letter == Letters.JOKER) {
			isJoker=true;
		} else {
			isJoker=false;
		}
		this.letter = letter;
	}

	public Letters getLetter() {
		return letter;
	}

	public void setLetter(Letters letter) {
		this.letter = letter;
	}
	
	public boolean isJoker() {
		return this.isJoker;
	}
	
	public String display() {
	    String[] subscriptDigits = {"\u2080","\u2081","\u2082","\u2083","\u2084","\u2085","\u2086","\u2087","\u2088","\u2089","\u2081\u2080"};
	    return this.letter + "" + subscriptDigits[this.letter.getValue()];
	}
}
