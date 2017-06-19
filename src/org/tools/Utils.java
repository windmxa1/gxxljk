package org.tools;

import org.dao.ZUserDao;
import org.dao.imp.ZUserDaoImp;

public class Utils {
	public static int ByteToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16
				| (b[0] & 0xFF) << 24;
	}
	/**
	 * 判断是否为总局工作人员
	 * 
	 * @param userid
	 */
	public static boolean isCentral(Long userid) {
		ZUserDao uDao = new ZUserDaoImp();
		if (uDao.getUserBelong(userid).contains("总局")) {
			// System.out.println("总局人员");
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 生成6位随机数
	 * 
	 * @return
	 */
	public static String ran6() {
		Double a = 100000 + Math.random() * 899999;
		return "" + a.intValue();
	}
}
