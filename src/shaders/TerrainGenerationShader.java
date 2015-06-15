package shaders;

public class TerrainGenerationShader extends Shader3D{
	private static final String VERTEX_FILE = "src/shaders/TerrainGenerationShader_VERTEX.txt";
	private static final String FRAGMENT_FILE = "src/shaders/TerrainGenerationShader_FRAG.txt";
	
	private int location_mapType;
	private int location_iterationNumber;
	private int location_firstCutoff;
	private int location_secondCutoff;
	
	public TerrainGenerationShader(int mapOutputType)
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	public static final int TERRAIN_MAP_IMAGE = 0;
	public static final int BINARY_MAP_IMAGE = 1;
	public static final int TERNERY_MAP_IMAGE = 2;
	
	@Override
	protected void getAllUniformLocations()
	{
		super.getAllUniformLocations();
		location_mapType = super.getUniformLocation("mapType");
		location_iterationNumber = super.getUniformLocation("iterationNumber");
		location_firstCutoff = super.getUniformLocation("firstCutoff");
		location_secondCutoff = super.getUniformLocation("secondCutoff");
	}
	
	public void loadMapType(int mapType)
	{
		super.loadFloat(location_mapType, mapType);
	}
	public void loadIterationNumber(int numberOfIterations)
	{
		super.loadInt(location_iterationNumber, numberOfIterations);
	}
	public void loadFirstCutoff(float cutoffValue)
	{
		super.loadFloat(location_firstCutoff, cutoffValue);
	}
	public void loadSecondCutoff(float cutoffValue)
	{
		super.loadFloat(location_secondCutoff, cutoffValue);
	}
}
