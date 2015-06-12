package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class VAOModel {
	private int vaoType;
	private int vaoID;
	private int verticeVboID;
	private int textureCoordVboID;
	private int indicesVboID;
	private int numberOfVertices;
	private int textureID;
	
	public VAOModel(float[] vertices, int[] indices)
	{
		numberOfVertices = indices.length;
		vaoType = 0;	//model without texture info
		createVAO();
		GL30.glBindVertexArray(vaoID);
		bindIndicesBuffer(indices);
		
		verticeVboID = storeDataInAttributeList(0, 3, vertices);
		GL30.glBindVertexArray(0);
	}
	public VAOModel(float[] vertices, int[] indices, float[] textureCoords, String textureFileName)
	{
		numberOfVertices = indices.length;
		vaoType = 0;	//model without texture info
		createVAO();
		GL30.glBindVertexArray(vaoID);
		bindIndicesBuffer(indices);
		
		verticeVboID = storeDataInAttributeList(0, 3, vertices);
		textureCoordVboID = storeDataInAttributeList(1, 2, textureCoords);
		
		loadTexture(textureFileName);
		
		GL30.glBindVertexArray(0);
	}
	
	private void loadTexture(String fileName)
	{
		Texture texture = null;
		try
		{
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/" + fileName + ".png"));
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		textureID = texture.getTextureID();
	}
	private void createVAO()
	{
		vaoID = GL30.glGenVertexArrays();
	}
	private void bindIndicesBuffer(int[] indices)
	{
		indicesVboID = GL15.glGenBuffers();
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesVboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	private FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	
	/**
	 * 
	 * @param attributeNumber
	 * @param vectorSize
	 * @param data
	 * @return id of the vbo for this attribute list
	 */
	private int storeDataInAttributeList(int attributeNumber, int vectorSize, float[] data)
	{
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER,  buffer,  GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, vectorSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		return vboID;
	}
	
	public void destroyModel()
	{
		GL30.glDeleteVertexArrays(vaoID);
		GL15.glDeleteBuffers(verticeVboID);
		GL15.glDeleteBuffers(indicesVboID);
		
		if(vaoType == 1)
		{
			GL15.glDeleteBuffers(textureCoordVboID);
			GL11.glDeleteTextures(textureID);
		}
	}
	
	//GETTERS
	public int getVaoType() {
		return vaoType;
	}
	public int getVaoID() {
		return vaoID;
	}
	public int getVerticeVboID() {
		return verticeVboID;
	}
	public int getTextureCoordVboID() {
		return textureCoordVboID;
	}
	public int getIndicesVboID() {
		return indicesVboID;
	}
	public int getTextureID() {
		return textureID;
	}
	public int getNumberOfVertices()
	{
		return numberOfVertices;
	}
}
