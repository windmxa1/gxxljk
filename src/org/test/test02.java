package org.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dao.ZConnectCtlDao;
import org.dao.imp.ZConnectCtlDaoImp;

public class test02 {
	public static void main(String[] args) {
		List<Long> l1= new ArrayList<>();
		List<Long> l2= new ArrayList<>();
//		System.out.println(l2.isEmpty());
		l1.add(1l);
		l1.add(2l);
		l1.add(3l);
		l1.add(7l);
		l1.add(123l);
		l1.add(54l);
		l1.add(588l);
		l1.add(78l);
//		
		l2.add(1l);
		l2.add(2l);
		l2.add(3l);
//		
		System.out.println(l2.containsAll(l1));
//		ZAlarmDao aDao = new ZAlarmDaoImp();
//		System.out.println(aDao.getUnACKAlarmIds());
		ZConnectCtlDao cDao = new ZConnectCtlDaoImp();
		System.out.println(cDao.resetConnectCount());
		
	}
}
