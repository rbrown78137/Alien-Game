package events;

import characters.TankEnemy;

public class SpawnTankEnemyEvent extends WorldEvent{
	public SpawnTankEnemyEvent(long timeCreated,long waitTime) {
		super(timeCreated,waitTime);
	}
	@Override
	public void handleEvent() {
		System.out.println("Attempting to spawn");
	double spawnSide = Math.random()*4;
		if(spawnSide<1) {
			world.characters.add(new TankEnemy( ((int)(Math.random()*800)),0,world.hero));
		}else if(spawnSide<2) {
			world.characters.add(new TankEnemy( ((int)(Math.random()*800)),800,world.hero));
		}else if (spawnSide<3) {
			world.characters.add(new TankEnemy(0,((int)(Math.random()*800)),world.hero ));
		}else {
			world.characters.add(new TankEnemy(800,((int)(Math.random()*800)),world.hero ));
		}
		
	}
}
