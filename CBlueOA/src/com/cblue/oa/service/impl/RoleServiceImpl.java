package com.cblue.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IRoleDao;
import com.cblue.oa.entity.Role;
import com.cblue.oa.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		return roleDao.getAll();
	}

	public void save(Role model) {
		// TODO Auto-generated method stub
		roleDao.add(model);
	}

	public void delete(Role model) {
		// TODO Auto-generated method stub
		roleDao.delete(model.getId());
	}

	public Role getById(Role model) {
		// TODO Auto-generated method stub
		return roleDao.getById(model.getId());
	}

	public void update(Role model) {
		// TODO Auto-generated method stub
		roleDao.update(model);
	}

	public List<Role> getByIds(Long[] roleIdList) {
		// TODO Auto-generated method stub
		return roleDao.getByIds(roleIdList);
		
	}
	

}
