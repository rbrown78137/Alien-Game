package logic;
import characters.Character;
public class MoneyHandler {
public static int money=0;
public static final int UPGRADE_COST[] = {10,20,40,60,80,120,160,240};
public static void awardDeathPrize(Character character) {
	money+=character.deathPrize;
}
}
