package com.cblue.oa.service;

import java.util.List;

import com.cblue.oa.entity.User;

public interface IUserService {

	public List<User> getAll();

	public void save(User model);

	public User getById(User model);

	public void update(User model);

	public void delete(User model);

	public int getLoginName(User model);

	public User login(User model);

}
