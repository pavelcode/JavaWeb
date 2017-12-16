package com.cblue.oa.dao;

import com.cblue.oa.base.IBaseDao;
import com.cblue.oa.entity.ForumManage;

public interface IForumManageDao extends IBaseDao<ForumManage> {

	public void moveUp(Long id);

	public void moveDown(Long id);

}
