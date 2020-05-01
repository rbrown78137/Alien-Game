package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.MoneyHandler;
import logic.WeaponHandler;

public class TraderMenu extends PersistantObject {
	
	@Override 
	public void drawObject(GraphicsContext space) {
		space.setFill(Color.BLACK);
		space.setFont(new Font(Font.getFontNames().get(69),30));
		//space.fillRect(arg0, arg1, arg2, arg3);
		space.fillText("Regular Rifle (Lvl:"+(WeaponHandler.REGULAR_RIFLE_DAMAGE_INDEX+1)+")", 200, 200);
		space.fillText("Upgrade",200,240);
		if(WeaponHandler.REGULAR_RIFLE_DAMAGE_INDEX<7) {
			space.setFill(Color.DARKRED);
			space.fillRect(320, 240-25, 185, 35);
			space.setFill(Color.WHITE);
			space.fillText("Buy(Cost:"+MoneyHandler.UPGRADE_COST[WeaponHandler.REGULAR_RIFLE_DAMAGE_INDEX+1]+")", 320, 240);
		}
		
		
		
		space.setFill(Color.BLACK);
		space.fillText("Bomb(Lvl:"+(WeaponHandler.BOMB_DAMAGE_INDEX+1)+" Ammo: " +WeaponHandler.bombAmmo+")", 200, 310);
		space.fillText("Upgrade",200,350);
		if(WeaponHandler.REGULAR_RIFLE_DAMAGE_INDEX<7) {
			space.setFill(Color.DARKRED);
			space.fillRect(320, 350-25, 185, 35);
			space.setFill(Color.WHITE);
			space.fillText("Buy(Cost:"+MoneyHandler.UPGRADE_COST[WeaponHandler.BOMB_DAMAGE_INDEX+1]+")", 320, 350);
		}
		space.setFill(Color.BLACK);
		space.fillText("Ammo",200,390);
		space.setFill(Color.DARKRED);
		space.fillRect(320, 390-25, 185, 35);
		space.setFill(Color.WHITE);
		space.fillText("Buy(Cost:10)", 320, 390);
		
		
		
		
		space.setFill(Color.BLACK);
		space.fillText("Sniper Rifle(Lvl:"+(WeaponHandler.SNIPER_RIFLE_DAMAGE_INDEX+1)+" Ammo: " +WeaponHandler.sniperAmmo+")", 200, 460);
		space.fillText("Upgrade",200,500);
		if(WeaponHandler.REGULAR_RIFLE_DAMAGE_INDEX<7) {
			space.setFill(Color.DARKRED);
			space.fillRect(320, 500-25, 185, 35);
			space.setFill(Color.WHITE);
			space.fillText("Buy(Cost:"+MoneyHandler.UPGRADE_COST[WeaponHandler.SNIPER_RIFLE_DAMAGE_INDEX+1]+")", 320, 500);
		}
		space.setFill(Color.BLACK);
		space.fillText("Ammo",200,540);
		space.setFill(Color.DARKRED);
		space.fillRect(320, 540-25, 185, 35);
		space.setFill(Color.WHITE);
		space.fillText("Buy(Cost:2)", 320, 540);
		
		
		
		space.setFill(Color.DARKBLUE);
		space.fillRect(280, 600-25, 165, 35);
		space.setFill(Color.WHITE);
		space.fillText("Start Wave", 280, 600);
		
	 }
}
