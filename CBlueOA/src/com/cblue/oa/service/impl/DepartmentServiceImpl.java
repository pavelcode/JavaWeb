package com.cblue.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IDepartmentDao;
import com.cblue.oa.entity.Department;
import com.cblue.oa.service.IDepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;

	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return departmentDao.getAll();
	}

	public Department getById(Long id) {
		// TODO Auto-generated method stub
		return departmentDao.getById(id);
	}

	public void save(Department model) {
		// TODO Auto-generated method stub
		departmentDao.add(model);
	}

	public void update(Department department) {
		// TODO Auto-generated method stub
		departmentDao.update(department);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		departmentDao.delete(id);
	}

	public List<Department> getTopDepartmentList() {
		// TODO Auto-generated method stub
		return departmentDao.getTopDepartmentList();
	}

	public List<Department> getChildrenDepartmentList(Long parentId) {
		// TODO Auto-generated method stub
		return departmentDao.getChildrenDepartmentList(parentId);
	}
}
