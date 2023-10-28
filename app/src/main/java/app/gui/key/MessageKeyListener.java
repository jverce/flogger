package app.gui.key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import app.managers.GuiManager;

public class MessageKeyListener implements KeyListener {
	private GuiManager guiManager;
	private Method action;
	
	public MessageKeyListener(GuiManager gm, String methodName) {
		this.guiManager = gm;
		try {
			this.action = gm.getClass().getMethod(methodName, (Class[]) null);
		} catch (NoSuchMethodException e) {}
	}
	
	
	public void keyPressed(KeyEvent e) {
		try {
			this.action.invoke(this.guiManager, (Object[]) null);
		} catch (IllegalArgumentException e1) {
		} catch (IllegalAccessException e1) {
		} catch (InvocationTargetException e1) {}
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
}