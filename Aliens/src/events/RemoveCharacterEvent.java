package events;
import characters.Character;
import logic.MoneyHandler;
public class RemoveCharacterEvent extends WorldEvent{
	public Character character;
	public RemoveCharacterEvent(Character character) {
		super(world.lastTime, 0);
		this.character=character;
	}
	@Override
	public void handleEvent() {
		world.characters.remove(character);
		MoneyHandler.awardDeathPrize(character);
	}

}
