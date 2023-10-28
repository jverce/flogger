package app.managers;

import java.util.List;

import app.gui.dinamicobjects.FakeFlogger;
import app.gui.dinamicobjects.Flogger;
import app.gui.dinamicobjects.IPaintable;
import app.remote.ConnectionManager;

public class MainManager {
	private GuiManager guiManager;
	private ConnectionManager connectionManager;

	public MainManager() {
		this.guiManager = new GuiManager(this);
		this.connectionManager = new ConnectionManager(this);
	}

	public Flogger singleGame() {
		return new Flogger();
	}

	public void endGame() {
		this.connectionManager.endGame();
	}

	public void multiHostGame() {
		this.guiManager.multiHostGame(new Flogger(true));
	}

	public void multiGuestGame() {
		this.guiManager.multiGuestGame(new Flogger(false));
	}

	public void cancel() {
		this.guiManager.cancelAction();
	}

	public void endedGame() {
		this.guiManager.mainMenu();
	}

	public void waitConnection() {
		this.connectionManager.waitConnection();
	}

	public void cancelWait() {
		this.connectionManager.cancelWait();
	}

	public void connect(String address) {
		this.connectionManager.connect(address);
	}

	public List<IPaintable> getFallingObjects() {
		return this.guiManager.getFallingObjects();
	}

	public List<IPaintable> getRemoteFallingObjects() {
		return this.connectionManager.getRemoteFallingObjects();
	}

	public void sendFlogger(Flogger f) {
		this.connectionManager.sendFlogger(f);
	}

	public void setFlogger(FakeFlogger f) {
		this.guiManager.setFlogger(f);
	}
}
