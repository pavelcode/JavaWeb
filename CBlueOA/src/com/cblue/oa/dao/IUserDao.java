package com.cblue.oa.dao;

import com.cblue.oa.base.IBaseDao;
import com.cblue.oa.entity.User;

public interface IUserDao extends IBaseDao<User> {

	public int getLoginName(String loginName);

	public User login(User model);

}
