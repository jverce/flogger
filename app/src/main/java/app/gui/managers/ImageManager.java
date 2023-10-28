package app.gui.managers;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class ImageManager {
	private static Map <String, Image> imageMap = new TreeMap<String, Image>();
	
	public static Image getImage(String imgFilename) {
		if (imageMap.get(imgFilename) == null) {
			imageMap.put(imgFilename, loadImage(imgFilename));
		}
		
		return imageMap.get(imgFilename);
	}
	
	private static Image loadImage(String imgFilename) {
		try {
			return ImageIO.read(new File(imgFilename));
		} catch (IOException e) {
			return Toolkit.getDefaultToolkit().createImage(imgFilename);
		}
	}
}