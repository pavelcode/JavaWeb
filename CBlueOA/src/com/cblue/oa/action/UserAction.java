package com.cblue.oa.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.management.relation.RoleList;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import com.cblue.oa.base.BaseAction;
import com.cblue.oa.entity.Department;
import com.cblue.oa.entity.Role;
import com.cblue.oa.entity.User;
import com.cblue.oa.utils.DepartmentTreeUtils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	private Long departmentId; //部门Id
	private Long[] roleIdList = null;
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(Long[] roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String list(){
	    List<User> users =  userService.getAll();
	    getValueStack().set("users", users);
		return "list";
	}
		
	public String addUI(){
		 //获得部门信息
		  List<Department> topDepartments = departmentService.getTopDepartmentList();
		  List<Department> departments = DepartmentTreeUtils.getTreeList(topDepartments,null);
		 //获得岗位信息
		 List<Role> roles =  roleService.queryAll();
		 
		 getValueStack().set("departments", departments);
		 getValueStack().set("rolesList",roles);
		 
		return "changeUI";
	}
		
		public String add(){
		    //判断是否有部门
			 if(departmentId!=null){
				Department department =  departmentService.getById(departmentId);
				model.setDepartment(department);
			 }
			 //判断是否有角色
			 if(roleIdList!=null&&roleIdList.length>0){
				 List<Role> roles = roleService.getByIds(roleIdList);
				 model.setRoles(new HashSet<Role>(roles));
			 }
			userService.save(model);
			return "toList";
		}
		
		public String delete(){	
			userService.delete(model);
			
			return "toList";
		}
		
		public String updateUI(){
	        //获得当前用户的信息
			User user = userService.getById(model);
			getValueStack().push(user);
			//获得部门信息(树形结构)
			 List<Department> topDepartments = departmentService.getTopDepartmentList();
			 List<Department> departments = DepartmentTreeUtils.getTreeList(topDepartments,null);
			 getValueStack().set("departments", departments);
			  
			//获得岗位信息
			 List<Role> roles =  roleService.queryAll();
			 getValueStack().set("rolesList",roles);
			 
			 //设置回显内容
			 //设置选中的部门
			 if(user.getDepartment()!=null){
				 departmentId = user.getDepartment().getId();
			 }
			 //设置选中的岗位
			 if(user.getRoles()!=null&&user.getRoles().size()>0){
				 int size = user.getRoles().size();
				 roleIdList = new Long[size];
				 int count = 0;
				 for(Role role:user.getRoles()){
					 roleIdList[count++] = role.getId();
				 }
				
			 }
			 

			return "changeUI";
		}
		
		public String update(){
			
			//先查询，后修改
			User user = userService.getById(model);
			user.setLoginName(model.getLoginName());
			user.setName(model.getName());
			user.setSex(model.getSex());
			user.setPhone(model.getPhone());
			user.setEmail(model.getEmail());
			user.setDescription(model.getDescription());
			
			//修改部门
			if(departmentId!=null){
				Department department = departmentService.getById(departmentId);
				user.setDepartment(department);
			}else{
				user.setDepartment(null);
			}
			
			//修改角色
			if(roleIdList!=null&&roleIdList.length>0){
				List<Role> roles = roleService.getByIds(roleIdList);
				user.setRoles(new HashSet<Role>(roles));
			}else{
				user.setRoles(null);
			}
			
			userService.update(user);
			

			return "toList";
		}
		
		
		public String initPassword(){
			User user = userService.getById(model);
			user.setPassword(DigestUtils.md5DigestAsHex("123".getBytes()));
			userService.update(user);
			return "toList";
		}
		
		/**
		 * 用户名是否存在
		 * @return
		 */
		public String getLoginNameIsExist(){

			int count = userService.getLoginName(model);
			
			//构建HTML页面
			ServletActionContext.getResponse().setContentType("text/html");
			
			String flag = "0";
			if(count>0){
				flag = "1";
			}
			try {
				ServletActionContext.getResponse().getWriter().write(flag);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return NONE;
		}

		/**
		 * 用户登录
		 */
		public String login(){
			//用户名或密码是否为空
			if(model.getLoginName()!=null&&model.getPassword()!=null){
				User user = userService.login(model);
				//判断用户是否存在
				//如果用户存在，把数据保存在session中
				if(user!=null){
					ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
					return "home";
				}else{
				//如果用户不存在，提示错误信息，跳转到登录页面
				   addActionError("用户名和密码错误");
				   return "loginUI";
				}
			}
			
			 return "loginUI";
		}
		
		/**
		 * 用户退出
		 */
		public String logout(){
			ServletActionContext.getRequest().getSession().removeAttribute("loginUser");
			
			System.out.println("*****%%%"+ServletActionContext.getRequest().getSession().getAttribute("loginUser"));
			
			return "logoutUI";
		}
}
