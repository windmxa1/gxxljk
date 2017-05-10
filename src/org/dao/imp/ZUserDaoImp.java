package org.dao.imp;

import org.dao.ZUserDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZUser;
import org.model.ZUserBelong;
import org.model.ZUserRole;
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
	public long addUser(String username, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			ZUser user = new ZUser(username, password);
			long id = (Long) session.save(user);
			ts.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public ZUser getUser(String username) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZUser where username=?");
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

	@Override
	public boolean deleteUser(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZUser u = (ZUser) session.load(ZUser.class, id);
			session.delete(u);
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
	public boolean updateUser(long id, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update ZUser u set u.password=? where u.id=?");
			query.setParameter(0, password);
			query.setParameter(1, id);
			query.executeUpdate();

			ts.commit();
			HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUB(long uid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			SQLQuery sqlQuery = session
					.createSQLQuery("delete from z_user_belong where user_id=?");
			sqlQuery.setParameter(0, uid);
			sqlQuery.executeUpdate();
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
	public boolean addUB(long uid, String belong) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZUserBelong ub = new ZUserBelong(uid, belong);
			session.save(ub);
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
	public String getUserBelong(Long uid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select belong from v_ur where uid=?";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, uid);
			String userBelong = (String) sqlQuery.uniqueResult();
			return userBelong;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
