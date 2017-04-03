package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

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
			if (limit == null)
				limit = 15;
			sqlQuery.setFirstResult(start);
			sqlQuery.setMaxResults(limit);

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
	public long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session.createQuery("select count(id) from ZAlarm");
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
					.createQuery("select count(id) from ZAlarm where ack=0");
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
				sql = "select * from v_alarm where vtime>? and vtime<?  order by id desc";
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
			if (limit == null)
				limit = 15;
			sqlQuery.setFirstResult(start);
			sqlQuery.setMaxResults(limit);

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
				sql = "select count(id) from v_alarm where ack=0 and vtime>? and vtime<? order by id desc";
				break;
			case 1:
				sql = "select count(id) from v_alarm where ack=1 and vtime>? and vtime<?  order by id desc";
				break;
			case 2:
				sql = "select count(id) from v_alarm where vtime>? and vtime<?  order by id desc";
				break;
			default:
				break;
			}
			Query query = session.createSQLQuery(sql);
			query.setParameter(0, start_time);
			query.setParameter(1, end_time);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}finally{
			
		}
	}

}
