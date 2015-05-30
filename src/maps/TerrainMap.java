package maps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;

public class TerrainMap extends Map{

	public TerrainMap(int xOffset, int yOffset, float zOffset) {
		super(400, 400, 8, xOffset, yOffset, zOffset);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected BufferedImage generateMap(int width, int height, int zoomLevel,
			int xOffset, int yOffset, float zOffset) {
		// TODO Auto-generated method stub
		
		BufferedImage map = GeneratedImages.generateCombinationTerrainImage(width, height, xOffset, yOffset, zoomLevel, zOffset);
		
		BufferedImage croppedMap = new BufferedImage(256, 256, map.getType());
		Graphics g = croppedMap.getGraphics();
		g.drawImage(map, 0, 0, 256, 256, 72, 72, 328, 328, null);
		
		return null;
	}

}
