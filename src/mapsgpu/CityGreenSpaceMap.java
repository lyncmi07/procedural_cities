package mapsgpu;

import java.awt.image.BufferedImage;
import java.nio.FloatBuffer;

import models.Camera;
import models.Entity;
import models.VAOModel;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import shaders.TerrainGenerationShader;
import display.Renderer;

public class CityGreenSpaceMap extends Map{

	public CityGreenSpaceMap(int x, int y, int z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateNewArea(int x, int y, float z) {
		// TODO Auto-generated method stub
GL11.glViewport(0, 0, 768, 768);
		
		TerrainGenerationShader shader = new TerrainGenerationShader(TerrainGenerationShader.BINARY_MAP_IMAGE);
		
		Renderer renderer = new Renderer();
		renderer.loadProjectionMatrixToShader(shader);
		
		shader.start();
		shader.loadMapType(1);
		shader.loadIterationNumber(5);
		shader.loadFirstCutoff(0.6667f);
		shader.stop();
		
		float[] vertices = 
			{
				-1f,1f,0.0f,
				-1f,-1f,0.0f,
				1f,-1f, 0.0f,
				1f,1f,0.0f
			};
		int[] indices = 
			{
				0,1,3,
				3,1,2
			};
		
		Entity testEntity = new Entity(
				new VAOModel(vertices, indices),
				new Vector3f(0,0,0),
				new Vector3f(0,0,0),
				new Vector3f(1,1,1)
				);
		
		Camera camera = new Camera(new Vector3f(x*256,y*256,z), new Vector3f(0,0,0));
		
		renderer.prepare();
		shader.start();
		shader.loadViewMatrix(camera);
		renderer.renderModel(testEntity, shader);
		shader.stop();
		
		FloatBuffer imageData = BufferUtils.createFloatBuffer(768*768*3);
		GL11.glReadPixels(0, 0, 768, 768, GL11.GL_RGB,  GL11.GL_FLOAT,  imageData);
		mapImage = new BufferedImage(768,768,BufferedImage.TYPE_INT_RGB);
		
		int[] rgbArray = new int[768*768];
		for(int i = 0; i < 768; i++)
		{
			for(int a = 0; a < 768; a++)
			{
				int r = (int)(imageData.get() * 255) << 16;
				int g = (int)(imageData.get() * 255) << 8;
				int b = (int)(imageData.get() * 255);
				rgbArray[((768 - 1) - i) * 768 + a] = r+g+b;
			}
		}
		
		mapImage.setRGB(0, 0, 768, 768, rgbArray, 0, 768);
		GL11.glViewport(0, 0, 1200, 800);
	}

}
