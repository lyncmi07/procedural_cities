package mapsgpu;

public class FullMap {
	private TerrainMap terrainMap;
	
	public FullMap()
	{
		terrainMap = new TerrainMap(0,0,0);
	}
	
	public TerrainMap getTerrainMap()
	{
		return terrainMap;
	}
}
