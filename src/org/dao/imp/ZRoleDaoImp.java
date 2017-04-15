package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ZRoleDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZRole;
import org.model.ZUserRole;
import org.util.HibernateSessionFactory;
import org.view.VUr;
import org.view.VUrId;

public class ZRoleDaoImp implements ZRoleDao{

	@Override
	public boolean addRole(String role) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts=  session.beginTransaction();
			
			ZRole r = new ZRole(role);
			session.save(r);
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
	public ZRole getRole(String role) {
		try {
			Session session  =HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from ZRole where role=?");
			query.setParameter(0, role);
			query.setMaxResults(1);
			ZRole r = (ZRole) query.uniqueResult();
			
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean addUR(long uid, long rid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts=  session.beginTransaction();
			
			ZUserRole ur = new ZUserRole(uid,rid);
			session.save(ur);
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
	public boolean deleteUR(long uid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts=  session.beginTransaction();
			
			SQLQuery sqlQuery = session.createSQLQuery("delete from z_user_role where user_id=?");
			sqlQuery.setParameter(0, uid);
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
	public List<ZRole> getRoleList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from ZRole");
			List<ZRole> rList = query.list();
			return rList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VUrId> getURList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			SQLQuery sqlQuery = session.createSQLQuery("select * from v_ur");
			sqlQuery.addEntity(VUr.class);
			
			if(start==null){
				start=0;
			}
			if(limit==null){
				limit=15;
				sqlQuery.setMaxResults(limit);
			}else if(limit==-1){
				
			}else{
				sqlQuery.setMaxResults(limit);
			}
			sqlQuery.setFirstResult(start);
			
			List<VUr> list = sqlQuery.list();
			List<VUrId> li = new ArrayList<>();
			for(VUr v:list){
				li.add(v.getId());
			}
			return li;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long getRByU(long uid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from ZUserRole where userId=?");
			query.setParameter(0, uid);
			query.setMaxResults(1);
			ZUserRole ur = (ZUserRole) query.uniqueResult();
			
			if(ur!=null){
				long roleid = ur.getRoleId();
				return roleid;
			}
			else 
				return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session.createQuery("select count(id) from ZUser");
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
