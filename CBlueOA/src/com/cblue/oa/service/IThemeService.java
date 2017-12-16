package com.cblue.oa.service;

import java.util.List;

import com.cblue.oa.entity.ForumManage;
import com.cblue.oa.entity.Theme;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

public interface IThemeService {

	public List<Theme> getThemeByForumManage(ForumManage forumManage);

	public void save(Theme model);

	public Theme getById(Long id);

	public PageBean getPageBean(int currentPage, HQLUtils hqlUtils);

}
