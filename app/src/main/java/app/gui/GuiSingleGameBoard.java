package app.gui;
import java.util.Iterator;

import javax.swing.JFrame;

import app.gui.dinamicobjects.FallingObject;
import app.gui.dinamicobjects.Flogger;
import app.gui.dinamicobjects.IPaintable;
import app.gui.dinamicobjects.Refresher;
import app.gui.dinamicobjects.Timer;
import app.gui.factories.FallingObjectCreator;
import app.gui.logic.GameLevel;
import app.managers.GuiManager;

public class GuiSingleGameBoard extends GuiGameBoard {
	private static final long serialVersionUID = -458446961144563050L;

	public GuiSingleGameBoard(JFrame f, GuiManager gm, Flogger fl, GameLevel level) {
		super(f, gm, fl);
		this.refresher = new Refresher(this);
		this.fallingObjectCreator = new FallingObjectCreator(this, level.getFallingObjectsDelay());
		this.timer = new Timer(gm, this, level.getTime());
		
		fl.setSpeed(level.getFloggerSpeed());
	}
	
	public void activate() {
		super.activate();
		this.resume();
	}
	
	public void deactivate() {
		super.deactivate();
		this.pause();
	}
	
	private synchronized void pause() {
		this.fallingObjectCreator.pause();
		
		Iterator<IPaintable> it = fallingObjects.iterator();
		while (it.hasNext()) {
			((FallingObject) it.next()).pause();
		}
		
		this.timer.pause();
	}
	
	private synchronized void resume() {
		this.fallingObjectCreator.resume();
		
		Iterator<IPaintable> it = fallingObjects.iterator();
		while (it.hasNext()) {
			((FallingObject) it.next()).resume();
		}
		
		this.timer.resume();
	}
}