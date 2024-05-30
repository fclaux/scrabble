package scrabble.model;

import java.util.Objects;

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

	public void letter(Letters letter) {
		this.letter = letter;
	}
	
	public boolean isJoker() {
		return this.isJoker;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(isJoker, letter);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		return isJoker == other.isJoker && letter == other.letter;
	}
}
