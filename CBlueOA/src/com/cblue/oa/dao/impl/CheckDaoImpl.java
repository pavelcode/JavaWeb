package com.cblue.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cblue.oa.base.BaseDaoImpl;
import com.cblue.oa.dao.ICheckDao;
import com.cblue.oa.entity.Check;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

@Repository
public class CheckDaoImpl extends BaseDaoImpl<Check> implements ICheckDao {

	public List<Check> getAllCheckById(Long id) {
		// TODO Auto-generated method stub
		String hql="from Check c where c.apply.id=? order by c.checkDate desc";
		return 	getSession().createQuery(hql).setLong(0, id).list();
	}

	
}
