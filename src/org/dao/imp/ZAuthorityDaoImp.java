package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ZAuthorityDao;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory;
import org.view.VRa;
import org.view.VRaId;

public class ZAuthorityDaoImp implements ZAuthorityDao{

	@Override
	public List getAuthorityList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getRAList(long rid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			SQLQuery sqlQuery = session.createSQLQuery("select * from v_ra where rid=?");
			sqlQuery.setParameter(0, rid);
			sqlQuery.addEntity(VRa.class);
			List<VRa> list = sqlQuery.list();
			List<VRaId> li = new ArrayList<>();
			for(VRa v:list){
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

}
