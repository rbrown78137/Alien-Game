package logic;

import java.util.ArrayList;

import world.World;

public class MouseHandler {
	public static World world;
 public static void handleMouseClick(ArrayList<MouseInfo> mouseInput) {
	 if(world.gameState== World.PREGAME_SCREEN && (System.currentTimeMillis()-world.startTime)>1000 && !mouseInput.isEmpty()) {
		 world.gameState=World.TRANSITIONING_TO_ROUND;
		 world.persistantObjects.clear();
		 mouseInput.clear();
		 world.persistantObjects.add(world.waveCounter);
		 world.persistantObjects.add(world.moneyCounter);
		 world.persistantObjects.add(world.weaponDisplay);
		 world.persistantObjects.add(world.ammoCounter);
		 world.persistantObjects.add(world.healthCounter);
	 }
	if(world.gameState == World.IN_ROUND) {
		if(!mouseInput.isEmpty()) {
		MouseInfo lastEvent = mouseInput.get(mouseInput.size()-1);
		if(lastEvent.down) {
		WeaponHandler.createShoot(lastEvent, world);
		}else {
			mouseInput.remove(mouseInput.size()-1);
		}
		}
	}
	if(world.gameState==World.IN_TRADE_MENU) {
		TraderMouseClickHandler.handleTradeInteraction(mouseInput);
	}
	 if(world.gameState== World.END_GAME_SCREEN && !mouseInput.isEmpty()) {
		 world.gameState=World.PREGAME_SCREEN;
		 world.persistantObjects.clear();
		 world.characters.clear();
		 world.futureEvents.clear();
		 world.persistantObjects.add(world.pregameScreen);
		 world.round = 0;
		 WeaponHandler.BOMB_DAMAGE_INDEX =0;
		 WeaponHandler.REGULAR_RIFLE_DAMAGE_INDEX =0;
		 WeaponHandler.SNIPER_RIFLE_DAMAGE_INDEX =0;
		 WeaponHandler.bombAmmo =0;
		 WeaponHandler.sniperAmmo=0;
		 MoneyHandler.money =0;
		 world.waveCounter.wave=0;
		 world.hero.health= 5;
		 mouseInput.clear();
		 
	 }
 }
}
