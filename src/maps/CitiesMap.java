package maps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;
import imageprocessing.AddImage;
import imageprocessing.BooleanOperators;
import imageprocessing.Filters;

/**
 * A type of map that holds data on the whether an area of land is a city or not
 * @author michael
 *
 */
public class CitiesMap extends UniquelyIdentifiableBinaryMap{
	
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
		super();
		
		BufferedImage mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset, 7, zOffset);
		
		BufferedImage croppedMap = new BufferedImage(256, 256, mapImage.getType());
		Graphics g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[4] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset-2, yOffset-2, 7, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[0] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset-2, 7, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[1] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset+2, yOffset-2, 7, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[2] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset-2, yOffset, 7, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[3] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset+2, yOffset, 7, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[5] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset-2, yOffset+2, 7, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[6] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset+2, 7, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[7] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset+2, yOffset+2, 7, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[8] = croppedMap;
		
		//map = croppedMap;
		stitchedMap = AddImage.tileX(
						AddImage.tileX(
							generatedMaps[0], 
							generatedMaps[1]),
							generatedMaps[2]);
		stitchedMap = AddImage.tileY(
						stitchedMap,
						AddImage.tileX(
							AddImage.tileX(
								generatedMaps[3],
								generatedMaps[4]),
							generatedMaps[5]));
		stitchedMap = AddImage.tileY(
						stitchedMap,
						AddImage.tileX(
							AddImage.tileX(
								generatedMaps[6],
								generatedMaps[7]),
							generatedMaps[8]));
		
		stitchedMap = Filters.binaryMapping(BooleanOperators.and(landSeaMap.getMap(), stitchedMap), cutoffValue);
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
