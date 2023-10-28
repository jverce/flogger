package app.gui.logic;
import java.util.Observable;
import java.util.Observer;

import app.gui.dinamicobjects.Flogger;
import app.managers.GuiManager;

public class FloggerObserver implements Observer {
	private GuiManager guiManager;
	
	public FloggerObserver(GuiManager gm) {
		this.guiManager = gm;
	}
	
	public void update(Observable o, Object arg) {
		this.guiManager.sendFlogger((Flogger) o);
	}	
}