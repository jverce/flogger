package app.gui.dinamicobjects;
import java.awt.Graphics;
import java.awt.Rectangle;

public interface IPaintable {
	public void paint(Graphics g);
	public void paint(Graphics g, int itemNumber);
	public Rectangle getRectangle();
}