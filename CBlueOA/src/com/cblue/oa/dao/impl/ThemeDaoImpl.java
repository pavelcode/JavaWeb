package com.cblue.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cblue.oa.base.BaseDaoImpl;
import com.cblue.oa.dao.IThemeDao;
import com.cblue.oa.entity.ForumManage;
import com.cblue.oa.entity.Theme;

@Repository
public class ThemeDaoImpl extends BaseDaoImpl<Theme> implements IThemeDao {

	public List<Theme> getThemeByForumManage(ForumManage forumManage) {
		// TODO Auto-generated method stub
		//String hql="from Theme t where t.forumManage=?";
		// SELECT * FROM oa_theme  ORDER BY (CASE TYPE WHEN 2 THEN 2 ELSE 1 END)  DESC,posttime desc
		String hql=" FROM Theme t where t.forumManage=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 1 END)  DESC,t.postTime desc";
		return getSession().createQuery(hql).setParameter(0, forumManage).list();
	}

	@Override
	public void add(Theme entity) {
		// TODO Auto-generated method stub
		super.add(entity);
		
		ForumManage forumManage = entity.getForumManage();
		forumManage.setThemeCount(forumManage.getThemeCount()+1);
		forumManage.setLastTheme(entity);
	}



}
