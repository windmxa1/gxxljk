package org.test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dao.ZAlarmDao;
import org.dao.ZConnectCtlDao;
import org.dao.imp.ZAlarmDaoImp;
import org.dao.imp.ZConnectCtlDaoImp;
import org.model.ZAlarm;

public class test02 {
	public static void main(String[] args) {
//		ZAlarmDao aDao = new ZAlarmDaoImp();
//		List<ZAlarm> list1 = aDao.getAlarmList(0, 6, 0);
//		List<ZAlarm> list2 = aDao.getAlarmList(0, 5, 0);
//		
//		System.out.println(list1.containsAll(list2));
//		
//		System.out.println(System.currentTimeMillis());
		
		
//		String s = "asd";
//		System.out.println(s.charAt(s.length()-1));
		
		try {
			PrintWriter pw = new PrintWriter("F:\\1");			
			pw.println("asdasdsa");
			pw.flush();
			pw.println("asdasdsa");
			pw.flush();
			pw.println("asdasdsa");
			pw.flush();
			
			pw.println("asdasdsa");
			pw.println("asdasdsa");
			pw.println("asdasdsa");
			pw.flush();
			pw.println("asdasdsa");
			pw.println("asdasdsa");
			pw.println("asdasdsa");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
