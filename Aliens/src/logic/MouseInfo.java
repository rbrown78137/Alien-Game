package logic;

public class MouseInfo {
	public double x;
	public double y;
	public long time;
	public boolean down;
	public MouseInfo(double x, double y, long time, boolean down){
		this.x=x;
		this.y=y;
		this.time = time;
		this.down = down;
		
	}
}