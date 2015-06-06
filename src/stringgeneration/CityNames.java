package stringgeneration;

public class CityNames {
	
	private static String[] cityName1 = new String[]
			{
				"New",
				"",
				"South",
				"North",
				"Victor",
				"Fort"
			};
	private static String[] cityName2 = new String[]
			{
				"York",
				"#hampton",
				"#stoke",
				"#bury",
				"#mouth",
				"Quay",
				"London",
				"Yorkshire",
				"Rotterdam",
				"Quays",
				"Keil",
				"City",
				"Orleans",
				"land",
				"Victoria",
				"Anchorage"
			};
			
	public static String getCityName(long cityID)
	{
		long cityID_first16 = (cityID & 0xFFFF) % cityName1.length;
		long cityID_second16 = (cityID & 0xFFFF0000) % cityName2.length;
		
		cityID_first16 = (int)Math.sqrt(cityID_first16*cityID_first16);
		cityID_second16 = (int)Math.sqrt(cityID_second16 * cityID_second16);
		
		if(cityName1[(int)cityID_first16].length() == 0)
		{
			return cityName2[(int)cityID_second16];
		}
		if(cityName2[(int)cityID_second16].charAt(0) == '#')
		{
			return cityName1[(int)cityID_first16] + (cityName2[(int)cityID_second16].substring(1, cityName2[(int)cityID_second16].length()));
		}
		
		return cityName1[(int)cityID_first16] + " " + cityName2[(int)cityID_second16];
	}
}
