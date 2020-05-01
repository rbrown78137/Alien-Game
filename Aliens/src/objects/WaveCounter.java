package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WaveCounter extends PersistantObject{
	public int wave;
	public WaveCounter() {
		wave = 0;
	}
	@Override
	public void drawObject(GraphicsContext space) {
		space.setFill(Color.BLACK);
		space.setFont(new Font(Font.getFontNames().get(69),30));
		space.fillText("Wave: "+wave, 0, 30);
	}
}
