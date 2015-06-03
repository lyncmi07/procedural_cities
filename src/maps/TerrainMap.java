package maps;

import imageprocessing.AddImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;

/**
 * A type of map that holds data on the height of the terrain
 * @author michael
 *
 */
public class TerrainMap extends IntegerMap{

	/**
	 * Creates a map indicating terrain with black being low and white being high
	 * @param xOffset The x offset of the map
	 * @param yOffset The y offset of the map
	 * @param zOffset The z offset (time) of the map	(should not be used)
	 */
	public TerrainMap(int xOffset, int yOffset, float zOffset) {
		//super(400, 400, 8, xOffset, yOffset, zOffset);
		//super(800, 800, 8, xOffset, yOffset, zOffset);
		// TODO Auto-generated constructor stub
		
		super();
		
		BufferedImage mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset, 8, zOffset);
		
		BufferedImage croppedMap = new BufferedImage(256, 256, mapImage.getType());
		Graphics g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[4] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset-1, yOffset-1, 8, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[0] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset-1, 8, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[1] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset+1, yOffset-1, 8, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[2] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset-1, yOffset, 8, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[3] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset+1, yOffset, 8, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[5] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset-1, yOffset+1, 8, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[6] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset+1, 8, zOffset);
		croppedMap = new BufferedImage(256, 256, mapImage.getType());
		g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		generatedMaps[7] = croppedMap;
		
		mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset+1, yOffset+1, 8, zOffset);
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
	}
	
	public void moveMapLeft()
	{
		/*
		 * swap images:
		 * ? 0 1
		 * ? 3 4
		 * ? 6 7
		 */
	}
	public void moveMapRight()
	{
		/*
		 * swap images:
		 * 1 2 ?
		 * 4 5 ?
		 * 7 8 ?
		 */
	}
	public void moveMapUp()
	{
		/*
		 * swap images:
		 * ? ? ?
		 * 0 1 2
		 * 3 4 5
		 */
	}
	public void moveMapDown()
	{
		/*
		 * swap images:
		 * 3 4 5
		 * 6 7 8
		 * ? ? ?
		 */
	}

}
