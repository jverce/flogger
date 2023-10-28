package app.gui.key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import app.gui.GuiPopupForm;

public class FormKeyListener implements KeyListener {
	private GuiPopupForm gui;
	private Map<Integer, Method> actions;
	
	public FormKeyListener(GuiPopupForm g) {
		this.gui = g;
		this.actions = new TreeMap<Integer, Method>();
		this.loadActions();
	}
	
	private void loadActions() {
		try {
			this.actions.put(
					KeyEvent.VK_BACK_SPACE, 
					this.gui.getClass().getMethod("removeChar", (Class[]) null));
		} catch (Exception e) {
			return;
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {
		boolean isChar = e.getKeyChar() != KeyEvent.CHAR_UNDEFINED;
		Class[] methodArgs = {KeyEvent.class};
		Object[] args = {e};
		
		try {
			this.getClass().getMethod(isChar + "Char", methodArgs).invoke(this, args);
		} catch (Exception ex) {
			return;
		}
		
		this.gui.repaint();
	}
	
	public void trueChar(KeyEvent e) {
		this.gui.addChar(e.getKeyChar());
		this.falseChar(e);
	}
	
	public void falseChar(KeyEvent e) {
		try {
			this.actions.get(e.getKeyCode()).invoke(this.gui, (Object[]) null);
		} catch (Exception ex) {
			return;
		}
	}
}