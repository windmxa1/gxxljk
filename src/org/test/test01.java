package org.test;

import org.dao.ZAlarmDao;
import org.dao.ZExceptionDao;
import org.dao.ZUserDao;
import org.dao.imp.ZAlarmDaoImp;
import org.dao.imp.ZExceptionDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.model.ZAlarm;
import org.model.ZException;
import org.model.ZUser;
import org.tools.PropertyUtils;

public class test01 {
	public static void main(String[] args) {
		ZAlarmDao aDao = new ZAlarmDaoImp();
		aDao.updateAck(1);
		ZAlarm a = new ZAlarm(1491188506L, "874", "alarm", "1", 0, "18");
		aDao.addAlarm(a);
		ZExceptionDao eDao = new ZExceptionDaoImp();
		ZException e= new ZException(1491188506L, 0,"光纤连接异常，心跳包未得到正常回应。。。");
		eDao.addException(e);
		System.out.println(aDao.getUnACKAlarmCount());
		System.out.println(eDao.getUnACKExceptionCount());
		
		
		
		
	}
}
