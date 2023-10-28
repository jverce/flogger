package app.gui.dinamicobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import app.gui.Gui;

public class Flogger extends Observable implements IPaintable, IScorer {
	private static final long serialVersionUID = -8687790485178299684L;
	private Image image;
	private int xPosition;
	private int yPosition;
	private int xSpeed;
	private int ySpeed;
	private int score;
	private int multiOffset;
	private int leftLimit;
	private int rightLimit;
	private boolean isMultiPlayer;
	private boolean isHostMachine;
	private Gui gui;
	
	public Flogger() {
		this(0, 0);
	}
	
	public Flogger(int x, int y) {
		this(x, y, 20, 20);
	}
	
	public Flogger(boolean host) {
		this.score = 0;
		this.xSpeed = 20;
		this.ySpeed = 20;
		this.loadImage();
		this.isMultiPlayer = true;
		this.isHostMachine = host;
	}
	
	public Flogger(int x, int y, int xSpeed, int ySpeed) {
		this(x, y, xSpeed, ySpeed, false, false);
	}
	
	public Flogger(int x, int y, int xSpeed, int ySpeed, boolean multi, boolean host) {
		this.xPosition = x;
		this.yPosition = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.score = 0;
		this.loadImage();
		this.isMultiPlayer = multi;
		this.isHostMachine = host;
	}
	
	private void loadImage() {
		try {
			this.image = ImageIO.read(new File("cumbio.png"));
		} catch (IOException e) {
			this.image = Toolkit.getDefaultToolkit().createImage("cumbio.png");
		}
	}
	
	private void setMulti() {
		try {
			this.getClass()
			.getMethod(this.isMultiPlayer + "Multi", (Class[]) null)
			.invoke(this, (Object[]) null);
			
			this.getClass()
			.getMethod(this.isHostMachine + "Host", (Class[]) null)
			.invoke(this, (Object[]) null);
		} catch (Exception e) {
			return;
		}
	}
	
	public void trueMulti() {
		this.multiOffset = this.gui.getWidth() / 2;
	}
	
	public void falseMulti() {
		this.multiOffset = 0;
	}
	
	public void trueHost() {
		this.leftLimit = 0;
		this.rightLimit = this.multiOffset;
		
		this.xPosition = this.leftLimit;
		this.yPosition = this.gui.getHeight() - this.image.getHeight(null);
	}
	
	public void falseHost() {
		this.leftLimit = this.multiOffset;
		this.rightLimit = this.gui.getWidth();
		
		this.xPosition = this.leftLimit;
		this.yPosition = this.gui.getHeight() - this.image.getHeight(null);
	}
	
	public void setGui(Gui g) {
		this.gui = g;
		this.setMulti();
	}
	
	public void superUp() {
		this.yPosition = 0;
	}
	
	public void superDown() {
		this.yPosition = this.gui.getHeight() - this.image.getHeight(null);
	}
	
	public void superLeft() {
		this.xPosition = this.leftLimit;
	}
	
	public void superRight() {
		this.xPosition = this.rightLimit - this.image.getWidth(null);
	}
	
	public void moveUp() {
		this.yPosition = (int) Math.max(this.yPosition - this.ySpeed, 0);
	}
	
	public void moveDown() {
		this.yPosition = (int) Math.min(
				this.yPosition + this.ySpeed, 
				this.gui.getHeight() - this.image.getHeight(null));
	}
	
	public void moveLeft() {
		this.xPosition = (int) Math.max(this.xPosition - this.xSpeed, this.leftLimit);
	}
	
	public void moveRight() {
		this.xPosition = (int) Math.min(
				this.xPosition + this.xSpeed, 
				this.rightLimit - this.image.getWidth(null));
	}
	
	public void setSpeed(int speed) {
		this.xSpeed = this.ySpeed = speed;
	}
	
	public void checkCrashes() {
		this.setChanged();
		this.notifyObservers();
	}
	
	public void score() {
		this.score += 50;
		this.checkCrashes();
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void paint(Graphics g) {
		g.drawImage(
				this.image,
				this.xPosition,
				this.yPosition,
				null);
				
		g.setColor(Color.orange);
		g.setFont(new Font("Dialog", Font.BOLD, 20));
		g.drawString(
				"SCORE: " + Integer.toString(this.getScore()),
				this.leftLimit + 30, 
				this.gui.getHeight() - 30);
	}
	
	public void paint(Graphics g, int itemNumber) {}
	
	public synchronized Rectangle getRectangle() {
		return new Rectangle(
				this.xPosition, 
				this.yPosition, 
				this.image.getWidth(null), 
				this.image.getHeight(null));
	}
	
	public FakeFlogger toFakeFlogger() {
		return new FakeFlogger("cumbio.png", this.xPosition, this.yPosition, this.score);
	}
}