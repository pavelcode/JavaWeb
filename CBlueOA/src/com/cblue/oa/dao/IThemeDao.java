package com.cblue.oa.dao;

import java.util.List;

import com.cblue.oa.base.IBaseDao;
import com.cblue.oa.entity.ForumManage;
import com.cblue.oa.entity.Theme;

public interface IThemeDao extends IBaseDao<Theme> {

	public List<Theme> getThemeByForumManage(ForumManage forumManage);

}
