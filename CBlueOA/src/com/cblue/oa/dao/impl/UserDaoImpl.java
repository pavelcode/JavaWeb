package com.cblue.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cblue.oa.base.BaseDaoImpl;
import com.cblue.oa.dao.IUserDao;
import com.cblue.oa.entity.User;
import com.cblue.oa.utils.MD5Utils;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	public int getLoginName(String loginName) {
		// TODO Auto-generated method stub
		String hql = "SELECT COUNT(*) FROM User u WHERE u.loginName=?";
		Long count = (Long)this.getSession().createQuery(hql).setString(0, loginName).uniqueResult();
		return count.intValue();
	}

	public User login(User model) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.loginName=? and u.password=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, model.getLoginName());
		query.setString(1, MD5Utils.md5(model.getPassword()));
		List<User> users = query.list();
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}


}
