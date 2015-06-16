package models;

import java.awt.image.BufferedImage;

public class TerrainModel {
	private float[] data;
	private float[] textureCoords;
	private int[] indices;
	
	public TerrainModel(BufferedImage terrainMap, int textureSize)
	{
		int width = terrainMap.getWidth();
		int height = terrainMap.getHeight();
		int numberOfVertices = width*height;
		
		data = new float[numberOfVertices*3];
		indices = new int[(numberOfVertices - width - height) * 6];
		textureCoords = new float[numberOfVertices * 2];
		
		for(int i = 0; i < (height); i++)
		{
			for(int a = 0; a < (width); a++)
			{
				float pixelData = (terrainMap.getRGB(a, i) & 0x000000FF);
				
				pixelData /= 20;
				
				//System.out.println(pixelData);
				//System.exit(-2);
				
				textureCoords[(a*2) + (i*width*2) + 0] = (i*1.0f)/textureSize;
				textureCoords[(a*2) + (i*width*2) + 1] = (a*1.0f)/textureSize;
				
				//System.out.println(textureCoords[(a*2) + (i*width*2) + 0] + ", " + textureCoords[(a*2) + (i*width*2) + 1]);
				
				data[(a*3) + (i*width*3) + 0] = a*0.1f-10f;	//x value;
				data[(a*3) + (i*width*3) + 1] = -10f + pixelData ;	//y value;
				data[(a*3) + (i*width*3) + 2] = i*0.1f+0.1f;	//z value;
				
				if(a+1 < width-1 && i+1 < height-1)
				{
					indices[(a*6) + (i*width*6) + 0] = a + i*width;
					indices[(a*6) + (i*width*6) + 1] = a + ((i+1)*width);
					indices[(a*6) + (i*width*6) + 2] = a+1 + i*width;
					indices[(a*6) + (i*width*6) + 3] = a+1 + i*width;
					indices[(a*6) + (i*width*6) + 4] = a + ((i+1)*width);
					indices[(a*6) + (i*width*6) + 5] = (a+1) + ((i+1)*width);
					
					//System.out.println(indices[(a*6) + (i*width*6) + 0] + ", " + indices[(a*6) + (i*width*6) + 1] + ", " + indices[(a*6) + (i*width*6) + 2]);
					//System.out.println(indices[(a*6) + (i*width*6) + 3] + ", " + indices[(a*6) + (i*width*6) + 4] + ", " + indices[(a*6) + (i*width*6) + 5]);
				}
			}
		}
		
		
	}

	public float[] getData() {
		return data;
	}

	public float[] getTextureCoords() {
		return textureCoords;
	}

	public int[] getIndices() {
		return indices;
	}
}
