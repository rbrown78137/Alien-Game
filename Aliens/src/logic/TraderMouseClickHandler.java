package logic;

import java.util.ArrayList;

import world.World;

public class TraderMouseClickHandler {
	public static World world = null;
	public static void handleTradeInteraction(ArrayList<MouseInfo> mouseInput) {
		if(!mouseInput.isEmpty()) {
			MouseInfo lastEvent = mouseInput.get(mouseInput.size()-1);
			if(!lastEvent.down) {
				mouseInput.remove(lastEvent);
				handleClickFromLocation(lastEvent.x,lastEvent.y);
			}
		}
	}
	private static void handleClickFromLocation(double x, double y) {
		if(x>=320 && x<=320+185 && y>=(240-25) && y<(240-25+35)) {
			//upgrade regular
			if(MoneyHandler.money >= MoneyHandler.UPGRADE_COST[WeaponHandler.REGULAR_RIFLE_DAMAGE_INDEX+1]) {
				MoneyHandler.money-=MoneyHandler.UPGRADE_COST[WeaponHandler.REGULAR_RIFLE_DAMAGE_INDEX+1];
				WeaponHandler.REGULAR_RIFLE_DAMAGE_INDEX++;
			}
		}else if(x>=320 && x<=320+185 && y>=(350-25) && y<(350-25+35)) {
			//upgrade bomb
			if(MoneyHandler.money >= MoneyHandler.UPGRADE_COST[WeaponHandler.BOMB_DAMAGE_INDEX+1]) {
				MoneyHandler.money-=MoneyHandler.UPGRADE_COST[WeaponHandler.BOMB_DAMAGE_INDEX+1];
				WeaponHandler.BOMB_DAMAGE_INDEX++;
			}
		}else if(x>=320 && x<=320+185 && y>=(390-25) && y<(390-25+35)) {
			//add bomb ammo
			if(MoneyHandler.money>=10) {
				MoneyHandler.money-=10;
				WeaponHandler.bombAmmo++;
			}
		}else if(x>=320 && x<=320+185 && y>=(500-25) && y<(500-25+35)) {
			//upgrade sniper
			if(MoneyHandler.money >= MoneyHandler.UPGRADE_COST[WeaponHandler.SNIPER_RIFLE_DAMAGE_INDEX+1]) {
				MoneyHandler.money-=MoneyHandler.UPGRADE_COST[WeaponHandler.SNIPER_RIFLE_DAMAGE_INDEX+1];
				WeaponHandler.SNIPER_RIFLE_DAMAGE_INDEX++;
			}
		}else if(x>=320 && x<=320+185 && y>=(540-25) && y<(540-25+35)) {
			//add sniper ammo
			if(MoneyHandler.money>=2) {
				MoneyHandler.money-=2;
				WeaponHandler.sniperAmmo++;
			}
		}else if(x>=280 && x<=280+165 && y>=(600-25) && y<(600-25+35)) {
			//start wave
			world.gameState =world.TRANSITIONING_TO_ROUND;
			
		}
		
	}
}
