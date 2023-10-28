package app.gui.dinamicobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import app.gui.Gui;

public class FakeFlogger extends FakeItem implements IScorer {
	private static final long serialVersionUID = -4937643620221205112L;
	private int score;
	
	public FakeFlogger(String imgFilename, int x, int y, int score) {
		super(imgFilename, x, y);
		this.score = score;
	}
	
	public void score() {
		this.score += 50;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void paint(Graphics g, Gui gui) {
		super.paint(g);
		
		g.setColor(Color.orange);
		g.setFont(new Font("Dialog", Font.BOLD, 20));
		g.drawString(
				"SCORE: " + Integer.toString(this.getScore()),
				(gui.getWidth() / 2) + 30,
				gui.getHeight() - 30);
	}
}