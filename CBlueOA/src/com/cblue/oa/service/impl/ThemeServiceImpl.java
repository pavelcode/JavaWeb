package com.cblue.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IThemeDao;
import com.cblue.oa.entity.ForumManage;
import com.cblue.oa.entity.Theme;
import com.cblue.oa.service.IThemeService;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

@Service
@Transactional
public class ThemeServiceImpl implements IThemeService {

	@Autowired
	private IThemeDao themeDao;

	public List<Theme> getThemeByForumManage(ForumManage forumManage) {
		// TODO Auto-generated method stub
		return themeDao.getThemeByForumManage(forumManage);
	}

	public void save(Theme model) {
		// TODO Auto-generated method stub
		themeDao.add(model);
	}

	public Theme getById(Long id) {
		// TODO Auto-generated method stub
		return themeDao.getById(id);
	}

	public PageBean getPageBean(int currentPage, HQLUtils hqlUtils) {
		// TODO Auto-generated method stub
		return themeDao.getPageBean(currentPage, hqlUtils);
	}

	
}
