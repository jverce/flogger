package app.gui.key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import app.managers.GuiManager;

public class PauseKeyListener implements KeyListener {
	private Map<Integer, Method> actions;
	private GuiManager guiManager;
	
	public PauseKeyListener(GuiManager gm) {
		this.guiManager = gm;
		this.actions = new TreeMap<Integer, Method>();
		this.init();
	}
	
	private void init() {
		try {
			this.actions.put(
					KeyEvent.VK_ESCAPE, 
					this.guiManager.getClass().getMethod("pauseAction", (Class[]) null));
		} catch (Exception e) {
			return;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		try {
			this.actions.get(e.getKeyCode()).invoke(this.guiManager, (Object[]) null);
		} catch (Exception ex) {
			return;
		}
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}