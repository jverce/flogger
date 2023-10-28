package app.gui.dinamicobjects;
import app.gui.Gui;
import app.gui.GuiGameBoard;

public class RemoteRefresher extends Refresher {
	public RemoteRefresher(Gui g) {
		super(g);
	}
	
	public void run() {
		while (this.isWorking) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
			
			((GuiGameBoard) this.gui).getRemoteFallingObjects();
			this.gui.repaint();
		}
	}
}