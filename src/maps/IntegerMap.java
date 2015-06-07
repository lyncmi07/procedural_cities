package maps;

public abstract class IntegerMap extends Map{
	public int getValueAtCoord(int x, int y)
	{
		int pixel = stitchedMap.getRGB(x, y);
		
		int r = (pixel >> 16) & 0x000000FF;
		
		return r;
	}
}
