package proceduralgeneration;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class GeneratedImages {
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
