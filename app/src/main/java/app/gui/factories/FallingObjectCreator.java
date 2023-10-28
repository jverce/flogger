package app.gui.factories;
import app.gui.GuiGameBoard;

public class FallingObjectCreator implements Runnable {
	private GuiGameBoard gui;
	private Thread thread;
	private int fallingObjectsDelayTime;
	private boolean isWorking;
	
	public FallingObjectCreator(GuiGameBoard g, int delayTime) {
		this.gui = g;
		this.fallingObjectsDelayTime = delayTime;
		this.isWorking = true;
		this.thread = new Thread(this);
		
		this.thread.start();
	}
	
	public void run() {
		while (isWorking) {
			try {
				Thread.sleep(this.fallingObjectsDelayTime);
			} catch (InterruptedException e) {
				return;
			}
			
			this.gui.addFallingObject(FallingObjectFactory.getFallingObject(this.gui.getWidth()));
		}
	}
	
	public void pause() {
		this.thread.suspend();
	}
	
	public void resume() {
		this.thread.resume();
	}
	
	public void stop() {
		this.isWorking = false;
	}
}