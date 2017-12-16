package com.cblue.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cblue.oa.base.BaseAction;
import com.cblue.oa.entity.ForumManage;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;


@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<ForumManage> {

	public String list() {
       // List<ForumManage> forumManages = forumManageService.getAll();
       // getValueStack().set("forumManages", forumManages);
		HQLUtils hqlUtils = new HQLUtils(ForumManage.class);
		hqlUtils.addOrder("o.position", true);
		PageBean pageBean =forumManageService.getPageBean(currentPage,hqlUtils);
		getValueStack().push(pageBean);
		
		return "list";
	}

	public String addUI() {
	
		return "changeUI";
	}

	public String add() {
		forumManageService.save(model);
		return "toList";
	}

	public String delete() {
		forumManageService.delete(model);
		return "toList";
	}

	public String updateUI() {
		ForumManage forumManage = forumManageService.getById(model.getId());
		getValueStack().push(forumManage);
		return "changeUI";
	}

	public String update() {
		forumManageService.update(model);
		return "toList";
	}
	
	public String moveUp(){
		forumManageService.moveUp(model);
		
		return "toList";
	}
	
	public String moveDown(){
		forumManageService.moveDown(model);
		return "toList";
	}
	
	
	

}
