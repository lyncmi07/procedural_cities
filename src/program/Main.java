package program;

import maps.TerrainMap;

/**
 * 
 * @author michael
 * Program starting class
 */
public class Main {
	
	/**
	 * Program starts with this function
	 * @param args
	 */
	public static void main(String[] args)
	{
		TerrainMap terrainMap = new TerrainMap(2,0,0);
		
		debug.MapDebug.printMapToScreen(terrainMap);
	}
}
