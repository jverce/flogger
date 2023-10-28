package app.gui;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import app.gui.dinamicobjects.FakeFlogger;
import app.gui.dinamicobjects.Flogger;
import app.gui.dinamicobjects.Refresher;
import app.gui.dinamicobjects.RemoteRefresher;
import app.gui.dinamicobjects.Timer;
import app.gui.factories.FallingObjectCreator;
import app.gui.logic.FloggerObserver;
import app.gui.logic.GameLevel;
import app.managers.GuiManager;

public class GuiMultiGameBoard extends GuiGameBoard {
	private static final long serialVersionUID = -4921753620541490890L;
	private boolean isHostMachine;
	private FakeFlogger remoteFlogger;
	private FloggerObserver floggerObserver;
	
	public GuiMultiGameBoard(JFrame f, GuiManager gm, Flogger fl, boolean host) {
		super(f, gm, fl);
		this.floggerObserver = new FloggerObserver(gm);
		fl.addObserver(this.floggerObserver);
		
		this.timer = new Timer(gm, this, GameLevel.MEDIUM);
		this.isHostMachine = host;
		this.init();
	}
	
	private void init() {
		try {
			this.getClass()
			.getMethod(this.isHostMachine + "Host", (Class[]) null)
			.invoke(this, ((Object[]) null));
		} catch (Exception e) {
			return;
		}
	}
	
	public void trueHost() {
		this.refresher = new Refresher(this);
		this.fallingObjectCreator = new FallingObjectCreator(this, GameLevel.MEDIUM);
	}
	
	public void falseHost() {
		this.refresher = new RemoteRefresher(this);
	}
	
	public void setFlogger(FakeFlogger f) {
		this.remoteFlogger = f;
		this.collisionManager.addFlogger(f);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.yellow);
		g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
		
		try {
			this.remoteFlogger.paint(g, this);
		} catch (NullPointerException e) {}
	}
	
	
}