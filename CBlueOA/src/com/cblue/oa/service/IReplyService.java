package com.cblue.oa.service;

import java.util.List;

import com.cblue.oa.entity.Reply;
import com.cblue.oa.entity.Theme;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

public interface IReplyService {

	public void save(Reply model);

	public List<Reply> getReplyTopList(Theme model);

	public PageBean getPageBean(int currentPage,HQLUtils hqlUtils);

}
