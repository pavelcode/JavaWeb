package com.cblue.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IForumManageDao;
import com.cblue.oa.entity.ForumManage;
import com.cblue.oa.service.IForumManageService;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

@Service
@Transactional
public class ForumManageServiceImpl implements IForumManageService {

	@Autowired
	private IForumManageDao forumManageDao;

	public List<ForumManage> getAll() {
		// TODO Auto-generated method stub
		return forumManageDao.getAll();
	}

	public void save(ForumManage model) {
		// TODO Auto-generated method stub
		forumManageDao.add(model);
	}

	public void delete(ForumManage model) {
		// TODO Auto-generated method stub
		forumManageDao.delete(model.getId());
	}

	public ForumManage getById(Long id) {
		// TODO Auto-generated method stub
		return forumManageDao.getById(id);
	}

	public void update(ForumManage model) {
		// TODO Auto-generated method stub
		forumManageDao.update(model);
	}

	public void moveUp(ForumManage model) {
		// TODO Auto-generated method stub
		forumManageDao.moveUp(model.getId());
	}

	public void moveDown(ForumManage model) {
		// TODO Auto-generated method stub
		forumManageDao.moveDown(model.getId());
	}

	public PageBean getPageBean(int currentPage, HQLUtils hqlUtils) {
		// TODO Auto-generated method stub
		return forumManageDao.getPageBean(currentPage,hqlUtils);
	}
	
	
}
