package program;

import java.awt.image.BufferedImage;

import proceduralgeneration.GeneratedImages;
import proceduralgeneration.RoadData;
import proceduralgeneration.RoadGeneration;
import imageprocessing.AddImage;
import imageprocessing.BooleanOperators;
import imageprocessing.Channel;
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
		System.out.println("0%");
		FullMap fullMap = new FullMap(1991, 3994, 0);
		
		System.out.println("50%");
		BufferedImage zoneBasedRoads = Filters.lineThicken(Filters.edgeDetection(fullMap.getZoningMap().getMap(), 5, 3, 7), (0 << 16) | (0 << 8) | (0));
		System.out.println("60%");
		BufferedImage wealthBasedRoads = Filters.lineThicken(Filters.edgeDetection(fullMap.getWealthMap().getMap(), 5, 3, 7), (0 << 16) | (0 << 8) | (0));
		System.out.println("70%");
		RoadData dataSet = RoadGeneration.getRoadPoints(zoneBasedRoads);
		System.out.println("75%");
		RoadData dataSet2 = RoadGeneration.getRoadPoints(wealthBasedRoads);
		System.out.println("80%");
		dataSet.addDataSet(dataSet2);
		System.out.println("81%");
		BufferedImage roadPointsMap = RoadGeneration.getRoadPointsImage(dataSet);
		System.out.println("81.5%");
		BufferedImage joinedPointsMap = RoadGeneration.getJoinedRoadPoints(dataSet);
		System.out.println("82%");
		debug.ImageDebug.printImageToScreen(joinedPointsMap);
		
		//System.out.println(fullMap.getPixelRundown(-150, 40));
		
		//generating a top down graphical composite map
		BufferedImage compositeMap = fullMap.getTerrainMap().getMap();
		compositeMap = Channel.greenFilter(compositeMap);
		System.out.println("86%");
		
		compositeMap = BooleanOperators.and(fullMap.getLandSeaMap().getMap(), compositeMap);
		System.out.println("87%");
		compositeMap = BooleanOperators.nand(compositeMap, Channel.blueFilter(fullMap.getTerrainMap().getMap()));
		System.out.println("88%");
		
		//create light grey city ambiance colour
		BufferedImage cityMap = fullMap.getCitiesMap().getMap();
		BufferedImage randomColourBuildings = GeneratedImages.generationSystem1(768, 768, 1232, 241, 113);
		cityMap = BooleanOperators.and(cityMap, randomColourBuildings);
		compositeMap = AddImage.blanketAdd(compositeMap, cityMap);
		
		//create buildings
		BufferedImage buildingMap = Filters.lineThicken(joinedPointsMap, (255 << 16) | (255 << 8) | (255));
		buildingMap = BooleanOperators.and(fullMap.getCitiesMap().getMap(), buildingMap);
		buildingMap = BooleanOperators.and(buildingMap, randomColourBuildings);
		compositeMap = AddImage.blanketAdd(compositeMap, buildingMap);
		System.out.println("89%");
		
		//create parks
		BufferedImage colouredParks = Filters.colourChange(fullMap.getCityGreenSpaceMap().getMap(), (255 << 16) | (255 << 8) | (255), (40 << 16) | (150 << 8) | (0));
		System.out.println("90%");
		compositeMap = AddImage.blanketAdd(compositeMap, colouredParks);
		System.out.println("92%");
		
		//make roads grey
		joinedPointsMap = Filters.colourChange(joinedPointsMap, (255 << 16) | (255 << 8) | (255), (100 << 16) | (100 << 8) | (100));
		System.out.println("95%");
		compositeMap = AddImage.blanketAdd(compositeMap, joinedPointsMap);
		System.out.println("97%");
		
		debug.ImageDebug.printImageToScreen(compositeMap);
		System.out.println("100%");
	}
}
