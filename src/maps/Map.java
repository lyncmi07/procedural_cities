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
	BufferedImage map;
	
	/**
	 * 
	 * @param width the width of the map to be generated
	 * @param height the height of the map to be generated
	 * @param zoomLevel the number of iterations of the map
	 * @param xOffset the x offset of the map
	 * @param yOffset the y offset of the map
	 * @param zOffset the z offset (time) of the map
	 */
	public Map(int width, int height, int zoomLevel, int xOffset, int yOffset, float zOffset)
	{
		map = generateMap(width, height, zoomLevel,xOffset, yOffset, zOffset);
	}
	
	protected abstract BufferedImage generateMap(int width, int height, int zoomLevel, int xOffset, int yOffset, float zOffset);
	
	/**
	 * 
	 * @return returns a BufferedImage representation of the map
	 */
	public BufferedImage getMap()
	{
		return map;
	}
}
