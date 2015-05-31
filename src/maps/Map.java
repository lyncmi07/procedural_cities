package maps;

import java.awt.image.BufferedImage;
import proceduralgeneration.GeneratedImages;
import java.awt.Graphics;

/**
 * A map of data to be used by the program
 * @author michael
 *
 */
public abstract class Map {
	protected BufferedImage map;
	
	
	//protected abstract BufferedImage generateMap(int width, int height, int zoomLevel, int xOffset, int yOffset, float zOffset);
	
	/**
	 * 
	 * @return returns a BufferedImage representation of the map
	 */
	public BufferedImage getMap()
	{
		return map;
	}
}
