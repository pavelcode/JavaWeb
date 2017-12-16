package com.cblue.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cblue.oa.base.BaseDaoImpl;
import com.cblue.oa.dao.IPrivilegeDao;
import com.cblue.oa.entity.Privilege;


@Repository
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements
		IPrivilegeDao {

	public List<Privilege> getTopList() {
		// TODO Auto-generated method stub
		String hql = "from Privilege p where p.parent is null";
		return getSession().createQuery(hql).list();
	}

	public List<String> getAllUrl() {
		// TODO Auto-generated method stub
		String hql = "select p.url from Privilege p where p.url is not null";
		return getSession().createQuery(hql).list();
	}
	
	

}
