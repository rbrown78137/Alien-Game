package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.MoneyHandler;

public class MoneyCounter extends PersistantObject{
	@Override
	public void drawObject(GraphicsContext space) {
		space.setFill(Color.BLACK);
		space.setFont(new Font(Font.getFontNames().get(69),30));
		space.fillText("Money: "+MoneyHandler.money, 0, 60);
	}

}
