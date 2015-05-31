package maps;

import imageprocessing.BooleanOperators;
import imageprocessing.Filters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;

/**
 * A type of map that holds data on the whether an area of a city is industrial, commercial or residential
 * @author michael
 *
 */
public class ZoningMap extends Map{
	
	/**
	 * Creates a map of industrial as red, commercial as green and residential as blue
	 * @param xOffset The x offset of the map
	 * @param yOffset The y offset of the map
	 * @param zOffset The z offset (time) of the map
	 * @param citiesMap	map to be based off
	 * @param cutoffValue1	raise for more industry and less commercial
	 * @param cutoffValue2	raise for more commercial and less residential
	 */
	public ZoningMap(int xOffset, int yOffset, float zOffset, CitiesMap citiesMap, int cutoffValue1, int cutoffValue2)
	{
		BufferedImage fullZoningMap = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset, 4, zOffset);
		
		BufferedImage croppedFullZoningMap = new BufferedImage(256, 256, fullZoningMap.getType());
		Graphics g = croppedFullZoningMap.getGraphics();
		g.drawImage(fullZoningMap, 0, 0, 256, 256, 72, 72, 328, 328, null);
		
		map = BooleanOperators.and(citiesMap.getMap(), Filters.tripleMapping(croppedFullZoningMap, cutoffValue1, cutoffValue2));
	
	}
}
