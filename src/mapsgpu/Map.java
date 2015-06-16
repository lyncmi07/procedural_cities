package mapsgpu;

import java.awt.image.BufferedImage;

public abstract class Map {
	protected int xCoord;
	protected int yCoord;
	protected float zCoord;
	protected BufferedImage mapImage;
	
	public int getXCoord()
	{
		return xCoord;
	}
	public int getYCoord()
	{
		return yCoord;
	}
	public float getZCoord()
	{
		return zCoord;
	}
	public BufferedImage getMapImage()
	{
		return mapImage;
	}
	
	public Map(int x, int y, int z)
	{
		xCoord = x;
		yCoord = y;
		zCoord = z;
		generateNewArea(xCoord, yCoord, zCoord);
	}
	
	public void moveLeft()
	{
		xCoord -= 1;
		generateNewArea(xCoord, yCoord, zCoord);
	}
	public void moveRight()
	{
		xCoord += 1;
		generateNewArea(xCoord, yCoord, zCoord);
	}
	public void moveUp()
	{
		yCoord -= 1;
		generateNewArea(xCoord, yCoord, zCoord);
	}
	public void moveDown()
	{
		yCoord += 1;
		generateNewArea(xCoord, yCoord, zCoord);
	}
	public void moveIn(float amount)
	{
		zCoord += amount;
		generateNewArea(xCoord, yCoord, zCoord);
	}
	public void moveOut(float amount)
	{
		zCoord -= amount;
		generateNewArea(xCoord, yCoord, zCoord);
	}
	
	public abstract void generateNewArea(int x, int y, float z);
}
