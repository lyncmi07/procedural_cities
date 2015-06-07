package maps;

import imageprocessing.AddImage;
import imageprocessing.Filters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;

/**
 * A type of map that holds data on the whether an area of land is above or below the water
 * @author michael
 *
 */
public class LandSeaMap extends BinaryMap{
	
	/**
	 * Creates a map of land as white and sea as black
	 * @param terrainMap	map to be based off
	 * @param cutoffValue	sea level
	 */
	public LandSeaMap(TerrainMap terrainMap, int cutoffValue) {
		stitchedMap = Filters.binaryMapping(terrainMap.getMap(), cutoffValue);
	}
	
	public void regenerate(TerrainMap terrainMap, int cutoffValue)
	{
		stitchedMap = Filters.binaryMapping(terrainMap.getMap(), cutoffValue);
	}

	@Override
	public void moveMapLeft() {
		// TODO Auto-generated method stub
	}

	@Override
	public void moveMapRight() {
		// TODO Auto-generated method stub
	}

	@Override
	public void moveMapUp() {
		// TODO Auto-generated method stub
	}

	@Override
	public void moveMapDown() {
		// TODO Auto-generated method stub
	}
}
