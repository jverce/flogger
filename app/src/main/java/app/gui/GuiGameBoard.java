package app.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import app.gui.dinamicobjects.FallingObject;
import app.gui.dinamicobjects.Flogger;
import app.gui.dinamicobjects.IPaintable;
import app.gui.dinamicobjects.Refresher;
import app.gui.dinamicobjects.Timer;
import app.gui.factories.FallingObjectCreator;
import app.gui.key.FloggerKeyListener;
import app.gui.key.PauseKeyListener;
import app.gui.logic.CollisionManager;
import app.gui.managers.AudioManager;
import app.managers.GuiManager;

public abstract class GuiGameBoard extends Gui {
	private static final long serialVersionUID = -8563736162762911171L;
	
	protected GuiManager guiManager;
	protected Refresher refresher;
	protected CollisionManager collisionManager;
	protected List<IPaintable> fallingObjects;
	protected FallingObjectCreator fallingObjectCreator;
	protected Flogger flogger;
	protected KeyListener pauseKeyListener;
	protected KeyListener floggerKeyListener;
	protected Timer timer;
	
	public GuiGameBoard(JFrame f, GuiManager gm, Flogger fl) {
		super(f);
		this.setSize(f.getWidth(), f.getHeight());
		
		this.guiManager = gm;
		this.collisionManager = new CollisionManager(this);
		
		this.flogger = fl;
		fl.setGui(this);
		this.collisionManager.addFlogger(fl);
		
		this.fallingObjects = new ArrayList<IPaintable>();
		this.pauseKeyListener = new PauseKeyListener(gm);
		this.floggerKeyListener = new FloggerKeyListener(fl);
		
		AudioManager.stop();
		AudioManager.playMusic(AudioManager.GAMEBOARD_MUSIC);
	}
	
	public synchronized void getRemoteFallingObjects() {
		this.fallingObjects = this.guiManager.getRemoteFallingObjects();
	}
	
	public void activate() {
		super.activate();
		this.window.addKeyListener(this.pauseKeyListener);
		this.window.addKeyListener(this.floggerKeyListener);
	}
	
	public void deactivate() {
		super.deactivate();
		this.window.removeKeyListener(this.pauseKeyListener);
		this.window.removeKeyListener(this.floggerKeyListener);
	}
	
	public synchronized void addFallingObject(IPaintable obj) {
		this.fallingObjects.add(obj);
		((FallingObject) obj).addObserver(this.collisionManager);
	}
	
	public synchronized void removeFallingObject(IPaintable obj) {
		((FallingObject) obj).stop();
		this.fallingObjects.remove(obj);
	}
	
	public synchronized List<IPaintable> getFallingObjects() {
		List<IPaintable> list = new ArrayList<IPaintable>();
		Iterator<IPaintable> it = this.fallingObjects.iterator();
		
		while(it.hasNext()) {
			list.add(((FallingObject)it.next()).toStaticObject());
		}
		
		return list;
	}
	
	public synchronized void update(Graphics g) {
		Image bgImage = this.createImage(this.getWidth(), this.getHeight());
		Graphics bgGraph = bgImage.getGraphics();			

		bgGraph.setColor(Color.black);
		bgGraph.fillRect(0, 0, this.getWidth(), this.getHeight());

		this.paint(bgGraph);
		g.drawImage(bgImage, 0, 0, this);
	}
	
	public synchronized void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		this.flogger.paint(g);
		
		Iterator<IPaintable> it = fallingObjects.iterator();
		while (it.hasNext()) {
			it.next().paint(g);
		}
		
		this.timer.paint(g);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(0, 0, this.getWidth(), this.getHeight());
	}
} 