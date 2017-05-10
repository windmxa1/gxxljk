package org.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.dao.ZAlarmDao;
import org.dao.ZExceptionDao;
import org.dao.imp.ZAlarmDaoImp;
import org.dao.imp.ZExceptionDaoImp;
import org.model.ZAlarm;
import org.model.ZException;
import org.view.VAlarmId;

public class test01 {
	public static void main(String[] args) {
		ZAlarmDao aDao = new ZAlarmDaoImp();
		ZExceptionDao eDao = new ZExceptionDaoImp();
		// aDao.updateAck(1);
		for (int i = 0; i < 10; i++) {
//			ZAlarm a1 = new ZAlarm(1491321600L, "874", "alarm", "1", 0, "18","localhost");
			ZAlarm a2 = new ZAlarm(1491408000L, "874", "alarm", "1", 1, "18","localhost");
//			ZAlarm a3 = new ZAlarm(1491494400L, "874", "alarm", "1", 1, "18","localhost");
//			ZAlarm a4 = new ZAlarm(1491580800L, "874", "alarm", "1", 1, "18","localhost");
//			ZAlarm a5 = new ZAlarm(1491667200L, "874", "alarm", "1", 1, "18","localhost");
//			aDao.addAlarm(a1);
			aDao.addAlarm(a2);
//			aDao.addAlarm(a3);
//			aDao.addAlarm(a4);
//			aDao.addAlarm(a5);
		}

	}
}
