package maps;

import java.awt.image.BufferedImage;

public class UniquelyIdentifiableBinaryMap extends BinaryMap{
	public long getIDAtCoord(int x, int y)
	{
		boolean isInArea = getValueAtCoord(x, y);
		
		if(!isInArea)
		{
			return 0;
		}
		
		System.out.println(isInArea);
		
		double idInDouble = getUniqueIdentifier(x,y, this.getMap());
		
		long idAsLong = Double.doubleToRawLongBits(idInDouble);
		
		return idAsLong;
	}
	
	private double getUniqueIdentifier(int x, int y, BufferedImage processingImage)
	{
		
		//System.out.println(processingImage.getHeight());
		//System.out.println(processingImage.getWidth());
		
		int pixel = processingImage.getRGB(x, y);
		int red = (pixel >> 16) & 0x000000FF;
		int green = (pixel >> 8) & 0x000000FF;
		int blue = (pixel) & 0x000000FF;
		
		if(red == 0 && green == 0 && blue == 255)
		{
			//already processed this pixel
			return 0;
		}
		if(red == 0 && green == 0 && blue == 0)
		{
			//not part of the area being identified
			return 0;
		}
		
		red = 0;
		green = 0;
		blue = 255;
		
		processingImage.setRGB(x, y, (red << 16) | (green << 8) | (blue));
		
		double returnValue = 0;
		returnValue += getUniqueIdentifier(x-1,y, processingImage);
		returnValue += getUniqueIdentifier(x+1,y, processingImage);
		returnValue += getUniqueIdentifier(x,y-1, processingImage);
		returnValue += getUniqueIdentifier(x,y+1, processingImage);
		
		return returnValue + Math.sin(x) + Math.cos(y);
	}
}
