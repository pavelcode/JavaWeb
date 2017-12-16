package com.cblue.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IPrivilegeDao;
import com.cblue.oa.entity.Privilege;
import com.cblue.oa.service.IPrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl implements IPrivilegeService {
	
	@Autowired
	private IPrivilegeDao privilegeDao;

	public List<Privilege> getAll() {
		// TODO Auto-generated method stub
		return privilegeDao.getAll();
	}

	public List<Privilege> getByIds(Long[] privilegeIds) {
		// TODO Auto-generated method stub
		return privilegeDao.getByIds(privilegeIds);
	}

	public List<Privilege> getTopList() {
		// TODO Auto-generated method stub
		return privilegeDao.getTopList();
	}

	public List<String> getAllUrl() {
		// TODO Auto-generated method stub
		return privilegeDao.getAllUrl();
	}

}
