package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ZLogDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZLog;
import org.util.HibernateSessionFactory;
import org.view.VLog;

public class ZLogDaoImp implements ZLogDao{

	@Override
	public boolean addLog(ZLog l) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			ZLog log = new ZLog(l.getUsername(),l.getOperation(),l.getTime());
			session.save(log);
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
	public List getLogList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			String sql ="select * from v_log order by id desc";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VLog.class);
			if(start==null)
				start =0;
			if(limit==null)
				limit=15;
			sqlQuery.setFirstResult(start);
			sqlQuery.setMaxResults(limit);
			
			List<VLog> li = sqlQuery.list();
			List list = new ArrayList<>();
			for(VLog l:li){
				list.add(l.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session.createQuery("select count(id) from ZLog");
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

}
