package scrabble.view.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import scrabble.model.Tile;

public class TileFX extends StackPane{
    private Tile tile;
    private Rectangle rectangle;
    private Text letterText;
    private Text valueText;

    public TileFX(Tile tile) {
        this.tile = tile;
        this.rectangle = new Rectangle(40, 40);
        this.letterText = new Text(tile.letter().toString());
        this.valueText = new Text(String.valueOf(tile.letter().value()));
        if (tile.isJoker()) {
        	this.letterText.setText("");
        	this.valueText.setText(String.valueOf(0));
        }

        rectangle.setFill(Color.BEIGE);
        rectangle.setStroke(Color.BLACK);
        this.letterText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        this.valueText.setFont(Font.font("Arial", 10));
        
        StackPane.setMargin(valueText, new Insets(0, 5, 5, 0));

        this.getChildren().addAll(rectangle, letterText, valueText);
        StackPane.setAlignment(letterText, Pos.CENTER);
        StackPane.setAlignment(valueText, Pos.BOTTOM_RIGHT);
    }

    public Tile tile() {
        return tile;
    }

    public void tile(Tile tile) {
        this.tile = tile;
        this.letterText.setText(tile.letter().toString());
        this.valueText.setText(String.valueOf(tile.letter().value()));
        if (tile.isJoker()) {
        	this.letterText.setText("");
        	this.valueText.setText(String.valueOf(0));
        }
    }
}
