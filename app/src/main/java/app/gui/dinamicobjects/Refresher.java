package app.gui.dinamicobjects;
import app.gui.Gui;

public class Refresher implements Runnable {
	protected Gui gui;
	protected boolean isWorking;
	
	public Refresher(Gui gui) {
		this.gui = gui;
		this.isWorking = true;
		new Thread(this).start();
	}
	
	public void run() {
		while (this.isWorking) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException ie) {
				return;
			}
			
			this.gui.repaint();
		}
	}
	
	public void stop() {
		this.isWorking = false;
	}
}