package org.dao.imp;

import org.dao.ZConnectCtlDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZConnectCtl;
import org.util.HibernateSessionFactory;

public class ZConnectCtlDaoImp implements ZConnectCtlDao{

	@Override
	public Long insertConnect(ZConnectCtl connectState) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Long id = 0L;
			if (connectState.getId() != null) {
				session.update(connectState);
				id = connectState.getId();
			} else {
				id = (Long) session.save(connectState);
			}
			ts.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public ZConnectCtl getConnect(Long userid, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from ZConnectCtl where userid=? and type=?");
			query.setParameter(0, userid);
			query.setParameter(1, type);
			query.setMaxResults(1);
			ZConnectCtl connectState = (ZConnectCtl) query.uniqueResult();
			return connectState;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Integer getConnectCount(Long userid, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select c.count from ZConnectCtl c where userid = ? and type = ?");
			query.setMaxResults(1);
			query.setParameter(0, userid);
			query.setParameter(1, type);
			query.setMaxResults(1);
			Integer count = (Integer) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean resetConnectCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update ZConnectCtl c set c.count=0");
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
