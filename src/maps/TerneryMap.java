package maps;

public abstract class TerneryMap extends Map{
	public int getValueAtCoord(int x, int y)
	{
		int pixel = stitchedMap.getRGB(x, y);
		
		int r = (pixel >> 16) & 0x000000FF;
		int g = (pixel >> 8) & 0x000000FF;
		int b = (pixel) & 0x000000FF;
		
		if(r == 255)
		{
			return 0;
		}
		else if(g == 255)
		{
			return 1;
		}
		else if(b == 255)
		{
			return 2;
		}
		
		return 0;
	}
}
