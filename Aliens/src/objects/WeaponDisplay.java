package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.WeaponHandler;

public class WeaponDisplay extends PersistantObject {
	public int weaponIndex;
	public WeaponDisplay() {
		weaponIndex = WeaponHandler.REGULAR_RIFLE;
	}
	@Override
	public void drawObject(GraphicsContext space) {
		space.setFill(Color.BLACK);
		space.setFont(new Font(Font.getFontNames().get(69),30));
		
		if(weaponIndex==WeaponHandler.REGULAR_RIFLE) {
			space.fillText("Regular", 0, 90);
		}
		else if(weaponIndex==WeaponHandler.BOMB) {
			space.fillText("Bomb", 0, 90);
		}
		else if(weaponIndex==WeaponHandler.SNIPER_RIFLE) {
			space.fillText("Sniper", 0, 90);
		}
	}
}
