package app.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import app.gui.key.FormKeyListener;
import app.managers.GuiManager;

public class GuiPopupForm extends GuiPopupMenu {
	private static final long serialVersionUID = -7139338596692127291L;
	private Image image;
	private KeyListener keyListener;
	private String text;
	
	public GuiPopupForm(JFrame f, GuiManager gm, String imgFileName) {
		super(f, gm, imgFileName);
		this.keyListener = new FormKeyListener(this);
		this.text = "";
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
		super.repaint();
		g.drawImage(
				this.image,
				(this.window.getWidth() - this.image.getWidth(null)) / 2,
				(this.window.getHeight() - this.image.getHeight(null)) / 2 - 50,
				this.window);
		
		Font f = g.getFont();
		g.setFont(new Font("Dialog", Font.BOLD, 24));
		g.setColor(Color.black);
		g.drawString(
				this.text, 
				(this.window.getWidth() - this.image.getWidth(null)) / 2 + 30, 
				(this.window.getHeight() - this.image.getHeight(null)) / 2 + 100);
		g.setFont(f);
		
		this.menu.paint(g, 2);
	}
	
	public void addChar(char c) {
		this.text += c;
		this.text = this.text.subSequence(0, 16).toString();
	}
	
	public void removeChar() {
		this.text = this.text.subSequence(0, this.text.length() - 2).toString();
	}
	
	public String getText() {
		return this.text.subSequence(0, this.text.length() - 1).toString();
	}
	
	public void deactivate() {
		super.deactivate();
		this.window.removeKeyListener(this.keyListener);
	}
}