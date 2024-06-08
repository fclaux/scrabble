package scrabble.view.javafx;

import javafx.scene.control.Label;
import scrabble.model.Player;

public class PlayerFX extends Label{
	private Player player;
	
	public PlayerFX (Player player) {
		this.player=player;
		
		this.setText("Joueur : " + player.name() + "\nScore Total = " + String.valueOf(player.score()));
	}
	
	public void updatePlayer() {
		this.setText("Joueur : " + player.name() + "\nScore Total = " + String.valueOf(player.score()));
	}
}
