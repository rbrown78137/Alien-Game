package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.WeaponHandler;

public class AmmoCounter extends PersistantObject {
	@Override
	public void drawObject(GraphicsContext space) {
		space.setFill(Color.BLACK);
		space.setFont(new Font(Font.getFontNames().get(69),30));
		if(WeaponHandler.currentWeapon == WeaponHandler.REGULAR_RIFLE) {
			
		}else if(WeaponHandler.currentWeapon==WeaponHandler.BOMB) {
		space.fillText("Ammo:"+WeaponHandler.bombAmmo, 665, 790);
		}
		else if(WeaponHandler.currentWeapon==WeaponHandler.SNIPER_RIFLE) {
			space.fillText("Ammo:"+WeaponHandler.sniperAmmo, 665, 790);
			}
	}
}
