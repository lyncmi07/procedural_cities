package maps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;

/**
 * A type of map that holds data on the height of the terrain
 * @author michael
 *
 */
public class TerrainMap extends Map{

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
		
		BufferedImage mapImage = GeneratedImages.generateCombinationTerrainImage(400, 400, xOffset, yOffset, 8, zOffset);
		
		BufferedImage croppedMap = new BufferedImage(256, 256, mapImage.getType());
		Graphics g = croppedMap.getGraphics();
		g.drawImage(mapImage, 0, 0, 256, 256, 72, 72, 328, 328, null);
		
		map = croppedMap;
	}

}
