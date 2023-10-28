package app.gui.dinamicobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import app.gui.Gui;
import app.managers.GuiManager;

public class Timer implements Runnable, IPaintable {
	private GuiManager guiManager;
	private double totalTime;
	private Gui gui;
	private Thread thread;
	private boolean isPaused;
	
	public Timer(GuiManager gm, Gui g, int time) {
		this.guiManager = gm;
		this.gui = g;
		this.totalTime = time;
		this.thread = new Thread(this);
		
		this.isPaused = false;
		this.thread.start();
	}
	
	public void run() {
		while (this.totalTime > 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			
			this.totalTime -= 0.1;
		}
		
		this.isPaused = true;
		this.guiManager.timeFinished();
	}

	public Rectangle getRectangle() {
		return null;
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font("Dialog", Font.BOLD, 20));
		g.setColor(Color.green.darker().darker().darker());
		g.drawString(this.toTime(this.totalTime), this.gui.getWidth() - 130, this.gui.getHeight() - 30);
	}
	
	private String toTime(double time) {
		int min = (int) time / 60;
		
		time %= 60;
		int sec = (int) time;
		
		time %= 1;
		int msec = (int) (time * 10);
		
		return (
				Integer.toString(min) + 
				" : " + Integer.toString(sec) 
				+ " : " + Integer.toString(msec));
	}
	
	public void paint(Graphics g, int itemNumber) {}
	
	public void pause() {
		try {
			this.getClass()
			.getMethod(this.isPaused + "Paused", (Class[]) null)
			.invoke(this, (Object[]) null);
		} catch (Exception e) {}
	}
	
	public void truePaused() {}
	
	public void falsePaused() {
		this.isPaused = true;
		this.thread.suspend();		
	}
	
	public void resume() {
		this.isPaused = false;
		this.thread.resume();
	}
}