package maps;

import imageprocessing.BooleanOperators;
import imageprocessing.Filters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;

/**
 * A type of map that holds data on the whether an area of a city has a unique attraction or not
 * @author michael
 *
 */
public class CityAttractionsMap extends Map{
	
	/**
	 * Creates a map of city attractions as white
	 * @param xOffset The x offset of the map
	 * @param yOffset The y offset of the map
	 * @param zOffset The z offset (time) of the map
	 * @param citiesMap	map to be based off
	 * @param cutoffValue	number of attractions (in arbitrary units)
	 */
	public CityAttractionsMap(int xOffset, int yOffset, float zOffset, CitiesMap citiesMap, int cutoffValue)
	{
		BufferedImage fullCityAttractionMap = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset, 3, zOffset);

		BufferedImage croppedFullCityAttractionMap = new BufferedImage(256, 256, fullCityAttractionMap.getType());
		Graphics g = croppedFullCityAttractionMap.getGraphics();
		g.drawImage(fullCityAttractionMap, 0, 0, 256, 256, 72, 72, 328, 328, null);
		
		map = Filters.binaryMapping(BooleanOperators.and(citiesMap.getMap(), croppedFullCityAttractionMap), cutoffValue);
	}
}
