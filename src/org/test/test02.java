package org.test;

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
		ZAlarmDao aDao = new ZAlarmDaoImp();
		List<ZAlarm> list1 = aDao.getAlarmList(0, 6, 0);
		List<ZAlarm> list2 = aDao.getAlarmList(0, 5, 0);
		
		System.out.println(list1.containsAll(list2));

	}
}
