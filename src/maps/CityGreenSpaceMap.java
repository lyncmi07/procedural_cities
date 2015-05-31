package maps;

import imageprocessing.BooleanOperators;
import imageprocessing.Filters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;

/**
 * A type of map that holds data on the whether an area of a city is a green space or not
 * @author michael
 *
 */
public class CityGreenSpaceMap extends Map{
	
	/**
	 * Creates a map of city green spaces (parks) as white
	 * @param xOffset The x offset of the map
	 * @param yOffset The y offset of the map
	 * @param zOffset The z offset (time) of the map
	 * @param citiesMap	map to be based off
	 * @param cutoffValue	green spaces size
	 */
	public CityGreenSpaceMap(int xOffset, int yOffset, float zOffset, CitiesMap citiesMap, int cutoffValue)
	{
		BufferedImage fullCityGreenMap = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset, 5, zOffset);
		
		BufferedImage croppedFullCityGreenMap = new BufferedImage(256, 256, fullCityGreenMap.getType());
		Graphics g = croppedFullCityGreenMap.getGraphics();
		g.drawImage(fullCityGreenMap, 0, 0, 256, 256, 72, 72, 328, 328, null);
		
		map = Filters.binaryMapping(BooleanOperators.and(citiesMap.getMap(), croppedFullCityGreenMap), cutoffValue);
	}
}
