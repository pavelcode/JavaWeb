package com.cblue.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cblue.oa.base.BaseDaoImpl;
import com.cblue.oa.dao.IDepartmentDao;
import com.cblue.oa.entity.Department;

@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements
		IDepartmentDao {

	public List<Department> getTopDepartmentList() {
		// TODO Auto-generated method stub
		String hql = "from Department d where d.parent is null";
		return getSession().createQuery(hql).list();
	}

	public List<Department> getChildrenDepartmentList(Long parentId) {
		// TODO Auto-generated method stub
		String hql = "from Department d where d.parent.id=?";
		return getSession().createQuery(hql).setLong(0, parentId).list();
	}

	
}
