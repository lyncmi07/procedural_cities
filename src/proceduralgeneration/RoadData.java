package proceduralgeneration;

import java.util.ArrayList;
import java.util.List;

import proceduralgeneration.RoadDataPoint;

public class RoadData {
	private List<RoadDataPoint> points = new ArrayList<RoadDataPoint>();
	private int width;
	private int height;
	
	public void addDataPoint(int x, int y)
	{
		points.add(new RoadDataPoint(x,y));
	}
	
	public RoadDataPoint getDataPoint(int index)
	{
		return points.get(index);
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public int getNumberOfPoints()
	{
		return points.size();
	}
	
	public RoadData(int imageWidth, int imageHeight)
	{
		width = imageWidth;
		height = imageHeight;
	}
}
