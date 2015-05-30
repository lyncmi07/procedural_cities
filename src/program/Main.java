package program;

import maps.TerrainMap;

public class Main {
	public static void main(String[] args)
	{
		TerrainMap terrainMap = new TerrainMap(2,0,0);
		
		debug.MapDebug.printMapToScreen(terrainMap);
	}
}
