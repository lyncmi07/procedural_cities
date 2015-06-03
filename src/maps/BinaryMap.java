package maps;

public abstract class BinaryMap extends Map{
	public boolean getValueAtCoord(int x, int y)
	{
		int pixel = stitchedMap.getRGB(x, y);
		
		int value = pixel & 0x000000FF;
		
		if(value == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
