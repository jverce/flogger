package app.gui.dinamicobjects;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import app.gui.managers.ImageManager;


public class FakeItem implements IPaintable, Serializable {
	private static final long serialVersionUID = 6173950012684942497L;
	private int xPosition;
	private int yPosition;
	private String imgFilename;
	
	public FakeItem(String imgFilename, int x, int y) {
		this.imgFilename = imgFilename;
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public Rectangle getRectangle() {
		Image image = ImageManager.getImage(this.imgFilename);
		
		return new Rectangle(
				this.xPosition,
				this.yPosition,
				image.getWidth(null),
				image.getHeight(null));
	}
	
	public void paint(Graphics g) {
		Image image = ImageManager.getImage(this.imgFilename);
		
		g.drawImage(
				image,
				this.xPosition,
				this.yPosition,
				null);
	}
	
	public void paint(Graphics g, int itemNumber) {}
}