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

	public Letters letter() {
		return letter;
	}

	public void setLetter(Letters letter) {
		this.letter = letter;
	}
	
	public boolean isJoker() {
		return this.isJoker;
	}
}
