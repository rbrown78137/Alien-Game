package characters;

import javafx.scene.canvas.GraphicsContext;

public class TankEnemy extends Character {
	public int tankState;
	public static final int PASSIVE_STATE =1;
	public static final int ANGERED_STATE =2;
	public static final int CHARGE_STATE =3;
	public static final int ANGRY_RUN_STATE=4;
	public long timeAngerBegan;
	public long timeChargeBegan;
	public long timeRunBegan;
	public double angryRunXVelocity;
	public double angryRunYVelocity;
	public Hero hero;
	public static final int AGRO_DISTANCE =200;
	public static final long AGRO_TIME_ALLOWED = 1000;
	public static final long CHARGE_TIME = 1000;
	public static final double CHARGE_SPEED =10;
	public static final long RUN_TIME =700;
	public TankEnemy(int x, int y,Hero hero){
		super("Tank/TankC");
		speed=0.5;
		this.x=x;
		this.y=y;
		deathPrize=25;
		health =700;
		tankState = 1;
		angryRunXVelocity=0;
		angryRunYVelocity=0;
		timeAngerBegan =0;
		timeChargeBegan =0;
		timeRunBegan=0;
		this.hero=hero;
	}
	@Override
	public void drawImageAtCenter(GraphicsContext space, int location) {
		space.drawImage(getImage(location), x-36, y-48);
	}
	@Override
	public void move(long timePassed, Character mainCharacter) {
		if (tankState == PASSIVE_STATE) {
			super.move(timePassed, mainCharacter);
			double distance = Math
					.sqrt(Math.pow(Math.abs(hero.x - this.x), 2) + Math.pow(Math.abs(hero.y - this.y), 2));
			if (distance <= AGRO_DISTANCE) {
				tankState = ANGERED_STATE;
				timeAngerBegan = System.currentTimeMillis();
			}
		} else if (tankState == ANGERED_STATE) {
			super.move(timePassed, mainCharacter);
			double distance = Math
					.sqrt(Math.pow(Math.abs(hero.x - this.x), 2) + Math.pow(Math.abs(hero.y - this.y), 2));
			if (distance <= AGRO_DISTANCE && System.currentTimeMillis() - timeAngerBegan > AGRO_TIME_ALLOWED) {
				tankState = CHARGE_STATE;
				timeAngerBegan = 0;
				timeChargeBegan = System.currentTimeMillis();
			} else if (distance > AGRO_DISTANCE) {
				tankState = PASSIVE_STATE;
			}
		} else if (tankState == CHARGE_STATE) {
			if (System.currentTimeMillis() - timeChargeBegan > CHARGE_TIME) {
				tankState = ANGRY_RUN_STATE;
				timeRunBegan = System.currentTimeMillis();
				double xDistance = hero.x - this.x;
				double yDistance = hero.y - this.y;
				double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
				if (distance != 0) {
					xVelocity = CHARGE_SPEED * xDistance / distance;
					yVelocity = CHARGE_SPEED * yDistance / distance;
				}
			}
		}else if(tankState == ANGRY_RUN_STATE) {
			moveOverTime(timePassed);
			boolean outOfBounds = x<0 || x>800 || y<0 || y>800;
			if(System.currentTimeMillis() - timeRunBegan > RUN_TIME ||outOfBounds) {
				tankState = PASSIVE_STATE;
			}
		}
	}

}

