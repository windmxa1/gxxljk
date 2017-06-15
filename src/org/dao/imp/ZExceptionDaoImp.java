package org.dao.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dao.ZExceptionDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZException;
import org.util.HibernateSessionFactory;
import org.view.VException;
import org.view.VExceptionId;

public class ZExceptionDaoImp implements ZExceptionDao {

	@Override
	public boolean addException(ZException e) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZException exception = new ZException(e.getTime(), e.getAck(),
					e.getDescription(), e.getHost());
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
	public boolean updateAllAck() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			SQLQuery sqlQuery = session
					.createSQLQuery("update z_exception set ack=1 where ack=0");
			sqlQuery.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List getExceptionList(Integer start, Integer limit, Integer type,
			Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "";
			switch (type) {
			case 0:
				sql = "select * from v_exception v where ack=0 and v.host in( select gh.host from z_gx_host gh, z_user_belong ub where gh.belong = ub.belong and ub.user_id=?) order by id desc";
				break;
			case 1:
				sql = "select * from v_exception v where ack=1 and v.host in( select gh.host from z_gx_host gh, z_user_belong ub where gh.belong = ub.belong and ub.user_id=?) order by id desc";
				break;
			case 2:
				sql = "select * from v_exception v where v.host in( select gh.host from z_gx_host gh, z_user_belong ub where gh.belong = ub.belong and ub.user_id=?) order by id desc";
			default:
				break;
			}

			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VException.class);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				sqlQuery.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				sqlQuery.setMaxResults(limit);
			}
			sqlQuery.setFirstResult(start);
			sqlQuery.setParameter(0, userid);
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
	public long getCount(Integer type, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			switch (type) {
			case 0:
				sql = "select count(e.id) from ZException e where ack=0 and e.host in( select gh.host from ZGxHost gh, ZUserBelong ub where gh.belong = ub.belong and ub.userId=?)";
				break;
			case 1:
				sql = "select count(e.id) from ZException e where ack=1 and e.host in( select gh.host from ZGxHost gh, ZUserBelong ub where gh.belong = ub.belong and ub.userId=?)";
				break;
			case 2:
				sql = "select count(e.id) from ZException e where e.host in( select gh.host from ZGxHost gh, ZUserBelong ub where gh.belong = ub.belong and ub.userId=?)";
				break;
			default:
				break;
			}
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
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
	public List getUnACKExceptionList(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();
			String sql = "";
			sql = "select * from v_exception v where ack=0 and v.host in( select gh.host from z_gx_host gh, z_user_belong ub where gh.belong = ub.belong and ub.user_id=?) order by id desc";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VException.class);
			sqlQuery.setParameter(0, userid);
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

	@Override
	public List<VExceptionId> getExceptionList(Integer start, Integer limit,
			Integer type, String start_time, String end_time, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();
			String sql = "";
			switch (type) {
			case 0:
				sql = "select * from v_exception v where ack=0 and (v.vtime between ? and ?) and v.host in( select gh.host from z_gx_host gh, z_user_belong ub where gh.belong = ub.belong and ub.user_id=?) order by id desc";
				break;
			case 1:
				sql = "select * from v_exception v where ack=1 and (v.vtime between ? and ?) and v.host in( select gh.host from z_gx_host gh, z_user_belong ub where gh.belong = ub.belong and ub.user_id=?) order by id desc";
				break;
			case 2:
				sql = "select * from v_exception v where (v.vtime between ? and ?) and v.host in( select gh.host from z_gx_host gh, z_user_belong ub where gh.belong = ub.belong and ub.user_id=?) order by id desc";
				break;
			default:
				break;
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VException.class);
			sqlQuery.setParameter(0, start_time);
			sqlQuery.setParameter(1, end_time);
			sqlQuery.setParameter(2, userid);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				sqlQuery.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				sqlQuery.setMaxResults(limit);
			}
			sqlQuery.setFirstResult(start);

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

	@Override
	public Long getExceptionCount(Integer type, String start_time,
			String end_time, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "";
			switch (type) {
			case 0:
				sql = "select count(v.id.id) from VException v where v.id.ack=0 and (v.id.vtime between ? and ?) and v.id.host in( select gh.host from ZGxHost gh, ZUserBelong ub where gh.belong = ub.belong and ub.userId=?) order by id desc";
				break;
			case 1:
				sql = "select count(v.id.id) from VException v where v.id.ack=1 and (v.id.vtime between ? and ?) and v.id.host in( select gh.host from ZGxHost gh, ZUserBelong ub where gh.belong = ub.belong and ub.userId=?) order by id desc";
				break;
			case 2:
				sql = "select count(v.id.id) from VException v where (v.id.vtime between ? and ?) and v.id.host in( select gh.host from ZGxHost gh, ZUserBelong ub where gh.belong = ub.belong and ub.userId=?) order by id desc";
				break;
			default:
				break;
			}
			Query query = session.createQuery(sql);
			query.setParameter(0, start_time);
			query.setParameter(1, end_time);
			query.setParameter(2, userid);
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
	public boolean deleteException(Long start_clock, Long end_clock, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from ZException e where (e.time between ? and ?) and e.time!=? and e.host in( select gh.host from ZGxHost gh, ZUserBelong ub where gh.belong = ub.belong and ub.userId=?)";
			Query query = session.createQuery(sql);
			query.setParameter(0, start_clock);
			query.setParameter(1, end_clock);
			query.setParameter(2, end_clock);
			query.setParameter(3, userid);
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
	public Set<Long> getUnACKExceptionIds(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select e.id from ZException e where e.ack = 0 and e.host in( select gh.host from ZGxHost gh, ZUserBelong ub where gh.belong = ub.belong and ub.userId=?)";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			List<Long> list = (List<Long>) query.list();
			Set<Long> set = new HashSet<>();
			set.addAll(list);
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			switch (type) {
			case 0:
				sql = "select * from v_exception v where ack=0  order by id desc";
				break;
			case 1:
				sql = "select * from v_exception v where ack=1  order by id desc";
				break;
			case 2:
				sql = "select * from v_exception v order by id desc";
			default:
				break;
			}

			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VException.class);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				sqlQuery.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				sqlQuery.setMaxResults(limit);
			}
			sqlQuery.setFirstResult(start);
			
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
	public long getCount(Integer type ) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			switch (type) {
			case 0:
				sql = "select count(e.id) from ZException e where ack=0 ";
				break;
			case 1:
				sql = "select count(e.id) from ZException e where ack=1 ";
				break;
			case 2:
				sql = "select count(e.id) from ZException e ";
				break;
			default:
				break;
			}
			Query query = session.createQuery(sql);
			
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
	public List getUnACKExceptionList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();
			String sql = "";
			sql = "select * from v_exception v where ack=0  order by id desc";
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

	@Override
	public List<VExceptionId> getExceptionList(Integer start, Integer limit,
			Integer type, String start_time, String end_time ) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();
			String sql = "";
			switch (type) {
			case 0:
				sql = "select * from v_exception v where ack=0 and (v.vtime between ? and ?)  order by id desc";
				break;
			case 1:
				sql = "select * from v_exception v where ack=1 and (v.vtime between ? and ?)  order by id desc";
				break;
			case 2:
				sql = "select * from v_exception v where (v.vtime between ? and ?)  order by id desc";
				break;
			default:
				break;
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VException.class);
			sqlQuery.setParameter(0, start_time);
			sqlQuery.setParameter(1, end_time);
			
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				sqlQuery.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				sqlQuery.setMaxResults(limit);
			}
			sqlQuery.setFirstResult(start);

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

	@Override
	public Long getExceptionCount(Integer type, String start_time,
			String end_time) {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "";
			switch (type) {
			case 0:
				sql = "select count(v.id.id) from VException v where v.id.ack=0 and (v.id.vtime between ? and ?) order by id desc";
				break;
			case 1:
				sql = "select count(v.id.id) from VException v where v.id.ack=1 and (v.id.vtime between ? and ?) order by id desc";
				break;
			case 2:
				sql = "select count(v.id.id) from VException v where (v.id.vtime between ? and ?) order by id desc";
				break;
			default:
				break;
			}
			Query query = session.createQuery(sql);
			query.setParameter(0, start_time);
			query.setParameter(1, end_time);
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
	public boolean deleteException(Long start_clock, Long end_clock ) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from ZException e where (e.time between ? and ?) and e.time!=? ";
			Query query = session.createQuery(sql);
			query.setParameter(0, start_clock);
			query.setParameter(1, end_clock);
			query.setParameter(2, end_clock);
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
	public Set<Long> getUnACKExceptionIds( ) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select e.id from ZException e where e.ack = 0 ";
			Query query = session.createQuery(sql);
			
			List<Long> list = (List<Long>) query.list();
			Set<Long> set = new HashSet<>();
			set.addAll(list);
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
