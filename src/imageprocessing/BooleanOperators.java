package imageprocessing;

import java.awt.image.BufferedImage;

/**
 * A group of functions that act as boolean operators for BufferedImages
 * @author michael
 *
 */
public class BooleanOperators {
	/**
	 * Masks image2 over image1 when image1 is white
	 * @param image1
	 * @param image2
	 * @return
	 */
	public static BufferedImage and(BufferedImage image1, BufferedImage image2)
	{
		int maskingColour = (255 << 16) | (255 << 8) | (255);
		int width = image1.getWidth();
		int height = image1.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < width; i++) {
			for (int a = 0; a < height; a++) {
				int pixel = image1.getRGB(i, a);
				int pixel2 = image2.getRGB(i, a);
				if((pixel & 0x000000FF) == (maskingColour & 0x000000FF) && (pixel & 0x0000FF00) == (maskingColour & 0x0000FF00) && (pixel & 0x00FF0000) == (maskingColour & 0x00FF0000))
				{
					newImage.setRGB(i, a, pixel2);
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
	 * Masks image2 over image1 when image1 is black
	 * @param image1
	 * @param image2
	 * @return
	 */
	public static BufferedImage nand(BufferedImage image1, BufferedImage image2)
	{
		int maskingColour = (0 << 16) | (0 << 8) | (0);
		int width = image1.getWidth();
		int height = image1.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < width; i++) {
			for (int a = 0; a < height; a++) {
				int pixel = image1.getRGB(i, a);
				int pixel2 = image2.getRGB(i, a);
				if((pixel & 0x000000FF) == (maskingColour & 0x000000FF) && (pixel & 0x0000FF00) == (maskingColour & 0x0000FF00) && (pixel & 0x00FF0000) == (maskingColour & 0x00FF0000))
				{
					newImage.setRGB(i, a, pixel2);
				}
				else
				{
					newImage.setRGB(i, a, pixel);
				}
			}
		}
		
		return newImage;
	}
}
