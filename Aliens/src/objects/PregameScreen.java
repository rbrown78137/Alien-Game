package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PregameScreen extends PersistantObject{
	public Image image;
	public PregameScreen(){
	image = new Image("Display/pregame.png");
	}
	@Override
	public void drawObject(GraphicsContext space) {
		space.drawImage(image, 0, 0);
	 }

}
