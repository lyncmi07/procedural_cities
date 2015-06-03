package program;

import maps.CitiesMap;
import maps.CityAttractionsMap;
import maps.CityGreenSpaceMap;
import maps.FullMap;
import maps.LandSeaMap;
import maps.TerrainMap;
import maps.WealthMap;
import maps.ZoningMap;

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
		/*TerrainMap terrainMap = new TerrainMap(0,0,0);
		LandSeaMap landSeaMap = new LandSeaMap(terrainMap, 160);
		CitiesMap citiesMap = new CitiesMap(0,0, 0, landSeaMap, 180);
		CityGreenSpaceMap cityGreenSpaceMap = new CityGreenSpaceMap(0, 0, 0, citiesMap, 170);
		CityAttractionsMap cityAttractionsMap = new CityAttractionsMap(0, 0, 0, citiesMap, 254);
		WealthMap wealthMap = new WealthMap(0, 0, 0, citiesMap, 100, 200);
		ZoningMap zoningMap = new ZoningMap(0, 0, 0, citiesMap, 70, 140);
		
		
		debug.MapDebug.printMapToScreen(terrainMap);
		debug.MapDebug.printMapToScreen(landSeaMap);
		debug.MapDebug.printMapToScreen(citiesMap);
		debug.MapDebug.printMapToScreen(cityGreenSpaceMap);
		debug.MapDebug.printMapToScreen(cityAttractionsMap);
		debug.MapDebug.printMapToScreen(wealthMap);
		debug.MapDebug.printMapToScreen(zoningMap);*/
		
		FullMap fullMap = new FullMap(0, 1, 0);
		
		debug.MapDebug.printMapToScreen(fullMap.getLandSeaMap());
		debug.MapDebug.printMapToScreen(fullMap.getCitiesMap());
		
		System.out.println(fullMap.getPixelRundown(380, 380));
	}
}
