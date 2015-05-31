package imageprocessing;

import java.awt.image.BufferedImage;

/**
 * A group of functions for creating useful data from a BufferedImage
 * @author michael
 *
 */
public class Filters {

	/**
	 * Replaces one colour in the image to another colour
	 * @param image	original image
	 * @param originalColour	colour to change
	 * @param newColour	colour to change to
	 * @return
	 */
	public static BufferedImage colourChange(BufferedImage image, int originalColour, int newColour)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < width; i++) {
			for (int a = 0; a < height; a++) {
				int pixel = image.getRGB(i, a);
				
				if((pixel & 0x000000FF) == (originalColour & 0x000000FF) && (pixel & 0x0000FF00) == (originalColour & 0x0000FF00) && (pixel & 0x00FF0000) == (originalColour & 0x00FF0000))
				{
					newImage.setRGB(i, a, newColour);
				}
				else
				{
					newImage.setRGB(i, a, pixel);
				}
			}
		}

		return newImage;
	}
	
	/**
	 * Sets each pixel to be black or white depending on whether it is below or above midpoint respectively
	 * @param image original image
	 * @param midpoint cutoff point
	 * @return
	 */
	public static BufferedImage binaryMapping(BufferedImage image,
			int midpoint) {

		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < width; i++) {
			for (int a = 0; a < height; a++) {
				int pixel = image.getRGB(i, a);

				int r = ((pixel >> 16) & 0x000000FF);

				if (r > midpoint) {
					r = 255;
				} else {
					r = 0;
				}

				newImage.setRGB(i, a, (r << 16) | (r << 8) | (r));
			}
		}

		return newImage;
	}

	/**
	 * Sets each pixel to be red, green or blue depending on whether it is below split1, between split1 and 2 or above split2 respectively
	 * @param image	original image
	 * @param split1 first cutoff point
	 * @param split2 second cutoff point
	 * @return
	 */
	public static BufferedImage tripleMapping(BufferedImage image, int split1, int split2)
	{

		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < width; i++)
		{
			for(int a = 0; a < height; a++)
			{
				int pixel = image.getRGB(i, a);
				
				
				int r = ((pixel >> 16) & 0x000000FF);
				int g;
				int b;
				if(r < split1)
				{
					r = 255;
					g = 0 & 0x000000FF;
					b = 0 & 0x000000FF;
				}
				else if(r < split2)
				{
					r = 0;
					g = 255 & 0x000000FF;
					b = 0 & 0x000000FF;
				}
				else
				{
					r = 0;
					g = 0 & 0x000000FF;
					b = 255 & 0x000000FF;
				}
				
				newImage.setRGB(i, a, (r << 16) | (g << 8) | (b));
			}
		}
		
		return newImage;
	}
}
