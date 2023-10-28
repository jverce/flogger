package app.gui;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Gui extends JPanel {
	private static final long serialVersionUID = 6732185838912765336L;
	protected JFrame window;
	
	public Gui(JFrame f) {
		this.window = f;
	}
	
	public void activate() {
		this.window.setContentPane(this);		
		this.window.setVisible(true);	
		this.repaint();
	}
	
	public void deactivate() {}
}