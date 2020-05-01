package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.WeaponHandler;

public class MouseShot extends PersistantObject {
	public int typeOfShot;
	public double x;
	public double y;
	public static final int REGULAR_RIFLE =1;
	public static final int BOMB=2;
	public static final int SNIPER_RIFLE=3;
	public MouseShot(int typeOfShot,double x, double y) {
		//System.out.println("X: "+ x+ " Y: "+ y);
		this.typeOfShot=typeOfShot;
		this.x=x;
		this.y=y;
	}
	@Override
	public void drawObject(GraphicsContext space) {
		if (typeOfShot == REGULAR_RIFLE) {
			space.setFill(Color.LIGHTGREEN);
			space.fillOval(x- WeaponHandler.REGULAR_RIFLE_SHOT_RADIUS/2, y-WeaponHandler.REGULAR_RIFLE_SHOT_RADIUS/2, WeaponHandler.REGULAR_RIFLE_SHOT_RADIUS, WeaponHandler.REGULAR_RIFLE_SHOT_RADIUS);
		} else if (typeOfShot == BOMB) {
			space.setFill(Color.RED);
			space.fillOval(x-WeaponHandler.BOMB_SHOT_RADIUS/2, y-WeaponHandler.BOMB_SHOT_RADIUS/2, WeaponHandler.BOMB_SHOT_RADIUS, WeaponHandler.BOMB_SHOT_RADIUS);
		} else if (typeOfShot == SNIPER_RIFLE) {
			space.setFill(Color.LIGHTBLUE);
			space.fillOval(x-WeaponHandler.SNIPER_RIFLE_SHOT_RADIUS/2, y-WeaponHandler.SNIPER_RIFLE_SHOT_RADIUS/2, WeaponHandler.SNIPER_RIFLE_SHOT_RADIUS, WeaponHandler.SNIPER_RIFLE_SHOT_RADIUS);
		}
	}
}
