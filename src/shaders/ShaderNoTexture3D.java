package shaders;

public class ShaderNoTexture3D extends Shader3D{
	private static final String VERTEX_FILE = "src/shaders/ShaderNoTexture3D_VERTEX.txt";
	private static final String FRAGMENT_FILE = "src/shaders/ShaderNoTexture3D_FRAG.txt";
	
	public ShaderNoTexture3D()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
}
