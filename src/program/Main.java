package program;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import display.DisplayManager;
import display.Renderer;
import proceduralgeneration.GeneratedImages;
import proceduralgeneration.RoadData;
import proceduralgeneration.RoadGeneration;
import shaders.Shader3D;
import shaders.ShaderNoTexture3D;
import shaders.ShaderProgram;
import shaders.TerrainGenerationShader;
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
import models.Camera;
import models.Entity;
import models.VAOModel;

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
		DisplayManager.createDisplay();
		
		Shader3D shader = new TerrainGenerationShader();
		
		Renderer renderer = new Renderer();
		renderer.loadProjectionMatrixToShader(shader);
		
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
		
		Camera camera = new Camera(new Vector3f(0,0,0), new Vector3f(0,0,0));
		
		while(!Display.isCloseRequested())
		{
			//update model transforms
			camera.move();
			testEntity.increasePosition(1, 1, 0);
			
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.renderModel(testEntity, shader);
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
	}
}
