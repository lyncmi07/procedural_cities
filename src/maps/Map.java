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
	//protected BufferedImage map;
	protected BufferedImage[] generatedMaps;
	protected BufferedImage stitchedMap;
	
	protected int xPosition;
	protected int yPosition;
	protected float zPosition;
	
	
	//protected abstract BufferedImage generateMap(int width, int height, int zoomLevel, int xOffset, int yOffset, float zOffset);
	
	public Map()
	{
		generatedMaps = new BufferedImage[9];
	}
	
	/**
	 * 
	 * @return returns a BufferedImage representation of the map
	 */
	public BufferedImage getMap()
	{
		return stitchedMap;
	}
	
	public abstract void moveMapLeft();
	public abstract void moveMapRight();
	public abstract void moveMapUp();
	public abstract void moveMapDown();
}
