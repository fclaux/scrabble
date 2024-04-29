package scrabble.model;

public class Player {
	private final String name;
	private int score;
	private Rack rack;
	
	public Player(String name) {
		this.name = name;
		this.score = 0;
		this.rack = new Rack();
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}

	public Rack getRack() {
		return this.rack;
	}
	
}
