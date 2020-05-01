package objects;

import characters.Hero;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class HealthCounter extends PersistantObject{
	private Hero hero;
	public HealthCounter(Hero hero) {
		this.hero = hero;
	}
	@Override
	public void drawObject(GraphicsContext space) {
	space.setStroke(Color.BLACK);
	space.setLineWidth(2);
	space.setFill(Color.DARKRED);
	if(hero.health>=0) {
	space.fillRect(620, 10, 150*hero.health/5.0, 27);
	}
	space.strokeRect(620, 10, 150, 27);
	}
}
