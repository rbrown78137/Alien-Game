package events;

import characters.MoverEnemy;

public class SpawnMoverEnemyEvent extends WorldEvent {
	public SpawnMoverEnemyEvent(long timeCreated,long waitTime) {
		super(timeCreated,waitTime);
	}
	@Override
	public void handleEvent() {
		System.out.println("Attempting to spawn");
	double spawnSide = Math.random()*4;
		if(spawnSide<1) {
			world.characters.add(new MoverEnemy( ((int)(Math.random()*800)),0,world));
		}else if(spawnSide<2) {
			world.characters.add(new MoverEnemy( ((int)(Math.random()*800)),800,world));
		}else if (spawnSide<3) {
			world.characters.add(new MoverEnemy(0,((int)(Math.random()*800)),world ));
		}else {
			world.characters.add(new MoverEnemy(800,((int)(Math.random()*800)),world ));
		}
		
	}	
}
