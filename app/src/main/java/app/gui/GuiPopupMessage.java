package app.gui;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import app.gui.key.MessageKeyListener;
import app.managers.GuiManager;

public class GuiPopupMessage extends Gui {
	private static final long serialVersionUID = -7139338596692127291L;
	protected Image image;
	private KeyListener keyListener;
	
	public GuiPopupMessage(JFrame f, GuiManager gm, String imgFileName, String methodName) {
		super(f);
		this.keyListener = new MessageKeyListener(gm, methodName);
		this.window.addKeyListener(this.keyListener);
		this.loadImage(imgFileName);
	}
	
	private void loadImage(String imgFileName) {
		try {
			this.image = ImageIO.read(new File(imgFileName));
		} catch (IOException e) {
			this.image = Toolkit.getDefaultToolkit().createImage(imgFileName);
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(
				this.image,
				(this.window.getWidth() - this.image.getWidth(null)) / 2,
				(this.window.getHeight() - this.image.getHeight(null)) / 2,
				this.window);
	}
	
	public void deactivate() {
		super.deactivate();
		this.window.removeKeyListener(this.keyListener);
	}
}