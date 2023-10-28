package app.gui.key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import app.gui.Gui;
import app.gui.managers.AudioManager;
import app.gui.other.Menu;


public class MenuKeyListener implements KeyListener {
	private Menu menu;
	private Gui gui;
	private Map<Integer, Method> actionMap;
	
	public MenuKeyListener(Menu menu, Gui gui) {
		this.menu = menu;
		this.gui = gui;
		this.actionMap = new TreeMap<Integer, Method>();
		
		this.initActions();
	}
	
	private void initActions() {
		try {
			this.actionMap.put(
					KeyEvent.VK_UP, 
					this.menu.getClass().getMethod("decSelector", (Class[]) null));
			this.actionMap.put(
					KeyEvent.VK_DOWN, 
					this.menu.getClass().getMethod("incSelector", (Class[]) null));
			this.actionMap.put(
					KeyEvent.VK_ENTER, 
					this.menu.getClass().getMethod("selectItem", (Class[]) null));
		} catch (Exception e) {
			return;
		}
	}

	public void keyPressed(KeyEvent e) {
		try {
			this.actionMap.get(e.getKeyCode()).invoke(this.menu, (Object[]) null);
		} catch (Exception ex) {
			return;
		}
		
		this.gui.repaint();
		
		AudioManager.playAudio(AudioManager.MENU_ITEM);
	}

	public void keyReleased(KeyEvent e) {}	

	public void keyTyped(KeyEvent e) {}
}