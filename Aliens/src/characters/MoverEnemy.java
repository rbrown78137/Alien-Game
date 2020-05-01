package characters;

import events.RemoveCharacterEvent;
import javafx.scene.image.Image;
import world.World;

public class MoverEnemy extends Character {
	public boolean charging;
	public long chargeStartTime;
	public long lastActionTime;
	public World world;
	public static final long CHARGE_TIME = 1000;
	public static final long WAIT_TIME=13000;
	public static final int EXPLOSION_RADIUS = 150;
	public MoverEnemy(int x, int y,World world) {
		super();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				image.add(new Image("Mover/ShootC" + "-" + i + "-" + j + ".png"));
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				image.add(new Image("Mover/ShooterC" + "-" + i + "-" + j + ".png"));
			}
		}
			health = 200;
			speed = 2;
			this.x = x;
			this.y = y;
			deathPrize=10;
			charging=false;
			chargeStartTime =0;
			lastActionTime = 0;
			this.world =world;
	}
	@Override
	public Image getImage(int location) {
		if(charging) {
			return image.get(location+12);
		}
		return image.get(location);
	}
	@Override
	public void action() {
		if(charging==false &&(lastActionTime==0 || System.currentTimeMillis()-lastActionTime>WAIT_TIME)) {
			chargeStartTime = System.currentTimeMillis();
			charging =true;
		}else if(charging && System.currentTimeMillis()-chargeStartTime>CHARGE_TIME) {
			charging =false;
			lastActionTime =System.currentTimeMillis();
			for(Character character:world.characters) {
				if(!character.equals(this) && !(character instanceof MoverEnemy ||character instanceof TankEnemy)) {
					moveCharacter(character);
				}
			}
			
		}
	}
	public void moveCharacter(Character character) {
		 double sumX=0;
		 double sumY=0;
		
		 double distanceBetweenCharacters = Math.sqrt(Math.pow(Math.abs(this.x-character.x),2)+Math.pow(Math.abs(this.y-character.y),2));
		 if(distanceBetweenCharacters<EXPLOSION_RADIUS) {
			 sumX +=((character.x-this.x)/distanceBetweenCharacters) *(EXPLOSION_RADIUS-distanceBetweenCharacters);
			 sumY +=((character.y-this.y)/distanceBetweenCharacters) *(EXPLOSION_RADIUS-distanceBetweenCharacters);
			 //Collision Detection Area
			  double heroCharacterXDistance =world.hero.x -character.x;
			  double heroCharacterYDistance =world.hero.y -character.y;
			  double distanceBetweenHeroAndCharacter = Math.sqrt(Math.pow(Math.abs(heroCharacterXDistance),2)+Math.pow(Math.abs(heroCharacterYDistance),2));
			  double adjustedX = sumX * distanceBetweenHeroAndCharacter / EXPLOSION_RADIUS;
			  double adjustedY = sumY * distanceBetweenHeroAndCharacter / EXPLOSION_RADIUS;
			  double vectorDotProduct =  heroCharacterXDistance * adjustedX + heroCharacterYDistance *adjustedY;
			  double angleBetweenNewSpotAndHero = Math.acos(vectorDotProduct/(Math.pow(distanceBetweenHeroAndCharacter,2)));
			  double arcDistance = distanceBetweenHeroAndCharacter* angleBetweenNewSpotAndHero / (2*Math.PI);
			  if(arcDistance<40 &&  (distanceBetweenHeroAndCharacter / EXPLOSION_RADIUS) <1) {
				  world.futureEvents.add(new RemoveCharacterEvent(character));
				  world.hero.health--;
			  }
			 //
			 character.x += sumX; 
			 character.y += sumY;
		 }
	}
}
