package events;

public class AddSpawnEventsEvent extends WorldEvent {
	public int waveNumber;
	public AddSpawnEventsEvent(int waveNumber) {
		super(System.currentTimeMillis(), 0);
		this.waveNumber = waveNumber;
	}
	@Override
	public void handleEvent() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < (waveNumber + 5); j = j + 4) {
				world.futureEvents.add(new SpawnRegularEnemyEvent(timeCreated, i * (1000+i*250))); //was 1000+i*wave
			}
			for (int j = 0; j < waveNumber - 4; j = j + 6) {
				world.futureEvents.add(new SpawnCloakerEnemyEvent(timeCreated, i * (1000+i*250)));
			}
			if (i == 2 || i == 5 && waveNumber>16) {
				if(waveNumber>6) {
				world.futureEvents.add(new SpawnMoverEnemyEvent(timeCreated, i * (1000+i*250)));
				}
			}
			if (i == 3 || i == 7) {
				for (int j = 0; j < waveNumber - 8; j = j + 8) {
					world.futureEvents.add(new SpawnTankEnemyEvent(timeCreated, i * (1000+i*250)));
				}
			}

		}
	}
}
