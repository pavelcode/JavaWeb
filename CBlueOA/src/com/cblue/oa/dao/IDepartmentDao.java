package com.cblue.oa.dao;

import java.util.List;

import com.cblue.oa.base.IBaseDao;
import com.cblue.oa.entity.Department;

public interface IDepartmentDao extends IBaseDao<Department> {

	public List<Department> getTopDepartmentList();

	public List<Department> getChildrenDepartmentList(Long parentId);

}
