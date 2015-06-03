package stringgeneration;

public class GreenSpaceNames {
	
	private static String[] parkName1 = new String[]
			{
				"New",
				"The",
				"Central",
				"South",
				"North",
				"West",
				"East",
				"Elizabeth",
				"Royal",
				"Watership",
				"Hyde",
				"Queen's"
			};
	private static String[] parkName2 = new String[]
			{
				"Park",
				"Forest",
				"Burrows",
				"Hills",
				"Gardens",
				"Copse",
				"Forests"
			};
	
	public static String getGreenSpaceName(long parkID)
	{
		long cityID_first16 = (parkID & 0xFFFF) % parkName1.length;
		long cityID_second16 = (parkID & 0xFFFF0000) % parkName2.length;
		
		if(parkName1[(int)cityID_first16].length() == 0)
		{
			return parkName2[(int)cityID_second16];
		}
		if(parkName2[(int)cityID_second16].charAt(0) == '#')
		{
			return parkName1[(int)cityID_first16] + (parkName2[(int)cityID_second16].substring(1, parkName2[(int)cityID_second16].length()));
		}
		
		return parkName1[(int)cityID_first16] + " " + parkName2[(int)cityID_second16];
	}
}
