package logic;

import characters.Character;
import characters.BasicEnemy;
import characters.CloakerEnemy;
import characters.Hero;
import characters.MoverEnemy;
import characters.TankEnemy;
import events.RemoveCharacterEvent;
import world.World;

public class EnemyAttackHandler {
	public World world;
	public EnemyAttackHandler(World world) {
		this.world = world;
	}
	public void handleAttack() {
		for(Character character:world.characters) {
			if(inCollisionDistance(character,world.hero)){
			if(character instanceof BasicEnemy || character instanceof CloakerEnemy) {
				world.futureEvents.add(new RemoveCharacterEvent(character));
				world.hero.health--;
			}
			else if(character instanceof TankEnemy || character instanceof MoverEnemy) {
				if(character.lastAttackTime == 0 || System.currentTimeMillis() > character.lastAttackTime+1000) {
				world.hero.health--;
				}
			}
		}
		}
	}
	public boolean inCollisionDistance(Character character, Hero hero) {
		if(Math.abs(character.x-hero.x)<36/2 && Math.abs(character.y-hero.y)<48/2) {
			return true;
		}
		return false;
	}
}
