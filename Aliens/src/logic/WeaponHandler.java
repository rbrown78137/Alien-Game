package logic;

import events.RemoveCharacterEvent;
import events.RemoveShotEvent;
import objects.MouseShot;
import objects.WeaponDisplay;
import world.World;
import characters.Character;

public class WeaponHandler {
	public static int currentWeapon = 1;
	public static final int REGULAR_RIFLE =1;
	public static final int BOMB =2;
	public static final int SNIPER_RIFLE =3;
	public static final int[] REGULAR_RIFLE_DAMAGE = {1,2,3,4,5,6,7,8};
	public static final int[] BOMB_DAMAGE = {3,6,9,12,15,18,21,24};
	public static final int[] SNIPER_RIFLE_DAMAGE = {100,200,300,400,500,600,700,800};
	public static final int REGULAR_RIFLE_SHOT_RADIUS =30;
	public static final int BOMB_SHOT_RADIUS =300;
	public static final int SNIPER_RIFLE_SHOT_RADIUS =50;
	public static int REGULAR_RIFLE_DAMAGE_INDEX =0;
	public static int BOMB_DAMAGE_INDEX=0;
	public static int SNIPER_RIFLE_DAMAGE_INDEX=0;
	public static long lastTimeFired = 0;
	public static int lastFiredWeapon = 0;
	public static int bombAmmo =0;
	public static int sniperAmmo=0;
	public static final int WEAPON_DISTANCE_GRACE_VALUE=5;
	public static WeaponDisplay weaponDisplay =null;
	public static void switchWeapon(String text) {
		if(text.contains("f")) {
			if(currentWeapon==REGULAR_RIFLE) {
				currentWeapon = BOMB;
				weaponDisplay.weaponIndex=BOMB;
			}else if(currentWeapon==BOMB) {
				currentWeapon = SNIPER_RIFLE;
				weaponDisplay.weaponIndex=SNIPER_RIFLE;
			}else if(currentWeapon==SNIPER_RIFLE) {
				currentWeapon = REGULAR_RIFLE;
				weaponDisplay.weaponIndex=REGULAR_RIFLE;
			}
		}else if(text.contains("1")) {
			currentWeapon = REGULAR_RIFLE;
			weaponDisplay.weaponIndex=REGULAR_RIFLE;
		}
		else if(text.contains("2")) {
			currentWeapon = BOMB;
			weaponDisplay.weaponIndex=BOMB;
		}
		else if(text.contains("3")) {
			currentWeapon = SNIPER_RIFLE;
			weaponDisplay.weaponIndex=SNIPER_RIFLE;
		}
	}
	public static int getRegularRifleDamage() {
		return REGULAR_RIFLE_DAMAGE[REGULAR_RIFLE_DAMAGE_INDEX];
	}
	public static int getBombDamage() {
		return BOMB_DAMAGE[BOMB_DAMAGE_INDEX];
	}
	public static int getSniperRifleDamage() {
		return SNIPER_RIFLE_DAMAGE[SNIPER_RIFLE_DAMAGE_INDEX];
	}
	public static void createShoot(MouseInfo mouseEvent, World world) {
		if(canFire(System.currentTimeMillis())) {
		MouseShot mouseShot = new MouseShot(currentWeapon,mouseEvent.x,mouseEvent.y);
		world.persistantObjects.add(mouseShot);
		world.futureEvents.add(new RemoveShotEvent(mouseShot));
		lastFiredWeapon = currentWeapon;
		lastTimeFired = System.currentTimeMillis();
		if(lastFiredWeapon==BOMB) {
			bombAmmo--;
		}
		else if(lastFiredWeapon==SNIPER_RIFLE) {
			sniperAmmo--;
		}
		for(Character character:world.characters) {
			if(inShotRadius(mouseEvent, character)) {
				damageCharacter(world, character);
			}
		}
		}
		
	}
	public static boolean canFire(long currentTime) {
		if(currentWeapon== REGULAR_RIFLE &&(lastFiredWeapon==REGULAR_RIFLE&&(currentTime-lastTimeFired)<(1000.0/12))) {
			return false;
		}
		if(currentWeapon == BOMB && (((lastFiredWeapon==BOMB&&(currentTime-lastTimeFired)<(1000.0)) ||bombAmmo<=0))) {
			return false;
		}
		if(currentWeapon == SNIPER_RIFLE && ((lastFiredWeapon==SNIPER_RIFLE && (currentTime-lastTimeFired)<(1000.0/2)) || sniperAmmo<=0)) {
			return false;
		}
		return true;
	}
	public static boolean inShotRadius(MouseInfo mouseEvent, Character character) {
		if(lastFiredWeapon==REGULAR_RIFLE) {
			return withinRadius(REGULAR_RIFLE_SHOT_RADIUS/2+WEAPON_DISTANCE_GRACE_VALUE, mouseEvent.x, mouseEvent.y, character.x, character.y);
		}
		else if(lastFiredWeapon==BOMB) {
			return withinRadius(BOMB_SHOT_RADIUS/2+WEAPON_DISTANCE_GRACE_VALUE, mouseEvent.x, mouseEvent.y, character.x, character.y);
		}
		else if(lastFiredWeapon==SNIPER_RIFLE) {
			return withinRadius(SNIPER_RIFLE_SHOT_RADIUS+WEAPON_DISTANCE_GRACE_VALUE, mouseEvent.x, mouseEvent.y, character.x, character.y);
		}
		return false;
	}
	public static boolean withinRadius(double radius, double xObject1, double yObject1, double xObject2, double yObject2) {
		if(radius> Math.sqrt(  Math.pow(Math.abs(xObject1-xObject2),2) + Math.pow(Math.abs(yObject1-yObject2),2)  ) ){
			return true;
		}
		return false;
	}
	public static void damageCharacter(World world, Character character) {
		if(lastFiredWeapon==REGULAR_RIFLE) {
			character.health-=getRegularRifleDamage();
		}
		else if(lastFiredWeapon==BOMB) {
			character.health-=getBombDamage();
		}
		else if(lastFiredWeapon==SNIPER_RIFLE) {
			character.health-=getSniperRifleDamage();
		}
		if(character.health<=0) {
			world.futureEvents.add(new RemoveCharacterEvent(character));
		}
	}
} 
