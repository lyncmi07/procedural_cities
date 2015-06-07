package imageprocessing;

import java.awt.image.BufferedImage;

public class AddImage {
	public static BufferedImage tileX(BufferedImage image1, BufferedImage image2)
	{
		int width = image1.getWidth() + image2.getWidth();
		int height = image1.getHeight();
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < image1.getWidth(); i++)
		{
			for(int a = 0; a < height; a++)
			{
				int pixel = image1.getRGB(i, a);
				if(i == image1.getWidth()-1)
				{
					int pixel2 = image2.getRGB(0, a);
					int r = (int)((((pixel >> 16) & 0x000000FF)*1.1) + (((pixel2 >> 16) & 0x000000FF)*0.9)) / 2;
					int g = (int)((((pixel >> 8) & 0x000000FF)*1.1) + (((pixel2 >> 8) & 0x000000FF)*0.9)) / 2;;
					int b = (int)((((pixel) & 0x000000FF)*1.1) + (((pixel2) & 0x000000FF)*0.9)) / 2;;
					
					pixel = (r << 16) | (g << 8) | (b);
					
					newImage.setRGB(i, a, pixel);
				}
				else
				{
					newImage.setRGB(i, a, pixel);
				}
			}
		}
		
		for(int i = 0; i < image2.getWidth(); i++)
		{
			for(int a = 0; a < height; a++)
			{
				//System.out.println(i + " " + a + " " + width);
				int pixel = image2.getRGB(i, a);
				
				if(i == 0)
				{
					int pixel2 = image1.getRGB(image1.getWidth()-1, a);
					int r = (int)((((pixel >> 16) & 0x000000FF)*1.1) + (((pixel2 >> 16) & 0x000000FF)*0.9)) / 2;
					int g = (int)((((pixel >> 8) & 0x000000FF)*1.1) + (((pixel2 >> 8) & 0x000000FF)*0.9)) / 2;;
					int b = (int)((((pixel) & 0x000000FF)*1.1) + (((pixel2) & 0x000000FF)*0.9)) / 2;;
					
					pixel = (r << 16) | (g << 8) | (b);
					
					newImage.setRGB(i+image1.getWidth(), a, pixel);
				}
				else{
					newImage.setRGB(i+image1.getWidth(), a, pixel);
				}
			}
		}
		
		return newImage;
	}
	
	public static BufferedImage tileY(BufferedImage image1, BufferedImage image2)
	{
		int width = image1.getWidth();
		int height = image1.getHeight() + image2.getHeight();
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < image1.getHeight(); i++)
		{
			for(int a = 0; a < width; a++)
			{
				int pixel = image1.getRGB(a, i);
				
				if(i == image1.getWidth()-1)
				{
					int pixel2 = image2.getRGB(a, 0);
					int r = (int)((((pixel >> 16) & 0x000000FF)*1.1) + (((pixel2 >> 16) & 0x000000FF)*0.9)) / 2;
					int g = (int)((((pixel >> 8) & 0x000000FF)*1.1) + (((pixel2 >> 8) & 0x000000FF)*0.9)) / 2;;
					int b = (int)((((pixel) & 0x000000FF)*1.1) + (((pixel2) & 0x000000FF)*0.9)) / 2;;
					
					pixel = (r << 16) | (g << 8) | (b);
					
					newImage.setRGB(a, i, pixel);
				}
				else{
					newImage.setRGB(a, i, pixel);
				}
			}
		}
		
		for(int i = 0; i < image2.getHeight(); i++)
		{
			for(int a = 0; a < width; a++)
			{
				//System.out.println(i + " " + a + " " + width);
				int pixel = image2.getRGB(a, i);
				
				if(i == 0)
				{
					int pixel2 = image1.getRGB(a, image1.getHeight()-1);
					int r = (int)((((pixel >> 16) & 0x000000FF)*1.5) + (((pixel2 >> 16) & 0x000000FF)*0.5)) / 2;
					int g = (int)((((pixel >> 8) & 0x000000FF)*1.5) + (((pixel2 >> 8) & 0x000000FF)*0.5)) / 2;;
					int b = (int)((((pixel) & 0x000000FF)*1.5) + (((pixel2) & 0x000000FF)*0.5)) / 2;;
					
					pixel = (r << 16) | (g << 8) | (b);
					
					newImage.setRGB(a, i+image1.getHeight(), pixel);
				}
				else
				{
					newImage.setRGB(a, i+image1.getHeight(), pixel);
				}
			}
		}
		
		return newImage;
	}
	
	/**
	 * Adds image2 over the top of image1 where image2 is not black
	 * @param image1
	 * @param image2
	 * @return
	 */
	public static BufferedImage blanketAdd(BufferedImage image1, BufferedImage image2)
	{
		int emptyColour = (0 << 16) | (0 << 8) | (0);
		int width = image1.getWidth();
		int height = image1.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < width; i++) {
			for (int a = 0; a < height; a++) {
				int pixel = image1.getRGB(i, a);
				int pixel2 = image2.getRGB(i, a);
				if((pixel2 & 0x000000FF) == (emptyColour & 0x000000FF) && (pixel2 & 0x0000FF00) == (emptyColour & 0x0000FF00) && (pixel2 & 0x00FF0000) == (emptyColour & 0x00FF0000))
				{
					newImage.setRGB(i, a, pixel);
				}
				else
				{
					newImage.setRGB(i, a, pixel2);
				}
			}
		}
		
		return newImage;
	}
}
