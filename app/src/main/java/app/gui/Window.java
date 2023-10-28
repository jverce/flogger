package app.gui;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = -8255319694373975038L;
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	public int width = 1000;
	public int height = 800;

	public Window() {
		this.setTitle("***  F � L � O � G � G � E � R  ***");
		this.setBounds(
				(this.screenWidth - this.width) / 2,
				(this.screenHeight - this.height) / 2,
				this.width,
				this.height);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		this.setVisible(true);
		this.repaint();
		return true;
	}
}
