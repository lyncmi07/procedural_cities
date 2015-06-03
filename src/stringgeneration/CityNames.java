package stringgeneration;

public class CityNames {
	
	private static String[] cityName1 = new String[]
			{
				"New",
				"",
				"Klagen",
				"South",
				"North",
				"Los",
				"Las",
				"San",
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
				"#furt",
				"Nancy",
				"Reims",
				"Paris",
				"#burg",
				"#grad",
				"Quays",
				"Keil",
				"City",
				"Orleans",
				"land",
				"Victoria",
				"#bank",
				"Anchorage"
			};
			
	public static String getCityName(long cityID)
	{
		long cityID_first16 = (cityID & 0xFFFF) % cityName1.length;
		long cityID_second16 = (cityID & 0xFFFF0000) % cityName2.length;
		
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
