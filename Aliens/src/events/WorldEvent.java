package events;

import world.World;

public class WorldEvent {
	public long timeCreated;
	public long waitTime;
	public static World world;
	public WorldEvent(long timeCreated,long waitTime) {
		this.timeCreated = timeCreated;
		this.waitTime = waitTime;
	}
	public boolean remove(long frameTime) {
		if(frameTime>=(timeCreated+waitTime)) {
			handleEvent();
			return true;
		}
		return false;
	}
	public void handleEvent() {
		
	}
}
