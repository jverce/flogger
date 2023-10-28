package app.gui.logic;
public class GameLevel {
	public static final int EASY = 100;
	public static final int MEDIUM = 300;
	public static final int HARD  = 500;
	
	private int fallingObjectsDelay;
	private int floggerSpeed;
	
	public GameLevel() {
		this.setLevel(MEDIUM);
	}
	
	public void setLevel(int level) {
		this.fallingObjectsDelay = level;
		
		double d = HARD / this.fallingObjectsDelay;
		this.floggerSpeed = (int) (d * 20);
	}
	
	public int getFallingObjectsDelay() {
		return this.fallingObjectsDelay;
	}
	
	public int getFloggerSpeed() {
		return this.floggerSpeed;
	}
	
	public int getTime() {
		return this.floggerSpeed * 4;
	}
}