package characters;

public class BasicEnemy extends Character {
	public BasicEnemy(){
		super("Regular/RegC");
		speed=2;
	}
	public BasicEnemy(int x, int y){
		super("Regular/RegC");
		speed=2;
		this.x=x;
		this.y=y;
		deathPrize=2;
	}
}
