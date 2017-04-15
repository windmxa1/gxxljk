package org.dao.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dao.ZAlarmDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZAlarm;
import org.util.HibernateSessionFactory;
import org.view.VAlarm;

public class ZAlarmDaoImp implements ZAlarmDao {

	@Override
	public boolean addAlarm(ZAlarm a) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			ZAlarm alarm = new ZAlarm();
			alarm.setAck(a.getAck());
			alarm.setDescription(a.getDescription());
			alarm.setDistance(a.getDistance());
			alarm.setLevel(a.getLevel());
			alarm.setOccurTime(a.getOccurTime());
			alarm.setLastTime(a.getLastTime());
			alarm.setHost(a.getHost());
			session.save(alarm);
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
	public boolean updateAck(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			SQLQuery sqlQuery = session
					.createSQLQuery("update z_alarm set ack=1 where id=?");
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
	public List getAlarmList(Integer start, Integer limit, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "";
			switch (type) {
			case 0:
				sql = "select * from v_alarm where ack=0 order by id desc";
				break;
			case 1:
				sql = "select * from v_alarm where ack=1 order by id desc";
				break;
			case 2:
				sql = "select * from v_alarm order by id desc";
				break;
			default:
				break;
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VAlarm.class);
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

			List<VAlarm> li = sqlQuery.list();
			List list = new ArrayList<>();
			for (VAlarm a : li) {
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
	public long getCount(Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			switch (type) {
			case 0:
				sql = "select count(id) from ZAlarm where ack=0 ";
				break;
			case 1:
				sql = "select count(id) from ZAlarm where ack=1 ";
				break;
			case 2:
				sql = "select count(id) from ZAlarm ";
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
	public long getUnACKAlarmCount() {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("select count(a.id) from ZAlarm  a where a.host in( select gh.host from z_gx_host gh, z_user_belong ub where gh.belong = ub.belong and ub.user_id=?)");
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
	public List getUnACKAlarmList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();
			String sql = "";
			sql = "select * from v_alarm where ack=0 order by id desc";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VAlarm.class);

			List<VAlarm> li = sqlQuery.list();
			List list = new ArrayList<>();
			for (VAlarm a : li) {
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
	public List getAlarmList(Integer start, Integer limit, Integer type,
			String start_time, String end_time) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();
			String sql = "";
			switch (type) {
			case 0:
				sql = "select * from v_alarm where ack=0 and vtime>? and vtime<? order by id desc";
				break;
			case 1:
				sql = "select * from v_alarm where ack=1 and vtime>? and vtime<?  order by id desc";
				break;
			case 2:
				sql = "select * from v_alarm where vtime >  ? and vtime<?  order by id desc";
				break;
			default:
				break;
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VAlarm.class);
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

			List<VAlarm> li = sqlQuery.list();
			List list = new ArrayList<>();
			for (VAlarm a : li) {
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
	public Long getAlarmCount(Integer type, String start_time, String end_time) {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "";
			switch (type) {
			case 0:
				sql = "select count(*) from VAlarm v where v.id.ack=0 and v.id.vtime>? and v.id.vtime<? order by id desc";
				break;
			case 1:
				sql = "select count(*) from VAlarm v where v.id.ack=1 and v.id.vtime>? and v.id.vtime<?  order by id desc";
				break;
			case 2:
				sql = "select count(*) from VAlarm where id.vtime>? and id.vtime<?  order by id desc";
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
			// TODO: handle exception
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean deleteAlarm(Long start_clock, Long end_clock) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from ZAlarm where occurTime>? and occurTime<?";
			Query query = session.createQuery(sql);
			query.setParameter(0, start_clock);
			query.setParameter(1, end_clock);
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
	public Set<Long> getUnACKAlarmIds() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select id from ZAlarm where ack = 0";
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
