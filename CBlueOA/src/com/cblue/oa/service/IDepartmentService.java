package com.cblue.oa.service;

import java.util.List;

import com.cblue.oa.entity.Department;

public interface IDepartmentService {

	public List<Department> getAll();

	public Department getById(Long id);

	public void save(Department model);

	public void update(Department department);

	public void delete(Long id);

	public List<Department> getTopDepartmentList();

	public List<Department> getChildrenDepartmentList(Long parentId);

}
