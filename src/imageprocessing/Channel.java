package imageprocessing;

import java.awt.image.BufferedImage;

public class Channel {
	public static BufferedImage redFilter(BufferedImage image)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < width; i++)
		{
			for(int a = 0; a < height; a++)
			{
				int pixel = image.getRGB(i, a);
				
				int red = (pixel >> 16) & 0x000000FF;
	            int green = 0;
	            int blue = 0;
	            
	            newImage.setRGB(i,a, ((red << 16) | (green << 8) | blue));
			}
		}
		
		return newImage;
	}
	
	public static BufferedImage greenFilter(BufferedImage image)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < width; i++)
		{
			for(int a = 0; a < height; a++)
			{
				int pixel = image.getRGB(i, a);
				
				int red = 0;
	            int green = (pixel >>8 ) & 0x000000FF;
	            int blue = 0;
	            
	            newImage.setRGB(i,a, ((red << 16) | (green << 8) | blue));
			}
		}
		
		return newImage;
	}
	
	public static BufferedImage blueFilter(BufferedImage image)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < width; i++)
		{
			for(int a = 0; a < height; a++)
			{
				int pixel = image.getRGB(i, a);
				
				int red = 0;
	            int green = 0;
	            int blue = (pixel) & 0x000000FF;
	            
	            newImage.setRGB(i,a, ((red << 16) | (green << 8) | blue));
			}
		}
		
		return newImage;
	}
}
