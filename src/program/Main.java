package program;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
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
import mapsgpu.CityAttractionsMap;
import mapsgpu.LandSeaMap;
import mapsgpu.Map;
import mapsgpu.TerrainMap;
import models.Camera;
import models.Entity;
import models.TerrainModel;
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
	 * @throws LWJGLException 
	 */
	public static void main(String[] args) throws LWJGLException
	{
		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		DisplayManager.createDisplay();
		
		ShaderNoTexture3D shader = new ShaderNoTexture3D();
		
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
		
		Camera camera = new Camera(new Vector3f(5,200,500), new Vector3f(0,90,45));
		
		
		Map map = new TerrainMap(0,0,0);
		//debug.ImageDebug.printImageToScreen(map.getMapImage());
		TerrainModel terrainModel = new TerrainModel(map.getMapImage(), 768);
		VAOModel terrainVAOModel = new VAOModel(terrainModel.getData(), terrainModel.getIndices());
		Entity terrainEntity = new Entity(terrainVAOModel, 
											new Vector3f(10,0,0), 
											new Vector3f(0,0,0), 
											new Vector3f(10,10,10));
		boolean screenshotCollected = false;
		
		Vector3f blockPosition = new Vector3f(0,0,0);
		boolean blockChange = false;
		while(!Display.isCloseRequested())
		{
			
			//change terrain block
			if(Math.floor(camera.getPosition().x / 255) > -blockPosition.x)
			{
				blockPosition.x -= 1;
				blockChange = true;
			}
			if(Math.floor(camera.getPosition().x / 255) < -blockPosition.x)
			{
				blockPosition.x += 1;
				blockChange = true;
			}
			if(Math.floor(camera.getPosition().z / 255) > blockPosition.y)
			{
				blockPosition.y += 1;
				blockChange = true;
			}
			if(Math.floor(camera.getPosition().z / 255) < blockPosition.y)
			{
				blockPosition.y -= 1;
				blockChange = true;
			}
			if(blockChange)
			{
				map.generateNewArea((int)blockPosition.x, (int)blockPosition.y, blockPosition.z);
				//debug.ImageDebug.printImageToScreen(map.getMapImage());
				terrainModel = new TerrainModel(map.getMapImage(), 768);
				terrainVAOModel.destroyModel();
				terrainVAOModel = new VAOModel(terrainModel.getData(), terrainModel.getIndices());
				terrainEntity = new Entity(terrainVAOModel, 
													new Vector3f((-blockPosition.x * 256) - 256,0,(blockPosition.y * 256)-256), 
													new Vector3f(0,0,0), 
													new Vector3f(10,10,10));
				blockChange = false;
			}
			
			//update model transforms
			camera.move();
			//testEntity.increasePosition(1, 1, 0);
			
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.renderModel(terrainEntity, shader);
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
	}
}
