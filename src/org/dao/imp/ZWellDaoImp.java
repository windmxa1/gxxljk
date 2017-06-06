package org.dao.imp;

import java.util.List;

import org.dao.ZWellDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZWell;
import org.util.HibernateSessionFactory;

public class ZWellDaoImp implements ZWellDao {

	@Override
	public Long saveOrUpdate(ZWell zWell) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Long id = 0L;
			if (zWell.getId() != null) {
				session.update(zWell);
			} else {
				id = (Long) session.save(zWell);
			}
			ts.commit();
			return id;
		} catch (Exception e) {
			return -1L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from ZWell where id = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<ZWell> getList(Integer start, Integer limit, Boolean isCentral,
			Long userId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (isCentral) {
				sql = "from ZWell";
				query = session.createQuery(sql);
			} else {
				sql = "from ZWell zw where zw.hostId in (select gh.id from ZGxHost gh,ZUserBelong ub where ub.userId= ? and ub.belong = gh.belong )";
				query = session.createQuery(sql);
				query.setParameter(0, userId);
			}
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setFirstResult(start);
			List<ZWell> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCount(Boolean isCentral, Long userId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (isCentral) {
				sql = "select count(id) from ZWell";
				query = session.createQuery(sql);
			} else {
				sql = "select count(id) from ZWell zw where zw.hostId in (select gh.id from ZGxHost gh,ZUserBelong ub where ub.userId= ? and ub.belong = gh.belong )";
				query = session.createQuery(sql);
				query.setParameter(0, userId);
			}
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
	public List<ZWell> getList(Integer start, Integer limit, Long hostId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from ZWell where hostId = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, hostId);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			query.setFirstResult(start);
			List<ZWell> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCount(Long hostId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from ZWell where hostId= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, hostId);
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

}
