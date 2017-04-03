package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ZExceptionDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZException;
import org.util.HibernateSessionFactory;
import org.view.VException;

public class ZExceptionDaoImp implements ZExceptionDao {

	@Override
	public boolean addException(ZException e) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZException exception = new ZException(e.getTime(), e.getAck(),e.getDescription());
			session.save(exception);
			ts.commit();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean updateAck(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			SQLQuery sqlQuery = session
					.createSQLQuery("update z_exception set ack=1 where id=?");
			sqlQuery.setParameter(0, id);
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
	public List getExceptionList(Integer start, Integer limit, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "";
			if (type == 2) {
				sql = "select * from v_exception order by id desc";
			} else {
				sql = "select * from v_exception where ack=1 order by id desc";
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VException.class);
			if (start == null)
				start = 0;
			if (limit == null)
				limit = 15;
			sqlQuery.setFirstResult(start);
			sqlQuery.setMaxResults(limit);

			List<VException> li = sqlQuery.list();
			List list = new ArrayList<>();
			for (VException s : li) {
				list.add(s.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select count(id) from ZException");
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long getUnACKExceptionCount() {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("select count(id) from ZException where ack=0");
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List getUnACKExceptionList() {
		try {
			Session session = HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();
			String sql = "";
			sql = "select * from v_exception where ack=0 order by id desc";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VException.class);

			List<VException> li = sqlQuery.list();
			List list = new ArrayList<>();
			for (VException a : li) {
				list.add(a.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
