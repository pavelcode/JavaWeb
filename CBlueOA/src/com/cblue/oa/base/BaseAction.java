package com.cblue.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cblue.oa.entity.User;
import com.cblue.oa.service.IDepartmentService;
import com.cblue.oa.service.IForumManageService;
import com.cblue.oa.service.IPrivilegeService;
import com.cblue.oa.service.IReplyService;
import com.cblue.oa.service.IRoleService;
import com.cblue.oa.service.IStudentService;
import com.cblue.oa.service.ITemplateService;
import com.cblue.oa.service.IThemeService;
import com.cblue.oa.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	@Autowired
	protected IStudentService studentService;
	@Autowired
	protected IRoleService roleService;
	@Autowired
	protected IDepartmentService departmentService;
	@Autowired
	protected IUserService userService;
	@Autowired
	protected IPrivilegeService privilegeService;
	@Autowired
	protected IForumManageService forumManageService;
	@Autowired
	protected IThemeService themeService;
	@Autowired
	protected IReplyService replyService;
	@Autowired
	protected ITemplateService templateService;
	
	
	public T  model;
	
	public BaseAction(){
		//获得当前类的父类的type
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		//获得类的泛型
		Type types [] = type.getActualTypeArguments();
		//获得第一个泛型,并转化成Class类
		Class<T> clazz = (Class<T>)types[0];
		try {
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	
	protected ValueStack getValueStack() {
		return ActionContext.getContext().getValueStack();
	}
	
	
	public String getIPAddress(){
		return ServletActionContext.getRequest().getRemoteAddr();
	}
	
	public User getLoginUser(){
		return (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
	
	protected int currentPage =1;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
