package program;

import java.awt.image.BufferedImage;

import proceduralgeneration.RoadData;
import proceduralgeneration.RoadGeneration;
import imageprocessing.Filters;
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
		
		FullMap fullMap = new FullMap(5877, 5787, 0);
		
		BufferedImage zoneBasedRoads = Filters.edgeDetection(fullMap.getZoningMap().getMap(), 5, 3, 7);
		BufferedImage greenSpaceBasedRoads = Filters.lineThicken(Filters.edgeDetection(fullMap.getCityGreenSpaceMap().getMap(), 5, 3, 7));
		BufferedImage LandBasedRoads = Filters.lineThicken(Filters.edgeDetection(fullMap.getLandSeaMap().getMap(), 5, 3, 7));
		
		RoadData dataSet = RoadGeneration.getRoadPoints(zoneBasedRoads);
		BufferedImage roadPointsMap = RoadGeneration.getRoadPointsImage(dataSet);
		BufferedImage joinedPointsMap = RoadGeneration.getJoinedRoadPoints(dataSet);
		
		RoadData dataSet2 = RoadGeneration.getRoadPoints(greenSpaceBasedRoads);
		BufferedImage roadPointsMap2 = RoadGeneration.getRoadPointsImage(dataSet2);
		BufferedImage joinedPointsMap2 = RoadGeneration.getJoinedRoadPoints(dataSet2);
		
		RoadData dataSet3 = RoadGeneration.getRoadPoints(LandBasedRoads);
		BufferedImage roadPointsMap3= RoadGeneration.getRoadPointsImage(dataSet3);
		BufferedImage joinedPointsMap3 = RoadGeneration.getJoinedRoadPoints(dataSet3);
		
		debug.ImageDebug.printImageToScreen(zoneBasedRoads);
		debug.ImageDebug.printImageToScreen(roadPointsMap);
		debug.ImageDebug.printImageToScreen(joinedPointsMap);
		debug.ImageDebug.printImageToScreen(roadPointsMap2);
		debug.ImageDebug.printImageToScreen(joinedPointsMap2);
		debug.ImageDebug.printImageToScreen(roadPointsMap3);
		debug.ImageDebug.printImageToScreen(joinedPointsMap3);
		//debug.ImageDebug.printImageToScreen(greenSpaceBasedRoads);
		//debug.ImageDebug.printImageToScreen(LandBasedRoads);
		
		System.out.println(fullMap.getPixelRundown(-150, 40));
	}
}
