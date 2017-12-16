package com.cblue.oa.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cblue.oa.base.BaseAction;
import com.cblue.oa.entity.Reply;
import com.cblue.oa.entity.Theme;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {
	
	private Long themeId;
	

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	public String show(){
		//主题信息
		Theme theme = themeService.getById(model.getId());
		getValueStack().push(theme);
		
		//回复的列表
/*		List<Reply> replys = replyService.getReplyTopList(theme);
		getValueStack().set("replys",replys);*/
		
		
		//按照分页来实现
		//PageBean pageBean = replyService.getPageBean(currentPage,theme);
		
		 HQLUtils hqlUtils = new HQLUtils(Reply.class);
		 hqlUtils.addWhere("o.theme=?", model);
		 hqlUtils.addOrder("o.postTime", true);
		 PageBean pageBean = replyService.getPageBean(currentPage,hqlUtils);
		
		getValueStack().push(pageBean);
		
		return "show";
	}
	
	public String saveUI(){
		Theme theme = themeService.getById(themeId);
		getValueStack().push(theme);
		
		return "saveUI";
	}
	
	public String save(){
		
		Theme theme = themeService.getById(themeId);
		
		model.setTheme(theme);
		model.setAuthor(getLoginUser());
		model.setIpAddress(getIPAddress());
		model.setPostTime(new Date());
		model.setIsShow(0);
		
		replyService.save(model);
		
		return "toShow";
	}

}
