package maps;

import imageprocessing.BooleanOperators;
import imageprocessing.Filters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;

/**
 * A type of map that holds data on the whether an area of a city is low, medium or high wealth
 * @author michael
 *
 */
public class WealthMap extends Map{
	
	/**
	 * Creates a map of low wealth as red, medium wealth as green and high wealth as blue
	 * @param xOffset The x offset of the map
	 * @param yOffset The y offset of the map
	 * @param zOffset The z offset (time) of the map
	 * @param citiesMap	map to be based off
	 * @param cutoffValue1	raise for more low wealth and less medium wealth
	 * @param cutoffValue2 raise for more medium wealth and less high wealth
	 */
	public WealthMap(int xOffset, int yOffset, float zOffset, CitiesMap citiesMap, int cutoffValue1, int cutoffValue2)
	{
		BufferedImage fullWealthMap = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset, 4, zOffset);
		
		BufferedImage croppedFullWealthMap = new BufferedImage(256, 256, fullWealthMap.getType());
		Graphics g = croppedFullWealthMap.getGraphics();
		g.drawImage(fullWealthMap, 0, 0, 256, 256, 72, 72, 328, 328, null);
		
		//map = Filters.tripleMapping(BooleanOperators.and(citiesMap.getMap(), croppedFullWealthMap), cutoffValue1, cutoffValue2);
		map = BooleanOperators.and(citiesMap.getMap(), Filters.tripleMapping(croppedFullWealthMap, cutoffValue1, cutoffValue2));
	
	}
}
