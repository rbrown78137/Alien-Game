package events;

public class RemoveShotEvent extends WorldEvent {
	Object shot;
	public RemoveShotEvent(Object shot) {
		super(System.currentTimeMillis(), 250);
		this.shot = shot;
	}
	@Override
	public void handleEvent() {
		world.persistantObjects.remove(shot);
	}
}
