package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EndGameScreen extends PersistantObject{
	public int round;
	public EndGameScreen() {
		round = 0;
	}
	@Override
	public void drawObject(GraphicsContext space) {
		space.setFill(Color.BLACK);
		space.setFont(new Font(Font.getFontNames().get(69),30));
		if(round<=5) {
		space.fillText("You only made it " + round+ " rounds?" , 80, 300);
		space.fillText("Maybe you should play something a bit easier" , 80, 330);
		space.fillText("like Minecraft." , 80, 360);
		}
		else if(round<=10) {
			space.fillText("You made it " + round+ " rounds?" , 80, 300);
			space.fillText("I guess you could have done worse" , 80, 330);
		}
		else {
			space.fillText("You made it " + round+ " rounds?" , 80, 300);
			space.fillText("Nice Job." , 80, 330);
		}
	 }

}