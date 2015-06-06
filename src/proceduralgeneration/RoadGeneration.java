package proceduralgeneration;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class RoadGeneration {
	public static RoadData getRoadPoints(BufferedImage image)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		RoadData dataSet = new RoadData(width,height);
		
		for (int i = 0; i < width; i++) {
			for (int a = 0; a < height; a++) {
				if(!((i == 0 && a == 0) || (i == 0 && a == height-1) || (i == width-1 && a == 0) || (i == width-1 && a == height-1))){
				int[] closePixels = new int[8];
				int[] closeExecutedPixels = new int[4];
				
				for(int z = 0; z < 8; z++)
				{
					closePixels[z] = 0;
				}
				for(int z = 0; z < 4; z++)
				{
					closeExecutedPixels[z] = (100 << 16) | (100 << 8) | (100);
				}
				
				
				if(i-1 > -1 && a-1 > -1){
				closePixels[0] = image.getRGB(i-1, a-1);
				closeExecutedPixels[0] = newImage.getRGB(i-1,a-1);}
				if(i-1 > -1){
				closePixels[1] = image.getRGB(i-1, a);
				closeExecutedPixels[1] = newImage.getRGB(i-1, a);}
				if(i-1 > -1 && a+1 < height){
				closePixels[2] = image.getRGB(i-1, a+1);
				closeExecutedPixels[2] = newImage.getRGB(i-1, a+1);}
				if(i+1 < width && a-1 > -1){
				closePixels[3] = image.getRGB(i+1, a-1);}
				if(i+1 < width){
				closePixels[4] = image.getRGB(i+1, a);}
				if(i+1 < width && a+1 < height){
				closePixels[5] = image.getRGB(i+1, a+1);}
				if(a-1 > -1){
				closePixels[6] = image.getRGB(i, a-1);
				closeExecutedPixels[3] = newImage.getRGB(i, a-1);}
				if(a+1 < height){
				closePixels[7] = image.getRGB(i, a+1);}
				
				int b = 0;
				
				boolean nearbyPixelAlreadyPoint = false;
				for(int z = 0; z < closeExecutedPixels.length; z++)
				{
					b = (closeExecutedPixels[z]) & 0x000000FF;
					
					if(b == 0)
					{
						nearbyPixelAlreadyPoint = true;
						break;
					}
				}
				
				if(!nearbyPixelAlreadyPoint)
				{
					int surroundingPixelsCount = 0;
					for(int z = 0; z < closePixels.length; z++)
					{
						b = (closePixels[z]) & 0x000000FF;
						
						//System.out.println(i + " " + a + " " + b + " " + z);
						
						if(b == 0)
						{
							//pixel is part of road so add to surrounding pixel count
							surroundingPixelsCount++;
						}
					}
					
					if(surroundingPixelsCount >= 5)
					{
						newImage.setRGB(i, a, (0 << 16) | (0 << 8) | (0));
						dataSet.addDataPoint(i, a);
					}
					else
					{
						newImage.setRGB(i, a, (255 << 16) | (255 << 8) | (255));
					}
				}
				else
				{
					newImage.setRGB(i, a, (255 << 16) | (255 << 8) | (255));
				}
				}
				else
				{
					newImage.setRGB(i, a, (255 << 16) | (255 << 8) | (255));
				}
			}
		}

		return dataSet;
	}
	
	public static BufferedImage getRoadPointsImage(RoadData dataSet)
	{
		int width = dataSet.getWidth();
		int height = dataSet.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		
		Graphics g = newImage.getGraphics();
		
		g.setColor(new Color(0, 0, 0, 255));
		g.fillRect(0, 0, width, height);
		
		for(int i = 0; i < dataSet.getNumberOfPoints(); i++)
		{
			newImage.setRGB(dataSet.getDataPoint(i).getXValue(), dataSet.getDataPoint(i).getYValue(), (255 << 16) | (255 << 8) | (255));
		}
		
		return newImage;
	}
	
	public static BufferedImage getJoinedRoadPoints(RoadData dataSet)
	{
		int width = dataSet.getWidth();
		int height = dataSet.getHeight();
		BufferedImage newImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = newImage.getGraphics();
		
		g.setColor(new Color(0,0,0,255));
		g.fillRect(0, 0, width, height);
		
		g.setColor(new Color(255,255,255,255));
		for(int i = 0; i < dataSet.getNumberOfPoints()-1; i++)
		{
			double minimumDistance = -1;
			int currentPoint = i+1;
			for(int a = i+1; a < dataSet.getNumberOfPoints(); a++)
			{
				if(minimumDistance < 0)
				{
					minimumDistance = Math.sqrt(Math.pow(dataSet.getDataPoint(i).getXValue() - dataSet.getDataPoint(a).getXValue(), 2) + Math.pow(dataSet.getDataPoint(i).getYValue() - dataSet.getDataPoint(a).getYValue(),2));
				}
				else
				{
					double newDistance = Math.sqrt(Math.pow(dataSet.getDataPoint(i).getXValue() - dataSet.getDataPoint(a).getXValue(), 2) + Math.pow(dataSet.getDataPoint(i).getYValue() - dataSet.getDataPoint(a).getYValue(),2));
					if(newDistance < minimumDistance)
					{
						minimumDistance = newDistance;
						currentPoint = a;
					}
				}
			}
			
			System.out.println(i + " " + currentPoint);
			g.drawLine(dataSet.getDataPoint(i).getXValue(), dataSet.getDataPoint(i).getYValue(), dataSet.getDataPoint(currentPoint).getXValue(), dataSet.getDataPoint(currentPoint).getYValue());
		}
		
		return newImage;
	}
}
