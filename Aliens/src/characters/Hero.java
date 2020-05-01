package characters;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Hero extends Character {

	public Hero(String baseLink) {
		super();
		for(int a=1;a<=1;a++) {
		for(int i =0;i<4;i++) {
			for(int j=0;j<3;j++) {
				image.add(new Image(baseLink+a+"-"+i+"-"+j+".png"));
			}
		}
		health = 5;
		x=400;
		y=400;
	}
		
	}
	public void move(long timePassed, ArrayList<String> keysPressed) {
		if(keysPressed.contains("a")&&keysPressed.contains("w")&&!keysPressed.contains("s")&&!keysPressed.contains("d")) {
			xVelocity = -4/Math.sqrt(2.0);
			yVelocity = -4/Math.sqrt(2.0);
		}if(!keysPressed.contains("a")&&keysPressed.contains("w")&&!keysPressed.contains("s")&&keysPressed.contains("d")) {
			xVelocity = 4/Math.sqrt(2.0);
			yVelocity = -4/Math.sqrt(2.0);
		}
		if(keysPressed.contains("a")&&!keysPressed.contains("w")&&keysPressed.contains("s")&&!keysPressed.contains("d")) {
			xVelocity = -4/Math.sqrt(2.0);
			yVelocity = 4/Math.sqrt(2.0);
		}if(!keysPressed.contains("a")&&!keysPressed.contains("w")&&keysPressed.contains("s")&&keysPressed.contains("d")) {
			xVelocity = 4/Math.sqrt(2.0);
			yVelocity = 4/Math.sqrt(2.0);
		}
		if(keysPressed.contains("a")&&!keysPressed.contains("w")&&!keysPressed.contains("s")&&!keysPressed.contains("d")) {
			xVelocity = -4;
			yVelocity = 0;
		}if(!keysPressed.contains("a")&&!keysPressed.contains("w")&&!keysPressed.contains("s")&&keysPressed.contains("d")) {
			xVelocity = 4;
			yVelocity = 0;
		}
		if(!keysPressed.contains("a")&&keysPressed.contains("w")&&!keysPressed.contains("s")&&!keysPressed.contains("d")) {
			xVelocity = 0;
			yVelocity = -4;
		}if(!keysPressed.contains("a")&&!keysPressed.contains("w")&&keysPressed.contains("s")&&!keysPressed.contains("d")) {
			xVelocity = 0;
			yVelocity = 4;
		}
		if(!keysPressed.contains("a")&&!keysPressed.contains("w")&&!keysPressed.contains("s")&&!keysPressed.contains("d")) {
			xVelocity = 0;
			yVelocity = 0;
		}
		moveOverTime(timePassed);
	}
}
