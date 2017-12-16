package com.cblue.oa.dao;

import java.util.List;

import com.cblue.oa.base.IBaseDao;
import com.cblue.oa.entity.Reply;
import com.cblue.oa.entity.Theme;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

public interface IReplyDao extends IBaseDao<Reply> {

	public List<Reply> getReplyTopList(Theme model);

	public PageBean getPageBean(int currentPage,HQLUtils hqlUtils);

}
