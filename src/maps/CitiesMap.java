package maps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;
import imageprocessing.BooleanOperators;
import imageprocessing.Filters;

/**
 * A type of map that holds data on the whether an area of land is a city or not
 * @author michael
 *
 */
public class CitiesMap extends Map{
	
	/**
	 * Creates a map of cities as white
	 * @param xOffset The x offset of the map
	 * @param yOffset The y offset of the map
	 * @param zOffset The z offset (time) of the map
	 * @param landSeaMap	map to be based off
	 * @param cutoffValue	city sizes
	 */
	public CitiesMap(int xOffset, int yOffset, float zOffset, LandSeaMap landSeaMap, int cutoffValue)
	{
		BufferedImage fullCityMap = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset, 7, zOffset);
		
		BufferedImage croppedFullCityMap = new BufferedImage(256, 256, fullCityMap.getType());
		Graphics g = croppedFullCityMap.getGraphics();
		g.drawImage(fullCityMap, 0, 0, 256, 256, 72, 72, 328, 328, null);
		
		map = Filters.binaryMapping(BooleanOperators.and(landSeaMap.getMap(), croppedFullCityMap), cutoffValue);
	}
}
