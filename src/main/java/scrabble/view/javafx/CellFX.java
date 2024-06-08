package scrabble.view.javafx;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import scrabble.model.Cell;

public class CellFX extends StackPane{
    private Rectangle rectangle;
    private Cell cell;
    private Text effectText;

    public CellFX(Cell cell) {
        this.rectangle = new Rectangle(40, 40);
        this.effectText = new Text();
        this.cell = cell;
        
        rectangle.setStroke(Color.BLACK);
        
        effectText.setFont(Font.font("Arial", FontWeight.BOLD, 8));
        effectText.setFill(Color.WHITE);
        effectText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        StackPane.setAlignment(effectText, Pos.CENTER);
        

        updateVisualEffect();
        this.getChildren().addAll(rectangle, effectText);
    }

    public StackPane getStackPane() {
    	if (this.cell.isEmpty()) {
    		return this;
    	}
    	else {
    		return new TileFX(this.cell.tile());
    	}
    }

    private void updateVisualEffect() {
        switch (this.cell.effect()) {
        	case STARS:
        		rectangle.setFill(Color.HOTPINK);
        		effectText.setText("ETOILE");
        		break;
            case DOUBLE_LETTER:
                rectangle.setFill(Color.LIGHTBLUE);
                effectText.setText("LETTRE\nDOUBLE");
                break;
            case TRIPLE_LETTER:
                rectangle.setFill(Color.DARKBLUE);
                effectText.setText("LETTRE\nTRIPLE");
                break;
            case DOUBLE_WORD:
                rectangle.setFill(Color.ORANGE);
                effectText.setText("MOT\nDOUBLE");
                break;
            case TRIPLE_WORD:
                rectangle.setFill(Color.RED);
                effectText.setText("MOT\nTRIPLE");
                break;
            default:
                rectangle.setFill(Color.GREEN);
                effectText.setText("");
                break;
        }
    }
}
