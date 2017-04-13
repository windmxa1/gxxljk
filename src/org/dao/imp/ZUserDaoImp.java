package org.dao.imp;

import org.dao.ZUserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZUser;
import org.util.HibernateSessionFactory;

public class ZUserDaoImp implements ZUserDao {

	@Override
	public ZUser getUser(String username, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from ZUser where username=? and password=?");
			query.setParameter(0, username);
			query.setParameter(1, password);
			query.setMaxResults(1);
			ZUser u = (ZUser) query.uniqueResult();
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean addUser(String username, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			ZUser user = new ZUser(username, password);
			session.save(user);
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	public ZUser getUser(String username) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from ZUser where username=?");
			query.setParameter(0, username);
			query.setMaxResults(1);
			ZUser u = (ZUser) query.uniqueResult();
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return new ZUser();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
