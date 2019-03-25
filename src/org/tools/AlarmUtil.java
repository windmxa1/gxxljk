package org.tools;

public class AlarmUtil {
	public static String getDescription(String alarm_level){
		String description = "";
		switch(Integer.parseInt(alarm_level)){
		case -1:
			description = "断线";
			break;
		case -2:
			description = "恢复";
			break;
		case 1:
			description = "触动光芯";
			break;
		case 2:
			description = "触碰接头盒";
			break;
		case 3:
			description = "刮擦光缆或轻微触碰光缆或触碰光缆";
			break;
		// case 4:
		// description = "";
		// break;
		}
		return description;
	}
}
