package org.dao.imp;

import java.util.List;

import org.dao.ZGxHostDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZGxHost;
import org.util.HibernateSessionFactory;

import com.itextpdf.text.pdf.TSAClient;

public class ZGxHostDaoImp implements ZGxHostDao {
	public List<ZGxHost> getAllList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZGxHost");
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			List<ZGxHost> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select count(id) from ZGxHost");
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Boolean updateOnStatus(String host) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update ZGxHost set status=1 where host=? ");
			query.setParameter(0, host);
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

	@Override
	public Boolean updateOffStatus(String host) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update ZGxHost set status=1 where host=?");
			query.setParameter(0, host);
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

	@Override
	public Long addGxHost(ZGxHost host) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts= session.beginTransaction();
			Long id = (Long) session.save(host);
			ts.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Boolean delGxHost(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("delete from ZGxHost where id=?");
			query.setParameter(0, id);
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

	@Override
	public ZGxHost getGxHost(String host) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZGxHost where host=?");
			query.setParameter(0, host);
			query.setMaxResults(1);
			ZGxHost gxhost = (ZGxHost) query.uniqueResult();
			return gxhost;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
