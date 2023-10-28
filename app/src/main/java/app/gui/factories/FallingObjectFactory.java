package app.gui.factories;
import app.gui.dinamicobjects.FallingObject;
import app.gui.managers.ImageManager;

public class FallingObjectFactory {
	private static final int NUM_OF_FRUITS = 18;
	private static final double NUM_MAX_XSPEED = 15;
	
	public static FallingObject getFallingObject(int screenWidth) {
		int imageNumber = (int) Math.floor(Math.random() * NUM_OF_FRUITS + 1);
		String imgFilename = "Fruit" + imageNumber + ".png";
		
		return new FallingObject(
				imgFilename,
				ImageManager.getImage(imgFilename),
				(int) Math.floor(Math.random() * screenWidth),
				(int) Math.floor(Math.random() * NUM_MAX_XSPEED * Math.signum(Math.random() - 0.5)),
				screenWidth); 
	}
}