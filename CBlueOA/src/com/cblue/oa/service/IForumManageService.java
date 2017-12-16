package com.cblue.oa.service;

import java.util.List;

import com.cblue.oa.entity.ForumManage;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

public interface IForumManageService {

	public List<ForumManage> getAll();

	public void save(ForumManage model);

	public void delete(ForumManage model);

	public ForumManage getById(Long id);

	public void update(ForumManage model);

	public void moveUp(ForumManage model);

	public void moveDown(ForumManage model);

	public PageBean getPageBean(int currentPage, HQLUtils hqlUtils);

}
