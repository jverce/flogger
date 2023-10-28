package app.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import app.gui.key.MenuKeyListener;
import app.gui.other.GraphicButton;
import app.gui.other.Menu;

public class GuiMenu extends Gui {
	private static final long serialVersionUID = 1831318077829808946L;
	private Image titleImg;
	private Menu menu;
	private KeyListener keyListener;

	public GuiMenu(JFrame f) {
		super(f);
		this.menu = new Menu(this);
		this.keyListener = new MenuKeyListener(this.menu, this);
		this.loadImages();
		this.repaint();
	}

	private void loadImages() {
		try {
			System.out.println("Opening file: " + System.getProperty("user.dir") + "/Flogger.png");
			this.titleImg = ImageIO.read(new File("Flogger.png"));
		} catch (IOException e) {
			System.err.println("Error loading images: " + e.getMessage());
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.drawImage(
				this.titleImg,
				(this.getWidth() - this.titleImg.getWidth(null)) / 2,
				25,
				null);

		this.menu.paint(g);
	}

	public void addButton(GraphicButton b) {
		this.menu.addItem(b);
	}

	public void activate() {
		super.activate();
		this.window.addKeyListener(this.keyListener);
	}

	public void deactivate() {
		super.deactivate();
		this.window.removeKeyListener(this.keyListener);
	}
}
