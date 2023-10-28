package app.gui.factories;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import app.gui.Gui;
import app.gui.other.GraphicButton;
import app.managers.GuiManager;

public class ButtonFactory {
	public static final Integer PLAY = 0;
	public static final Integer OPTIONS = 1;
	public static final Integer ABOUT = 2;
	public static final Integer QUIT = 3;
	public static final Integer SINGLE = 4;
	public static final Integer MULTI = 5;
	public static final Integer WAIT = 6;
	public static final Integer CONNECT = 7;
	public static final Integer OK_CONNECT = 8;
	public static final Integer CANCEL = 9;
	public static final Integer RESUME = 10;
	public static final Integer BACK = 11;
	public static final Integer DIFFICULTY = 12;
	public static final Integer SOUND = 13;
	public static final Integer EASY = 14;
	public static final Integer MEDIUM = 15;
	public static final Integer HARD = 16;
	public static final Integer ON = 17;
	public static final Integer OFF = 18;
	public static final Integer OK_OPTIONS = 19;
	
	private static ButtonFactory instance;	
	private Map<Integer, String> imgFilename;
	private Map<Integer, Method> actionMap;
	private GuiManager guiManager;
	
	private ButtonFactory(GuiManager gm) {
		this.guiManager = gm;
		this.imgFilename = new TreeMap<Integer, String>();
		this.actionMap = new TreeMap<Integer, Method>();
		
		this.initFilenames();
		this.initActions();
	}
	
	public static ButtonFactory getInstance(GuiManager gm) {
		if (instance == null) {
			instance = new ButtonFactory(gm);
		}
		
		return instance;
	}
	
	private void initFilenames() {
		imgFilename.put(PLAY, "PlayButton.png");
		imgFilename.put(OPTIONS, "OptionsButton.png");
		imgFilename.put(ABOUT, "AboutButton.png");
		imgFilename.put(QUIT, "QuitButton.png");
		imgFilename.put(SINGLE, "SingleButton.png");
		imgFilename.put(MULTI, "MultiButton.png");
		imgFilename.put(WAIT, "WaitButton.png");
		imgFilename.put(CONNECT, "ConnectButton.png");
		imgFilename.put(OK_CONNECT, "OkButton.png");
		imgFilename.put(OK_OPTIONS, "OkButton.png");
		imgFilename.put(CANCEL, "CancelButton.png");
		imgFilename.put(RESUME, "ResumeButton.png");
		imgFilename.put(BACK, "BackButton.png");
		imgFilename.put(DIFFICULTY, "DifficultyButton.png");
		imgFilename.put(SOUND, "SoundButton.png");
		imgFilename.put(EASY, "EasyButton.png");
		imgFilename.put(MEDIUM, "MediumButton.png");
		imgFilename.put(HARD, "HardButton.png");
		imgFilename.put(ON, "OnButton.png");
		imgFilename.put(OFF, "OffButton.png");
	}
	
	private void initActions() {
		try {
			actionMap.put(PLAY, guiManager.getClass().getMethod("playAction", (Class[]) null));
			actionMap.put(OPTIONS, guiManager.getClass().getMethod("optionsAction", (Class[]) null));
			actionMap.put(ABOUT, guiManager.getClass().getMethod("aboutAction", (Class[]) null));
			actionMap.put(QUIT, guiManager.getClass().getMethod("quitAction", (Class[]) null));
			actionMap.put(SINGLE, guiManager.getClass().getMethod("singleAction", (Class[]) null));
			actionMap.put(MULTI, guiManager.getClass().getMethod("multiAction", (Class[]) null));
			actionMap.put(WAIT, guiManager.getClass().getMethod("waitAction", (Class[]) null));
			actionMap.put(CONNECT, guiManager.getClass().getMethod("connectAction", (Class[]) null));
			actionMap.put(OK_CONNECT, guiManager.getClass().getMethod("okConnectAction", (Class[]) null));
			actionMap.put(OK_OPTIONS, guiManager.getClass().getMethod("okOptionsAction", (Class[]) null));
			actionMap.put(CANCEL, guiManager.getClass().getMethod("cancelAction", (Class[]) null));
			actionMap.put(RESUME, guiManager.getClass().getMethod("cancelAction", (Class[]) null));
			actionMap.put(BACK, guiManager.getClass().getMethod("mainMenu", (Class[]) null));
			actionMap.put(DIFFICULTY, guiManager.getClass().getMethod("difficultyAction", (Class[]) null));
			actionMap.put(SOUND, guiManager.getClass().getMethod("soundAction", (Class[]) null));
			actionMap.put(EASY, guiManager.getClass().getMethod("easyAction", (Class[]) null));
			actionMap.put(MEDIUM, guiManager.getClass().getMethod("mediumAction", (Class[]) null));			
			actionMap.put(HARD, guiManager.getClass().getMethod("hardAction", (Class[]) null));
			actionMap.put(ON, guiManager.getClass().getMethod("soundOnAction", (Class[]) null));
			actionMap.put(OFF, guiManager.getClass().getMethod("soundOffAction", (Class[]) null));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public GraphicButton getButton(Integer type, Gui g) {
		return new GraphicButton(
				g, 
				this.guiManager, 
				this.actionMap.get(type),
				this.imgFilename.get(type));
	}
}