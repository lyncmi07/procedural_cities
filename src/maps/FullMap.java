package maps;

import stringgeneration.CityNames;
import stringgeneration.GreenSpaceNames;

public class FullMap {
	private TerrainMap terrainMap;
	private LandSeaMap landSeaMap;
	private CitiesMap citiesMap;
	private CityAttractionsMap cityAttractionsMap;
	private CityGreenSpaceMap cityGreenSpaceMap;
	private WealthMap wealthMap;
	private ZoningMap zoningMap;
	
	public FullMap(int originXOffset, int originYOffset, float originZOffset)
	{
		terrainMap = new TerrainMap(originXOffset, originYOffset, originZOffset);
		landSeaMap = new LandSeaMap(terrainMap, 200);
		citiesMap = new CitiesMap(originXOffset, originYOffset, originZOffset, landSeaMap, 180);
		cityAttractionsMap = new CityAttractionsMap(originXOffset, originYOffset, originZOffset, citiesMap, 254);
		cityGreenSpaceMap = new CityGreenSpaceMap(originXOffset, originYOffset, originZOffset, citiesMap, 170);
		wealthMap = new WealthMap(originXOffset, originYOffset, originZOffset, citiesMap, 100, 200);
		zoningMap = new ZoningMap(originXOffset, originYOffset, originZOffset, citiesMap, 70, 140);
	}
	
	public TerrainMap getTerrainMap() {
		return terrainMap;
	}
	public LandSeaMap getLandSeaMap() {
		return landSeaMap;
	}
	public CitiesMap getCitiesMap() {
		return citiesMap;
	}
	public CityAttractionsMap getCityAttractionsMap() {
		return cityAttractionsMap;
	}
	public CityGreenSpaceMap getCityGreenSpaceMap() {
		return cityGreenSpaceMap;
	}
	public WealthMap getWealthMap() {
		return wealthMap;
	}
	public ZoningMap getZoningMap() {
		return zoningMap;
	}
	
	public void goNorth()
	{
		terrainMap.moveMapUp();
		landSeaMap.regenerate(terrainMap, 180);
	}
	public void goEast()
	{
		terrainMap.moveMapRight();
		landSeaMap.regenerate(terrainMap, 180);
	}
	public void goSouth()
	{
		terrainMap.moveMapDown();
		landSeaMap.regenerate(terrainMap, 180);
	}
	public void goWest()
	{
		terrainMap.moveMapLeft();
		landSeaMap.regenerate(terrainMap, 180);
	}
	
	public String getPixelRundown(int x, int y)
	{
		x = x + 384;
		y = y + 384;
		int terrainHeight = terrainMap.getValueAtCoord(x, y);
		boolean isOnLand = landSeaMap.getValueAtCoord(x, y);
		boolean isInCity = citiesMap.getValueAtCoord(x, y);
		long cityID = citiesMap.getIDAtCoord(x, y);
		boolean isInCityGreenSpace = cityGreenSpaceMap.getValueAtCoord(x, y);
		long greenSpaceID = cityGreenSpaceMap.getIDAtCoord(x, y);
		boolean isAtCityAttraction = cityAttractionsMap.getValueAtCoord(x, y);
		long cityAttractionID = cityAttractionsMap.getIDAtCoord(x, y);
		int currentWealthArea = wealthMap.getValueAtCoord(x, y);
		int currentZoningArea = zoningMap.getValueAtCoord(x, y);
		
		String wealthAreaName = "unspecified";
		String zoningAreaName = "unspecified";
		
		switch(currentWealthArea)
		{
		case 0:
			wealthAreaName = "poor";
			break;
		case 1:
			wealthAreaName = "medium";
			break;
		case 3:
			wealthAreaName = "rich";
			break;
		}
		switch(currentZoningArea)
		{
		case 0:
			zoningAreaName = "industrial";
			break;
		case 1:
			zoningAreaName = "commercial";
			break;
		case 2:
			zoningAreaName = "residential";
		}
		
		return "PIXEL (" + x + ", " + y + ")\n" + 
				"Terrain Height: " + terrainHeight + "\n" +
				"Is on land: " + isOnLand + "\n" + 
				"Is in city: " + isInCity + "\n" + 
				"Is in city green space: " + isInCityGreenSpace + "\n" +
				"Is at city attraction: " + isAtCityAttraction + "\n" +
				"Is in " + wealthAreaName + " " + zoningAreaName + " area \n" +
				"IDs: \n" + 
				"City ID: " + cityID + "\n" +
				"City name: " + CityNames.getCityName(cityID) + "\n" +
				"Green Space ID: " + greenSpaceID + "\n" +
				"Green Space Name: " + GreenSpaceNames.getGreenSpaceName(greenSpaceID) + "\n" +
				"City Attraction ID: " + cityAttractionID;
	}
}
