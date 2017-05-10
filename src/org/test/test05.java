package org.test;

import org.dao.imp.ZWebsocketCtlDaoImp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZWebsocketCtl;
import org.util.HibernateSessionFactory;


public class test05 {
	public static void main(String[] args) {
		
		String host = "192.1.1.1";
		Long userid = 2L;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "update ZWebsocketCtl set host=? where userid=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, host);
			query.setParameter(1, userid);
			if(query.executeUpdate()>0){
				System.out.println("更新成功1");
			}else {
				ZWebsocketCtl websocketCtl = new ZWebsocketCtl(host, userid);
				session.save(websocketCtl);
				System.out.println("更新成功2");
			}
			ts.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
}
