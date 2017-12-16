package com.cblue.oa.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cblue.oa.base.BaseAction;
import com.cblue.oa.entity.Privilege;
import com.cblue.oa.entity.Role;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	public String list(){
		List<Role> roles = roleService.queryAll();
		getValueStack().set("roles", roles);
		return "list";
	}
	
	public String addUI(){
	   return "changeUI";
	}
	
	public String add(){
		roleService.save(model);
		
		return "toList";
	}
	
	public String delete(){	
		roleService.delete(model);
		return "toList";
	}
	
	public String updateUI(){
		Role role = roleService.getById(model);
		getValueStack().push(role);
		return "changeUI";
	}
	
	public String update(){
		roleService.update(model);
		return "toList";
	}
	
	//跳转到权限界面
	public String setPrivilegeUI(){
		//获得岗位信息
		Role role = roleService.getById(model);
		getValueStack().push(role);
		
		//获得所有的权限
/*		List<Privilege> privileges = privilegeService.getAll();
		*/
		List<Privilege> privileges= privilegeService.getTopList();
		getValueStack().set("privileges", privileges);
		
		//获得岗位权限
		Set<Privilege> role_privilege = role.getPrivileges();
		//判断是否为空
		if(role_privilege!=null&&role_privilege.size()>0){
			privilegeIds = new Long[role_privilege.size()];
			int count = 0;
			for(Privilege privilege:role_privilege){
				privilegeIds[count++] = privilege.getId();
			}
		}
		
		
		return "setPrivilegeUI";
	}
	
	private Long privilegeIds[];
	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	//设定权限
	public String setPrivilege(){
		//通过id获得设定权限的岗位
		Role role = roleService.getById(model);
		
		if(privilegeIds!=null&&privilegeIds.length>0){
			//根据权限id获得权限集合
			List<Privilege> privileges = privilegeService.getByIds(privilegeIds);
			role.setPrivileges(new HashSet<Privilege>(privileges));
		}else{
			role.setPrivileges(null);
		}
		
		roleService.update(role);
		
		return "toList";
	}
	
	
}
