package com.cblue.oa.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cblue.oa.base.BaseAction;
import com.cblue.oa.entity.ForumManage;
import com.cblue.oa.entity.Theme;
import com.cblue.oa.entity.User;
import com.cblue.oa.service.impl.ForumManageServiceImpl;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Theme> {

	private Long forumId;
	private int viewType = 0;
	private int orderBy = 0;
	private Boolean reverse ;

	

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getReverse() {
		return reverse;
	}

	public void setReverse(Boolean reverse) {
		this.reverse = reverse;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public String list() {
		List<ForumManage> forumManages = forumManageService.getAll();
		getValueStack().set("forumManages", forumManages);
		return "list";
	}

	public String show() {
		ForumManage forumManage = forumManageService.getById(model.getId());
		getValueStack().push(forumManage);

	/*	List<Theme> themes = themeService.getThemeByForumManage(forumManage);
		getValueStack().set("themes", themes);*/
		
		//System.out.println(getViewType()+"----"+getOrderBy()+"---"+getReverse());
		
		
		HQLUtils hqlUtils = new HQLUtils(Theme.class);
		hqlUtils.addWhere(" o.forumManage=? ", forumManage);
        //SELECT * FROM oa_theme  ORDER BY (CASE TYPE WHEN 2 THEN 2 ELSE 1 END)  DESC,posttime desc
		hqlUtils.addOrder(" (CASE o.type WHEN 2 THEN 2 ELSE 1 END) ", false);
		hqlUtils.addOrder(" o.postTime ",getReverse()==null?false:true);
		
		
		if(getViewType()==1){
			//按精华帖排序
			hqlUtils.addWhere(" o.type=? ", 1);
		}
		
		/*<option value="1">按最后更新时间排序</option>
		<option value="2">按主题发表时间排序</option>
		<option value="3">按回复数量排序</option>*/
		if(getOrderBy()==1){
			hqlUtils.addOrder(" o.lastReply.postTime ",getReverse());
		}else if(getOrderBy()==2){
			hqlUtils.addOrder(" o.postTime ", getReverse());
		}else if(getOrderBy()==3){
			hqlUtils.addOrder(" o.replyCount ",getReverse());
		}
		
	
			
		
		
		PageBean pageBean = themeService.getPageBean(currentPage,hqlUtils);
		getValueStack().push(pageBean);

		return "show";
	}

	public String saveUI() {
		ForumManage forumManage = forumManageService.getById(forumId);
		getValueStack().push(forumManage);
		return "saveUI";
	}

	public String save() {
		ForumManage forumManage = forumManageService.getById(forumId);

		model.setAuthor(getLoginUser());
		model.setForumManage(forumManage);

		model.setIpAddress(getIPAddress());

		model.setLastReply(null);
		model.setPostTime(new Date());
		model.setReplyCount(0L);
		model.setType(0);

		themeService.save(model);

		return "toShow";
	}

}
