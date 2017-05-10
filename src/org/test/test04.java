package org.test;

import java.util.List;

import org.dao.ZAlarmDao;
import org.dao.ZGxHostDao;
import org.dao.imp.ZAlarmDaoImp;
import org.dao.imp.ZGxHostDaoImp;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.util.HibernateSessionFactory;

public class test04 {
	public static void main(String[] args) {
		ZAlarmDao aDao = new ZAlarmDaoImp();
		System.out.println(aDao.getAlarmCount(2, "2017-04-05", "2017-04-07", 1L));
		
//		Session session = HibernateSessionFactory.getSession();
//		Query query = session.createQuery("");
	}
}
