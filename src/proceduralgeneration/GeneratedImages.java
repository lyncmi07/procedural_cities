package proceduralgeneration;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * A group of function which purpose is to procedurally generate related maps
 * @author michael
 *
 */
public class GeneratedImages {
	/**
	 * 
	 * @param width width of map
	 * @param height height of map
	 * @param xOffset x offset of map
	 * @param yOffset y offset of map
	 * @param zPosition	z offset (time) of map
	 * @return BufferedImage representation of the map produces
	 */
	public static BufferedImage generationSystem1(int width, int height, double xOffset, double yOffset, double zPosition)
	{
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < width; i++)
		{
			for(int a = 0; a < height; a++)
			{
				double x = i + xOffset;
				double y = a + yOffset;
				int pixel = getGenerationSystem1Pixel(x,y, zPosition);
	            
	            newImage.setRGB(i,a, pixel);
			}
		}
		
		return newImage;
	}
	private static int getGenerationSystem1Pixel(double x, double y, double z)
	{
		//double generatedNumber = (Math.sin(x+1) * Math.sin(y) / Math.cos(x+1) + Math.cos(y) - Math.tan(y) * Math.tan(x+1) / Math.sin(x+1) + Math.tan(y) - Math.cos(x))*1000;
		double generatedNumber = Math.tan(x) * Math.tan(y) * Math.sin(x) / Math.cos(x) / Math.cos(y) +  Math.sin(x) * Math.tan(x) + Math.tan(y+x) + (Math.sin(z));
		//double generatedNumber = (Math.sin(x) + Math.sin(y) + Math.sin(z)) * (Math.cos(x) + Math.cos(y) + Math.cos(z)) / (Math.tan(x) + Math.tan(y) + Math.tan(z));
		//double generatedNumber = Math.sin(x*y*z) * Math.cos(x*y/z) / Math.tan(x*y*z);
		int pixelNumber = getPixelFromNumber(generatedNumber);
		
		return ((pixelNumber & 0x000000FF) << 16) | ((pixelNumber & 0x000000FF) << 8) | (pixelNumber & 0x000000FF);
	}
	
	private static int getPixelFromNumber(double number)
	{
		return (int)(number * 255);
	}
	
	/**
	 * Takes an input image and repeats it over a larger size going back on itself when it reaches the edge of each image
	 * @param width width of the new image
	 * @param height height of the new image
	 * @param oldImage the image to repeat
	 * @return
	 */
	public static BufferedImage createRepeatingTexture(int width, int height, BufferedImage oldImage)
	{
		BufferedImage newImage = new BufferedImage(width, height, oldImage.getType());
		
		double ratioWidth = width / oldImage.getWidth();
		double ratioHeight = height / oldImage.getHeight();
		
		for(int i = 0; i < width; i++)
		{
			for(int a = 0; a < height; a++)
			{
				int x = (int) (i % oldImage.getWidth());
				int y = (int) (a % oldImage.getHeight());
			
				int pixel = oldImage.getRGB(x, y);
				
				newImage.setRGB(i, a, pixel);
			}
		}
		
		return newImage;
	}
	
	/**
	 * Combines two images by making the second image slightly effect the first image by a given amount
	 * @param image1 high presidence image
	 * @param image2 low presidence image
	 * @param adjustmentRange The range of which to change the first image by
	 * @return
	 */
	public static BufferedImage combineImagesByAdjustment(BufferedImage image1, BufferedImage image2, double adjustmentRange)
	{
		double adjustmentRatio = 255 / adjustmentRange;
		int width = image1.getWidth();
		int height = image1.getHeight();
		
		BufferedImage newImage = new BufferedImage(width, height, image1.getType());
		
		for(int i = 0; i < width; i++)
		{
			for(int a = 0; a < height; a++)
			{
			
				int pixel1 = image1.getRGB(i, a);
				int pixel2 = image2.getRGB(i, a);
				
				pixel1 = pixel1 & 0x000000FF;
				pixel2 = pixel2 & 0x000000FF;
				
				pixel2 = (int)((pixel2 / adjustmentRatio) - (adjustmentRange / 2));
				
				pixel1 = pixel1 + pixel2;
				
				if(pixel1 > 255)
				{
					pixel1 = 255;
				}
				else if(pixel1 < 0)
				{
					pixel1 = 0;
				}
				
				newImage.setRGB(i, a, (pixel1 << 16) | (pixel1 << 8) | (pixel1));
			}
		}
		
		return newImage;
	}
	
	/**
	 * Gives an image that looks much like clouds that can be used to imitate terrain
	 * @param width width of the map
	 * @param height height of the map
	 * @param xOffset x offset of the map
	 * @param yOffset y offset of the map
	 * @param numberOfLevels number of iterations into the algorithm to go (effectively the zoom level)
	 * @param zPosition z offset (time) of the map
	 * @return A BufferedImage representation of the image produced using the generationSystem1 function
	 */
	public static BufferedImage generateCombinationTerrainImage(int width, int height, double xOffset, double yOffset, double numberOfLevels, double zPosition)
	{
		if(width <= 0)
		{
			width = 1;
		}
		if(height <= 0)
		{
			height = 1;
		}
		if(numberOfLevels > 1)
		{
			//there is an image smaller than this one that needs to be included
			BufferedImage smallerImage = generateCombinationTerrainImage((int)(width/2), (int)(height/2), xOffset, yOffset, numberOfLevels - 1, zPosition);
			BufferedImage resizedSmallerImage = new BufferedImage(width, height, smallerImage.getType());
		    Graphics2D g = resizedSmallerImage.createGraphics();
		    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g.drawImage(smallerImage, 0, 0, width, height, 0, 0, smallerImage.getWidth(), smallerImage.getHeight(), null);
		    g.dispose();
		    
		    BufferedImage thisImage = generationSystem1(width, height, (xOffset*Math.pow(2,numberOfLevels)), (yOffset*Math.pow(2, numberOfLevels)), zPosition);
		    
		    BufferedImage resultingImage = combineImagesByAdjustment(resizedSmallerImage, thisImage, 255 / Math.pow(2, numberOfLevels));
		    
		    return resultingImage;
		}
		else
		{
			//this is the smallest possible image
			BufferedImage thisImage = generationSystem1(width, height,(xOffset*Math.pow(2,numberOfLevels)), (yOffset*Math.pow(2, numberOfLevels)), zPosition);
			
			return thisImage;
		}
	}
}
