package app.gui.other;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import app.gui.Gui;
import app.gui.dinamicobjects.IPaintable;

public class Selector implements IPaintable {
	private int position;
	private Image image;
	private Menu menu;
	private Gui gui;
	
	public Selector(Menu menu, Gui g) {
		this.position = 0;
		this.menu = menu;
		this.gui = g;		
		this.loadImage();
	}
	
	private void loadImage() {
		try {
			this.image = ImageIO.read(new File("Selector.png"));
		} catch (IOException e) {
			this.image = Toolkit.getDefaultToolkit().createImage("Selector.png");
		}
	}
	
	public void paint(Graphics g) {
		this.paint(g, 0);
	}
	
	public void paint(Graphics g, int itemNumber) {
		g.drawImage(
				image, 
				this.gui.getWidth() / 2 - 100 - 75, 
				(this.position + itemNumber) * 60 + (this.gui.getHeight() / 2 - 115),
				this.gui);
	}
	
	public void inc() {
		this.position = (++this.position) % this.menu.size();
	}
	
	public void dec() {
		this.position = (--this.position + this.menu.size()) % this.menu.size();
	}
	
	public int value() {
		return this.position;
	}
	
	public Rectangle getRectangle() {
		return null;
	}
}