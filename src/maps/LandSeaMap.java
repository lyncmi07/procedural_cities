package maps;

import imageprocessing.Filters;

import java.awt.image.BufferedImage;

/**
 * A type of map that holds data on the whether an area of land is above or below the water
 * @author michael
 *
 */
public class LandSeaMap extends Map{
	
	/**
	 * Creates a map of land as white and sea as black
	 * @param terrainMap	map to be based off
	 * @param cutoffValue	sea level
	 */
	public LandSeaMap(TerrainMap terrainMap, int cutoffValue) {
		map = Filters.binaryMapping(terrainMap.getMap(), cutoffValue);
	}
}
