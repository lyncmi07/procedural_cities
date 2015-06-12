package shaders;

import models.Camera;

import org.lwjgl.util.vector.Matrix4f;

import tools.Maths;

public class ShaderTexture3D extends Shader3D{
	private static final String VERTEX_FILE = "src/shaders/ShaderTexture3D_VERTEX.txt";
	private static final String FRAGMENT_FILE = "src/shaders/ShaderTexture3D_FRAG.txt";
	
	public ShaderTexture3D()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}
}
