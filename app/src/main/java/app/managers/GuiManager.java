package app.managers;


import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;

import app.gui.Gui;
import app.gui.GuiGameBoard;
import app.gui.GuiMultiGameBoard;
import app.gui.GuiPopupForm;
import app.gui.Window;
import app.gui.dinamicobjects.FakeFlogger;
import app.gui.dinamicobjects.Flogger;
import app.gui.dinamicobjects.IPaintable;
import app.gui.factories.GuiFactory;
import app.gui.managers.AudioManager;

public class GuiManager {
	private MainManager mainManager;
	private Stack<Gui> guiStack;
	private Gui currentGui;
	private GuiFactory guiFactory;
	
	public GuiManager(MainManager m) {
		JFrame f = new Window();
		this.mainManager = m;
		this.guiStack = new Stack<Gui>();
		this.guiFactory = GuiFactory.getInstance(f, this);
		
		this.currentGui = this.guiFactory.getMainMenu();
		this.currentGui.activate();
		AudioManager.playMusic(AudioManager.MENU);
	}
	
	private void changeGui(Gui g) {
		this.guiStack.push(this.currentGui);
		this.currentGui.deactivate();
		
		this.currentGui = g;
		this.currentGui.activate();
	}
	
	public void mainMenu() {
		AudioManager.stop();
		AudioManager.playMusic(AudioManager.MENU);
		
		this.mainManager.endGame();
		this.currentGui.deactivate();
		this.guiStack.clear();
		
		this.changeGui(this.guiFactory.getMainMenu());
	}
	
	public void playAction() {
		this.changeGui(this.guiFactory.getPlayMenu());
	}
	
	public void optionsAction() {
		this.changeGui(this.guiFactory.getOptionsMenu());
	}
	
	public void difficultyAction() {
		this.changeGui(this.guiFactory.getDifficultyMenu());
	}
	
	public void soundAction() {
		this.changeGui(this.guiFactory.getSoundMenu());
	}
	
	public void easyAction() {
		this.guiFactory.easyDifficulty();		
		this.cancelAction();
	}
	
	public void mediumAction() {
		this.guiFactory.mediumDifficulty();
		this.cancelAction();
	}
	
	public void hardAction() {
		this.guiFactory.hardDifficulty();
		this.cancelAction();
	}
	
	public void soundOnAction() {
		AudioManager.setEnabled(true);
		AudioManager.playMusic();
		this.cancelAction();
	}
	
	public void soundOffAction() {
		AudioManager.setEnabled(false);
		AudioManager.stop();
		this.cancelAction();
	}
	
	public void aboutAction() {
		this.changeGui(this.guiFactory.getAboutGui());
	}
	
	public void singleAction() {
		Flogger f = this.mainManager.singleGame();
		this.changeGui(this.guiFactory.getGameBoard(f));
	}
	
	public void multiHostGame(Flogger f) {
		this.changeGui(this.guiFactory.getHostGameBoard(f));
	}
	
	public void multiGuestGame(Flogger f) {
		this.changeGui(this.guiFactory.getGuestGameBoard(f));
	}
	
	public void multiAction() {
		this.changeGui(this.guiFactory.getMultiMenu());
	}
	
	public void waitAction() {		
		this.mainManager.waitConnection();
		this.changeGui(this.guiFactory.getWaitGui());
	}
	
	public void cancelWaitAction() {
		this.mainManager.cancelWait();
		this.cancelAction();
	}
	
	public void connectAction() {
		this.changeGui(this.guiFactory.getConnectGui());
	}
	
	public void okConnectAction() {
		String address = ((GuiPopupForm) this.currentGui).getText();
		this.mainManager.connect(address);
	}
	
	public void okOptionsAction() {}
	
	public void cancelAction() {
		this.currentGui.deactivate();		
		
		this.currentGui = this.guiStack.pop();
		this.currentGui.activate();
	}
	
	public void timeFinished() {
		this.changeGui(this.guiFactory.getGameOverMessage());
	}
	
	public List<IPaintable> getFallingObjects() {
		return ((GuiGameBoard) this.currentGui).getFallingObjects();
	}
	
	public List<IPaintable> getRemoteFallingObjects() {
		return this.mainManager.getRemoteFallingObjects();
	}
	
	public void sendFlogger(Flogger f) {
		this.mainManager.sendFlogger(f);
	}
	
	public void setFlogger(FakeFlogger f) {
		((GuiMultiGameBoard) this.currentGui).setFlogger(f);
	}
	
	public void pauseAction() {
		this.changeGui(this.guiFactory.getPauseMenu());
	}
	
	public void quitAction() {
		this.mainManager.endGame();
		System.exit(0);
	}
}