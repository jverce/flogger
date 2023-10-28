package app.gui.key;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import app.gui.dinamicobjects.Flogger;

public class FloggerKeyListener implements KeyListener {
	private Flogger flogger;
	private final Map<Integer, Method> table;

	public FloggerKeyListener(Flogger f) {
		this.flogger = f;
		this.table = new TreeMap<Integer, Method>();

		try {
			this.table.put(
					KeyEvent.VK_UP, 
					this.flogger.getClass().getMethod("moveUp", (Class[]) null));
			this.table.put(
					KeyEvent.VK_DOWN, 
					this.flogger.getClass().getMethod("moveDown", (Class[]) null));
			this.table.put(
					KeyEvent.VK_LEFT, 
					this.flogger.getClass().getMethod("moveLeft", (Class[]) null));
			this.table.put(
					KeyEvent.VK_RIGHT, 
					this.flogger.getClass().getMethod("moveRight", (Class[]) null));
			this.table.put(
					KeyEvent.VK_PAGE_UP, 
					this.flogger.getClass().getMethod(("superUp"), (Class[]) null));
			this.table.put(
					KeyEvent.VK_PAGE_DOWN, 
					this.flogger.getClass().getMethod(("superDown"), (Class[]) null));
			this.table.put(
					KeyEvent.VK_HOME, 
					this.flogger.getClass().getMethod(("superLeft"), (Class[]) null));
			this.table.put(
					KeyEvent.VK_END, 
					this.flogger.getClass().getMethod(("superRight"), (Class[]) null));
		} catch (final NoSuchMethodException nsme) {
			System.err.println("Won't move correctly: " + nsme.getMessage());
		}
	}

	public void keyPressed(KeyEvent event) {
		try {
			this.table.get(event.getKeyCode()).invoke(this.flogger, (Object[]) null);
			this.flogger.checkCrashes();
		} catch (final Exception e) {
			return;
		}
	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
		this.keyPressed(event);
	}

	public void setFlogger(Flogger f) {
		this.flogger = f;
	}
}