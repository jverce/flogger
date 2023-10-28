package app.gui.managers;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioManager {
	public static final String MENU = "MenuMusic.wav";
	public static final String MENU_ITEM = "MenuItemSound.wav";
	public static final String GAMEBOARD_MUSIC = "GameBoardMusic.wav";
	
	private static boolean soundOn = true;
	private static Clip currentSound;
	private static Clip currentTheme;
	
	public static void setEnabled(boolean enabled) {
		soundOn = enabled;
	}
	
	public static void playAudio(String clipId) {
		try {
			currentSound.close();
		} catch (NullPointerException e) {}
		
		try {
			currentSound = loadSound(soundOn + clipId);
			currentSound.start();
		} catch (NullPointerException e) {}
	}
	
	public static void playMusic(String clipId) {
		try {
			currentTheme.close();
		} catch (NullPointerException e) {}
		
		try {
			currentTheme = loadSound(soundOn + clipId);
			currentTheme.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (NullPointerException e) {}
	}
	
	public static void playMusic() {
		try {
			currentTheme.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (NullPointerException e) {}
	}
	
	public static void stop() {
		try {
			currentTheme.stop();
			currentTheme.setFramePosition(0);
		} catch (NullPointerException e) {}
	}
	
	private static Clip loadSound(String clipId) {
		try {			
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(clipId));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			Clip clip = (Clip) AudioSystem.getLine(info);
			
			clip.open(ais);
			
			return clip;
		} catch (UnsupportedAudioFileException e) {
		} catch (IOException e) {
		} catch (LineUnavailableException e) {}
		
		return null;
	}
}
