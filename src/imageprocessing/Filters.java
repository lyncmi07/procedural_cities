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
	
	public static BufferedImage edgeDetection(BufferedImage image, int contrastThreshold, int minimumPixels, int maximumPixels)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < width; i++)
		{
			for(int a = 0; a < height; a++)
			{
				int pixel = image.getRGB(i, a);
				
				int[] closePixels = new int[8];
				
				for(int z = 0; z < 8; z++)
				{
					closePixels[z] = 0;
				}
				
				
				
				if(i-1 > -1 && a-1 > -1){
				closePixels[0] = image.getRGB(i-1, a-1);}
				if(i-1 > -1){
				closePixels[1] = image.getRGB(i-1, a);}
				if(i-1 > -1 && a+1 < height){
				closePixels[2] = image.getRGB(i-1, a+1);}
				if(i+1 < width && a-1 > -1){
				closePixels[3] = image.getRGB(i+1, a-1);}
				if(i+1 < width){
				closePixels[4] = image.getRGB(i+1, a);}
				if(i+1 < width && a+1 < height){
				closePixels[5] = image.getRGB(i+1, a+1);}
				if(a-1 > -1){
				closePixels[6] = image.getRGB(i, a-1);}
				if(a+1 < height){
				closePixels[7] = image.getRGB(i, a+1);}
				
				int r = 0;
				int g = 0;
				int b = 0;
				
				int differencePixels = 0;
				
				int red = ((pixel >> 16) & 0x000000FF);
				int green = ((pixel >> 8) & 0x000000FF);
				int blue = ((pixel) & 0x000000FF);
				
				for(int z = 0; z < closePixels.length; z++)
				{
					r = (closePixels[z] >> 16) & 0x000000FF;
					g = (closePixels[z] >> 8) & 0x000000FF;
					b = (closePixels[z]) & 0x000000FF;
					
					int rDifference = (int)Math.pow(((red - r) * 2), 0.5);
					int gDifference = (int)Math.pow(((green - g) * 2), 0.5);
					int bDifference = (int)Math.pow(((blue - b) * 2), 0.5);
					
					if(rDifference > contrastThreshold || gDifference > contrastThreshold || bDifference > contrastThreshold)
					{
						differencePixels += 1;
					}
				}
				
				if(differencePixels >= minimumPixels && differencePixels <= maximumPixels)
				{
					newImage.setRGB(i, a, (((0 & 0x000000FF) << 16) | ((0 & 0x000000FF) << 8) | (0 & 0x000000FF)));
				}
				else
				{
					newImage.setRGB(i, a, (((255 & 0x000000FF) << 16) | ((255 & 0x000000FF) << 8) | (255 & 0x000000FF)));
				}
				
				/*r /= 8;
				g /= 8;
				b /= 8;
				
				int red = r - ((pixel >> 16) & 0x000000FF);
				int green = g - ((pixel >> 8) & 0x000000FF);
				int blue = b - ((pixel) & 0x000000FF);*/
	            
	           // newImage.setRGB(i,a, ((red << 16) | (green << 8) | blue));
			}
		}
		
		return newImage;
	}
	
	public static BufferedImage deSpot(BufferedImage image, int pathLength) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < width; i++) {
			for (int a = 0; a < height; a++) {
				if (deSpotRecursion(image, i, a, i, a, pathLength) == true) {
					newImage.setRGB(i, a, ((0 & 0x000000FF) << 16)
							| ((0 & 0x000000FF) << 8) | (0 & 0x00000FF));
				} else {
					newImage.setRGB(i, a, ((255 & 0x000000FF) << 16)
							| ((255 & 0x000000FF) << 8) | (255 & 0x00000FF));
				}

				// newImage.setRGB(i, a, ((255 & 0x000000FF) << 16) | ((255 &
				// 0x000000FF) << 8) | (255 & 0x00000FF));
			}
		}

		return newImage;
	}
	
	private static boolean deSpotRecursion(BufferedImage image,
			int originPixelX, int originPixelY, int newPixelX, int newPixelY,
			int pathLength) {

		int width = image.getWidth();
		int height = image.getHeight();

		int pixel = image.getRGB(newPixelX, newPixelY);

		if (((pixel) & 0x000000FF) != 0) {
			return false;
		}

		if (pathLength == 0) {
			return true;
		}

		int[] closePixels = new int[8];

		for (int z = 0; z < 8; z++) {
			closePixels[z] = ((255 & 0x000000FF) | (255 & 0x000000FF) | (255 & 0x000000FF));
		}

		boolean isPath = false;

		if (newPixelX - 1 > -1
				&& newPixelY - 1 > -1
				&& ((originPixelX != (newPixelX - 1)) && (originPixelY != (newPixelY - 1)))) {
			closePixels[0] = image.getRGB(newPixelX - 1, newPixelY - 1);
			if (((closePixels[0]) & 0x000000FF) == 0) {
				isPath = deSpotRecursion(image, newPixelX, newPixelY,
						newPixelX - 1, newPixelY - 1, pathLength - 1);
			}
		} else if (newPixelX - 1 > -1
				&& ((originPixelX != (newPixelX - 1)) && (originPixelY != (newPixelY)))) {
			closePixels[1] = image.getRGB(newPixelX - 1, newPixelY);
			if (((closePixels[0]) & 0x000000FF) == 0) {
				isPath = deSpotRecursion(image, newPixelX, newPixelY,
						newPixelX - 1, newPixelY, pathLength - 1);
			}
		} else if (newPixelX - 1 > -1
				&& newPixelY + 1 < height
				&& ((originPixelX != (newPixelX - 1)) && (originPixelY != (newPixelY + 1)))) {
			closePixels[2] = image.getRGB(newPixelX - 1, newPixelY + 1);
			if (((closePixels[0]) & 0x000000FF) == 0) {
				isPath = deSpotRecursion(image, newPixelX, newPixelY,
						newPixelX - 1, newPixelY + 1, pathLength - 1);
			}
		} else if (newPixelX + 1 < width
				&& newPixelY - 1 > -1
				&& ((originPixelX != (newPixelX + 1)) && (originPixelY != (newPixelY - 1)))) {
			closePixels[3] = image.getRGB(newPixelX + 1, newPixelY - 1);
			if (((closePixels[0]) & 0x000000FF) == 0) {
				isPath = deSpotRecursion(image, newPixelX, newPixelY,
						newPixelX + 1, newPixelY - 1, pathLength - 1);
			}
		} else if (newPixelX + 1 < width
				&& ((originPixelX != (newPixelX + 1)) && (originPixelY != (newPixelY)))) {
			closePixels[4] = image.getRGB(newPixelX + 1, newPixelY);
			if (((closePixels[0]) & 0x000000FF) == 0) {
				isPath = deSpotRecursion(image, newPixelX, newPixelY,
						newPixelX + 1, newPixelY, pathLength - 1);
			}
		} else if (newPixelX + 1 < width
				&& newPixelY + 1 < height
				&& ((originPixelX != (newPixelX + 1)) && (originPixelY != (newPixelY + 1)))) {
			closePixels[5] = image.getRGB(newPixelX + 1, newPixelY + 1);
			if (((closePixels[0]) & 0x000000FF) == 0) {
				isPath = deSpotRecursion(image, newPixelX, newPixelY,
						newPixelX + 1, newPixelY + 1, pathLength - 1);
			}
		} else if (newPixelY - 1 > -1
				&& ((originPixelX != (newPixelX)) && (originPixelY != (newPixelY - 1)))) {
			closePixels[6] = image.getRGB(newPixelX, newPixelY - 1);
			if (((closePixels[0]) & 0x000000FF) == 0) {
				isPath = deSpotRecursion(image, newPixelX, newPixelY,
						newPixelX, newPixelY - 1, pathLength - 1);
			}
		} else if (newPixelY + 1 < height
				&& ((originPixelX != (newPixelX)) && (originPixelY != (newPixelY + 1)))) {
			closePixels[7] = image.getRGB(newPixelX, newPixelY + 1);
			if (((closePixels[0]) & 0x000000FF) == 0) {
				isPath = deSpotRecursion(image, newPixelX, newPixelY,
						newPixelX, newPixelY + 1, pathLength - 1);
			}
		}
		return isPath;
	}
	
	public static BufferedImage lineThicken(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < width; i++) {
			for (int a = 0; a < height; a++) {
				int pixel = image.getRGB(i, a);

				int r = ((pixel >> 16) & 0x000000FF);

				if (r == 0 && i > 0 && i < width - 1 && a > 0 && a < height - 1) {
					newImage.setRGB(i - 1, a - 1, 0);
					newImage.setRGB(i - 1, a, 0);
					newImage.setRGB(i - 1, a + 1, 0);
					newImage.setRGB(i, a - 1, 0);
					newImage.setRGB(i, a + 1, 0);
					newImage.setRGB(i + 1, a - 1, 0);
					newImage.setRGB(i + 1, a, 0);
					newImage.setRGB(i + 1, a + 1, 0);

				}
				newImage.setRGB(i, a, pixel);
			}
		}

		return newImage;
	}
}
