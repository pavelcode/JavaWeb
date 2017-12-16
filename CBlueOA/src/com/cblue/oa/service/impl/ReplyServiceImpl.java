package com.cblue.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IReplyDao;
import com.cblue.oa.entity.ForumManage;
import com.cblue.oa.entity.Reply;
import com.cblue.oa.entity.Theme;
import com.cblue.oa.service.IReplyService;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

@Service
@Transactional
public class ReplyServiceImpl implements IReplyService {
	
	@Autowired
	private IReplyDao replyDao;

	public void save(Reply model) {
		// TODO Auto-generated method stub
		replyDao.add(model);
		
		ForumManage forumManage = model.getTheme().getForumManage();
		forumManage.setReplyCount(forumManage.getReplyCount()+1);
		
		Theme theme = model.getTheme();
		theme.setReplyCount(theme.getReplyCount()+1);
		theme.setLastReply(model);
			
	}

	public List<Reply> getReplyTopList(Theme model) {
		// TODO Auto-generated method stub
		return replyDao.getReplyTopList(model);
	}

	public PageBean getPageBean(int currentPage,HQLUtils hqlUtils) {
		
		return replyDao.getPageBean(currentPage,hqlUtils);
	}

}
