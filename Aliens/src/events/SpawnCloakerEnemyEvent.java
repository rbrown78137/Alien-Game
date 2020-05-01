package events;

import characters.CloakerEnemy;

public class SpawnCloakerEnemyEvent extends WorldEvent{
	public SpawnCloakerEnemyEvent(long timeCreated,long waitTime) {
		super(timeCreated,waitTime);
	}
	@Override
	public void handleEvent() {
		System.out.println("Attempting to spawn");
	double spawnSide = Math.random()*4;
		if(spawnSide<1) {
			world.characters.add(new CloakerEnemy( ((int)(Math.random()*800)),0));
		}else if(spawnSide<2) {
			world.characters.add(new CloakerEnemy( ((int)(Math.random()*800)),800));
		}else if (spawnSide<3) {
			world.characters.add(new CloakerEnemy(0,((int)(Math.random()*800)) ));
		}else {
			world.characters.add(new CloakerEnemy(800,((int)(Math.random()*800)) ));
		}
		
	}
}
