package org.dao.imp;

import org.dao.ZWebsocketCtlDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZWebsocketCtl;
import org.util.HibernateSessionFactory;

public class ZWebsocketCtlDaoImp implements ZWebsocketCtlDao {

	@Override
	public Boolean saveOrupdate(String host, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update ZWebsocketCtl set host=? where userid=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, host);
			query.setParameter(1, userid);
			if (query.executeUpdate() > 0) {
				// System.out.println("更新成功1");
			} else {
				ZWebsocketCtl websocketCtl = new ZWebsocketCtl(host, userid);
				session.save(websocketCtl);
				// System.out.println("更新成功2");
			}
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
	public ZWebsocketCtl getWebsocketCtl(String host, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from ZWebsocketCtl where host=? and userid=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, host);
			query.setParameter(1, userid);
			query.setMaxResults(1);
			ZWebsocketCtl websocketCtl = (ZWebsocketCtl) query.uniqueResult();

			return websocketCtl;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public ZWebsocketCtl getWebsocketCtl(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from ZWebsocketCtl where userid=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setMaxResults(1);
			ZWebsocketCtl websocketCtl = (ZWebsocketCtl) query.uniqueResult();

			return websocketCtl;
		} catch (Exception e) {
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean deleteAll() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "truncate table z_websocket_ctl";
			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
