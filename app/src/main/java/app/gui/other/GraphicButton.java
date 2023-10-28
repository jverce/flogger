package app.gui.other;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;

import app.gui.Gui;
import app.managers.GuiManager;

public class GraphicButton implements ISelectionable {
	private Image image;
	private Method action;
	private Gui gui;
	private GuiManager guiManager;
	
	public GraphicButton(Gui g, GuiManager gm, Method action, String imgTitle) {
		this.gui = g;
		this.guiManager = gm;
		this.action = action;
		this.loadImage(imgTitle);
	}
	
	private void loadImage(String imgTitle) {
		try {
			this.image = ImageIO.read(new File(imgTitle));
		} catch (IOException e) {
			this.image = Toolkit.getDefaultToolkit().createImage(imgTitle);
		}
	}
	
	public void paint(Graphics g) {}
	
	public void paint(Graphics g, int itemNumber) {
		g.drawImage(
				image, 
				this.gui.getWidth() / 2 - 100, 
				itemNumber * (this.image.getHeight(null) + 10) + (this.gui.getHeight() / 2 - 115), 
				this.gui);
	}
	
	public void select() {
		try {
			this.action.invoke(this.guiManager, (Object[]) null); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Rectangle getRectangle() {
		return null;
	}
}