package com.cblue.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cblue.oa.base.BaseDaoImpl;
import com.cblue.oa.dao.IForumManageDao;
import com.cblue.oa.entity.ForumManage;

@Repository
public class ForumManageDaoImpl extends BaseDaoImpl<ForumManage> implements
		IForumManageDao {

	@Override
	public void add(ForumManage entity) {
		// TODO Auto-generated method stub
		super.add(entity);
		entity.setPosition(entity.getId());
	}

	@Override
	public List<ForumManage> getAll() {
		// TODO Auto-generated method stub
		String hql = "from ForumManage f order by f.position asc";
		return getSession().createQuery(hql).list();
	}

	public void moveUp(Long id) {
		// TODO Auto-generated method stub
		//SELECT * FROM oa_forummanage WHERE POSITION < 3 ORDER BY POSITION DESC LIMIT 0,1
		ForumManage forumManage = getById(id);
		Long position = forumManage.getPosition();
		String hql ="from ForumManage f where f.position<? order by f.position desc";
		Query query = getSession().createQuery(hql);
		query.setLong(0, position);
		query.setFirstResult(0);
		query.setMaxResults(1);
		ForumManage forumManage2 = (ForumManage)query.uniqueResult();
		
		forumManage.setPosition(forumManage2.getPosition());
		forumManage2.setPosition(position);
		
	}
	
	public void moveDown(Long id) {
		// TODO Auto-generated method stub
		//SELECT * FROM oa_forummanage WHERE POSITION > 2 ORDER BY POSITION ASC LIMIT 0,1
		ForumManage forumManage = getById(id);
		Long position = forumManage.getPosition();
		String hql ="from ForumManage f where f.position>? order by f.position asc";
		Query query = getSession().createQuery(hql);
		query.setLong(0, position);
		query.setFirstResult(0);
		query.setMaxResults(1);
		ForumManage forumManage2 = (ForumManage)query.uniqueResult();
		
		forumManage.setPosition(forumManage2.getPosition());
		forumManage2.setPosition(position);
	}
	
	
	
	



}
