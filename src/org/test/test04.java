package org.test;

import java.util.List;

import org.dao.ZGxHostDao;
import org.dao.imp.ZGxHostDaoImp;

public class test04 {
	public static void main(String[] args) {
		ZGxHostDao gDao = new ZGxHostDaoImp();
		List<String> li = gDao.getBelongList();
		for(String a:li){
			System.out.println(a);
		}
	}
}
