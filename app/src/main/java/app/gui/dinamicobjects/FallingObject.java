package app.gui.dinamicobjects;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Observable;

public class FallingObject extends Observable implements Runnable, IPaintable, ICrashable {
	private static final double NUM_GRAVITY = 0.5;
	
	private Image image;
	private int xPosition;
	private int yPosition;
	private double xSpeed;
	private double ySpeed;
	private double time;
	private boolean isFalling;
	private int screenWidth;
	private Thread thread;
	private String imgFilename;
	
	public FallingObject(String imgFilename, Image image, int xPosition, double xSpeed, int screenWidth) {
		this.imgFilename = imgFilename;
		this.xPosition = xPosition;
		this.yPosition = 0;
		this.xSpeed = xSpeed;
		this.ySpeed = 0;
		this.time = 0;
		this.isFalling = true;
		this.image = image;
		this.screenWidth = screenWidth;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public void paint(Graphics g) {
		g.drawImage(
				this.image, 
				this.xPosition, 
				this.yPosition, 
				this.image.getWidth(null), 
				this.image.getHeight(null), 
				null);
	}
	
	public void paint(Graphics g, int itemNumber) {}
	
	public synchronized void leftSideCrash() {
		this.xPosition = 0;
		this.sideCrash();
	}
	
	public synchronized void rightSideCrash() {
		this.xPosition = this.screenWidth - this.image.getWidth(null);
		this.sideCrash();
	}
	
	private void sideCrash() {
		this.xSpeed *= -1;
	}
	
	public synchronized Rectangle getRectangle() {
		return new Rectangle(
				this.xPosition, 
				this.yPosition, 
				this.image.getWidth(null), 
				this.image.getHeight(null));
	}
	
	public void checkCrashes() {
		this.setChanged();
		this.notifyObservers();
	}
	
	public void run() {
		while (isFalling) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				return;
			}
			
			synchronized (this) {
				this.time += 0.25;
				this.xPosition += (int) this.xSpeed;
				this.yPosition += (int) this.ySpeed;
				this.ySpeed += this.time * NUM_GRAVITY;
				
				this.checkCrashes();
			}				
		}
	}
	
	public FakeItem toStaticObject() {
		return new FakeItem(this.imgFilename, this.xPosition, this.yPosition);
	}
	
	public void stop() {
		isFalling = false;
	}
	
	public void pause() {
		this.thread.suspend();
	}
	
	public void resume() {
		this.thread.resume();
	}
}