package app.gui.logic;


import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import app.gui.GuiGameBoard;
import app.gui.dinamicobjects.ICrashable;
import app.gui.dinamicobjects.IPaintable;
import app.gui.dinamicobjects.IScorer;

public class CollisionManager implements Observer {
	private GuiGameBoard gui;
	private Rectangle leftWall;
	private Rectangle rightWall;
	private List<IPaintable> floggers;
	
	public CollisionManager(GuiGameBoard g) {
		this.gui = g;
		this.leftWall = new Rectangle(-1, 0, 1, g.getHeight());		
		this.rightWall = new Rectangle(g.getWidth(), 0, 1, g.getHeight());
		this.floggers = new ArrayList<IPaintable>();
	}
	
	public synchronized void addFlogger(IPaintable f) {
		this.floggers.add(f);
	}
	
	public synchronized void update(Observable o, Object arg) {
		this.checkWallCrash((ICrashable) o);
		this.checkFloggerCrash((ICrashable) o);
		this.checkInsideScreen((ICrashable) o);
	}
	
	private void checkWallCrash(ICrashable obj) {
		boolean leftCrash = obj.getRectangle().intersects(this.leftWall);
		boolean rightCrash = obj.getRectangle().intersects(this.rightWall);
		
		Class[] methodArgs = {ICrashable.class};
		Object[] args = {obj};
		
		try {
			this.getClass().getMethod(leftCrash + "LeftCrash", methodArgs).invoke(this, args);
			this.getClass().getMethod(rightCrash + "RightCrash", methodArgs).invoke(this, args);
		} catch (Exception e) {
			return;
		}
	}
	
	private void checkFloggerCrash(ICrashable obj) {
		Iterator<IPaintable> it = this.floggers.iterator();
		while (it.hasNext()) {
			IScorer f = (IScorer) it.next();
			boolean crash = obj.getRectangle().intersects(f.getRectangle());
			Class[] methodArgs = {ICrashable.class, IScorer.class};
			Object[] args = {obj, f};
			
			try {
				this.getClass().getMethod(crash + "FloggerCrash", methodArgs).invoke(this, args);
			} catch (Exception e) {
				return;
			}
		}
	}
	
	private void checkInsideScreen(ICrashable obj) {
		boolean out = !obj.getRectangle().intersects(this.gui.getRectangle());
		Class[] methodArgs = {ICrashable.class};
		Object[] args = {obj};
		
		try {
			this.getClass().getMethod(out + "OutScreen", methodArgs).invoke(this, args);
		} catch (Exception e) {
			return;
		}
	}
	
	public void trueLeftCrash(ICrashable obj) {
		obj.leftSideCrash();
	}
	
	public void trueRightCrash(ICrashable obj) {
		obj.rightSideCrash();
	}
	
	public void falseLeftCrash(ICrashable obj) {}
	public void falseRightCrash(ICrashable obj) {}
	
	public void trueFloggerCrash(ICrashable obj, IScorer f) {
		obj.stop();
		this.gui.removeFallingObject(obj);
		
		f.score();
	}
	
	public void falseFloggerCrash(ICrashable obj, IScorer f) {}
	
	public void trueOutScreen(ICrashable obj) {
		obj.stop();
		this.gui.removeFallingObject(obj);
	}
	
	public void falseOutScreen(ICrashable obj) {}
}