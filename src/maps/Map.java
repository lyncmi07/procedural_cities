package maps;

import java.awt.image.BufferedImage;
import proceduralgeneration.GeneratedImages;
import java.awt.Graphics;

public abstract class Map {
	BufferedImage map;
	
	public Map(int width, int height, int zoomLevel, int xOffset, int yOffset, float zOffset)
	{
		map = generateMap(width, height, zoomLevel,xOffset, yOffset, zOffset);
	}
	
	protected abstract BufferedImage generateMap(int width, int height, int zoomLevel, int xOffset, int yOffset, float zOffset);
	
	public BufferedImage getMap()
	{
		return map;
	}
}
