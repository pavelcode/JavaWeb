package com.cblue.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IUserDao;
import com.cblue.oa.entity.User;
import com.cblue.oa.service.IUserService;
import com.cblue.oa.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private  IUserDao userDao;
	
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDao.getAll();
	}

	public void save(User model) {
		// TODO Auto-generated method stub
		model.setPassword(MD5Utils.md5("123"));
		userDao.add(model);
	}

	public User getById(User model) {
		// TODO Auto-generated method stub
		return userDao.getById(model.getId());
	}

	public void update(User model) {
		// TODO Auto-generated method stub
		userDao.update(model);
	}

	public void delete(User model) {
		// TODO Auto-generated method stub
		userDao.delete(model.getId());
	}

	public int getLoginName(User model) {
		// TODO Auto-generated method stub
		return userDao.getLoginName(model.getLoginName());
	}

	public User login(User model) {
		// TODO Auto-generated method stub
		return userDao.login(model);
	}

}
