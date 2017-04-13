package org.tools;

public class AlarmUtil {
	public static String getDescription(String alarm_level){
		String description = "";
		switch(Integer.parseInt(alarm_level)){
		case 1:
			description = "轻微触碰光缆或触碰光缆";
			break;
		case 2:
			description = "刮擦光缆";
			break;
		case 3:
			description = "触碰接头盒";
			break;
		case 4:
			description = "触动光芯";
			break;
		}
		return description;
	}
}
