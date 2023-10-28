package app.gui;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import app.gui.key.MenuKeyListener;
import app.gui.other.GraphicButton;
import app.gui.other.Menu;
import app.managers.GuiManager;

public class GuiPopupMenu extends GuiPopupMessage {
	private static final long serialVersionUID = 7035714631097571242L;
	protected Menu menu;
	private KeyListener keyListener;
	
	public GuiPopupMenu(JFrame f, GuiManager gm, String imgFileName) {
		super(f, gm, imgFileName, "");		
		this.menu = new Menu(this);
		this.keyListener = new MenuKeyListener(this.menu, this);
		super.deactivate();		
	}
	
	public void activate() {
		super.activate();		
		this.window.addKeyListener(this.keyListener);
	}
	
	public void deactivate() {
		super.deactivate();
		this.window.removeKeyListener(this.keyListener);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.menu.paint(g);
	}
	
	public void addButton(GraphicButton b) {
		this.menu.addItem(b);
	}
}