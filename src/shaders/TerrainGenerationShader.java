package shaders;

public class TerrainGenerationShader extends Shader3D{
	private static final String VERTEX_FILE = "src/shaders/TerrainGenerationShader_VERTEX.txt";
	private static final String FRAGMENT_FILE = "src/shaders/TerrainGenerationShader_FRAG.txt";
	
	public TerrainGenerationShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
}
