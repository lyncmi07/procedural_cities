package shaders;

import models.Camera;

import org.lwjgl.util.vector.Matrix4f;

import tools.Maths;

public abstract class Shader3D extends ShaderProgram{
	public Shader3D(String vertexFile, String fragmentFile) {
		super(vertexFile, fragmentFile);
		// TODO Auto-generated constructor stub
	}

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocations() {
		// TODO Auto-generated method stub
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		
	}

	public void loadProjectionMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
}
