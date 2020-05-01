package characters;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Character {
	public ArrayList<Image> image;
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public long timeMoved;
	public int lastImageIndex;
	public double speed;
	public int health;
	public int deathPrize;
	public long lastAttackTime;
	public Character(){
		image = new ArrayList<Image>();
		x=0;
		y=0;
		xVelocity = 0;
		yVelocity = 0;
		timeMoved=0;
		lastImageIndex =6;
		speed = 6;
		deathPrize=0;
		lastAttackTime = 0;
	}
	public Character(String baseLink) {
		image= new ArrayList<Image>();
		for(int i =0;i<4;i++) {
			for(int j=0;j<3;j++) {
				System.out.println(baseLink+"-"+i+"-"+j+".png");
				image.add(new Image(baseLink+"-"+i+"-"+j+".png"));
			}
		}
		x=0;
		y=0;
		xVelocity = 0;
		yVelocity = 0;
		timeMoved=0;
	}
	public void draw(GraphicsContext space) {
		if(xVelocity>0 &&(yVelocity==0 || Math.abs(xVelocity/yVelocity) >= 1)) {
			int location = 1*3+(int)(timeMoved*3/1000)%3;
			drawImageAtCenter(space, location);
			lastImageIndex = location;
		}else if(xVelocity<0  &&(yVelocity==0 || Math.abs(xVelocity/yVelocity) >= 1)) {
			int location = 3*3+(int)(timeMoved*3/1000)%3;
			drawImageAtCenter(space, location);
			lastImageIndex = location;
		}else if(yVelocity>0) {
			int location = 2*3+(int)(timeMoved*3/1000)%3;
			drawImageAtCenter(space, location);
			lastImageIndex = location;
		}else if(yVelocity<0) {
			int location = 0*3+(int)(timeMoved*3/1000)%3;
			drawImageAtCenter(space, location);
			lastImageIndex = location;
		}else {
			drawImageAtCenter(space, lastImageIndex);
		}
		
	}
	public void moveOverTime(long timePassed) {
		
		x = x + xVelocity*(timePassed/1000.0)/(1/60.0);
		y = y + yVelocity*(timePassed/1000.0)/(1/60.0);
		if(((int)xVelocity)!=0 && ((int)yVelocity)!=0) {
			timeMoved+=timePassed;
		}
		timeMoved+=timePassed;
	}
	public void move(long timePassed,Character mainCharacter) {
		double xDistance = mainCharacter.x-this.x;
		double yDistance = mainCharacter.y-this.y;
		double distance = Math.sqrt(Math.pow(xDistance, 2)+Math.pow(yDistance, 2));
		if(distance!=0) {
		xVelocity = speed*xDistance/distance;
		yVelocity = speed*yDistance/distance;
		moveOverTime(timePassed);
		}
	}
	public Image getImage(int location) {
		return image.get(location);
	}
	public void drawImageAtCenter(GraphicsContext space, int location) {
		space.drawImage(getImage(location), x-18, y-24);
	}
	public void action() {
		
	}
}
