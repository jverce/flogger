package app.gui.factories;

import javax.swing.JFrame;

import app.gui.Gui;
import app.gui.GuiMenu;
import app.gui.GuiMultiGameBoard;
import app.gui.GuiPopupForm;
import app.gui.GuiPopupMenu;
import app.gui.GuiPopupMessage;
import app.gui.GuiSingleGameBoard;
import app.gui.dinamicobjects.Flogger;
import app.gui.logic.GameLevel;
import app.managers.GuiManager;

public class GuiFactory {
	private static GuiFactory instance;
	private JFrame frame;
	private GuiManager guiManager;
	private ButtonFactory buttonFactory;
	private GameLevel level;
	
	protected GuiFactory(JFrame f, GuiManager gm) {
		this.frame = f;
		this.guiManager = gm;
		this.buttonFactory = ButtonFactory.getInstance(gm);
		this.level = new GameLevel();
	}
	
	public static GuiFactory getInstance(JFrame f, GuiManager gm) {
		if (instance == null) {
			instance = new GuiFactory(f, gm);
		}
		
		return instance;
	}
	
	public Gui getMainMenu() {
		GuiMenu g = new GuiMenu(this.frame);
		g.addButton(this.buttonFactory.getButton(ButtonFactory.PLAY, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.OPTIONS, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.ABOUT, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.QUIT, g));
		
		return g;
	}
	
	public Gui getPlayMenu() {
		GuiMenu g = new GuiMenu(this.frame);
		g.addButton(this.buttonFactory.getButton(ButtonFactory.SINGLE, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.MULTI, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.CANCEL, g));
		
		return g;
	}
	
	public Gui getMultiMenu() {
		GuiMenu g = new GuiMenu(this.frame);
		g.addButton(this.buttonFactory.getButton(ButtonFactory.WAIT, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.CONNECT, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.CANCEL, g));
		
		return g;
	}
	
	public Gui getWaitGui() {
		GuiPopupMessage g = 
			new GuiPopupMessage(this.frame, this.guiManager, "WaitMessage.png", "cancelWaitAction");
		
		return g;
	}
	
	public Gui getConnectGui() {
		GuiPopupForm g = new GuiPopupForm(this.frame, this.guiManager, "ConnectForm.png");
		g.addButton(this.buttonFactory.getButton(ButtonFactory.OK_CONNECT, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.CANCEL, g));
		
		return g;
	}
	
	public Gui getOptionsMenu() {
		GuiMenu g = new GuiMenu(this.frame);
		g.addButton(this.buttonFactory.getButton(ButtonFactory.DIFFICULTY, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.SOUND, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.CANCEL, g));
		
		return g;
	}
	
	public Gui getDifficultyMenu() {
		GuiMenu g = new GuiMenu(this.frame);
		g.addButton(this.buttonFactory.getButton(ButtonFactory.EASY, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.MEDIUM, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.HARD, g));
		
		return g;
	}
	
	public Gui getSoundMenu() {
		GuiMenu g = new GuiMenu(this.frame);
		g.addButton(this.buttonFactory.getButton(ButtonFactory.ON, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.OFF, g));
		
		return g;
	}
	
	public Gui getAboutGui() {
		GuiPopupMessage g = 
			new GuiPopupMessage(this.frame, this.guiManager, "AboutMessage.png", "cancelAction");
		
		return g;
	}
	
	public Gui getGameBoard(Flogger f) {
		GuiSingleGameBoard g = 
			new GuiSingleGameBoard(this.frame, this.guiManager, f, this.level);
		
		return g;
	}
	
	public Gui getGuestGameBoard(Flogger f) {
		GuiMultiGameBoard g = 
			new GuiMultiGameBoard(this.frame, this.guiManager, f, false);
		
		return g;
	}
	
	public Gui getHostGameBoard(Flogger f) {
		GuiMultiGameBoard g = 
			new GuiMultiGameBoard(this.frame, this.guiManager, f, true);
		
		return g;
	}
	
	public Gui getPauseMenu() {
		GuiPopupMenu g = new GuiPopupMenu(this.frame, this.guiManager, "FormBackground.png");
		g.addButton(this.buttonFactory.getButton(ButtonFactory.RESUME, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.BACK, g));
		g.addButton(this.buttonFactory.getButton(ButtonFactory.QUIT, g));
		
		return g;
	}
	
	public Gui getGameOverMessage() {
		GuiPopupMessage g = 
			new GuiPopupMessage(this.frame, this.guiManager, "GameOver.png", "mainMenu");
		
		return g;
	}
	
	public void easyDifficulty() {
		this.level.setLevel(GameLevel.EASY);
	}
	
	public void mediumDifficulty() {
		this.level.setLevel(GameLevel.MEDIUM);
	}
	
	public void hardDifficulty() {
		this.level.setLevel(GameLevel.HARD);
	}
}