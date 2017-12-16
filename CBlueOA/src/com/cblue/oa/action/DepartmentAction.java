package com.cblue.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cblue.oa.base.BaseAction;
import com.cblue.oa.entity.Department;
import com.cblue.oa.entity.Role;
import com.cblue.oa.utils.DepartmentTreeUtils;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	
	private Long parentId;
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String list(){
       // List<Department> departments =  departmentService.getAll();
		//如果是顶级部门,就没有parentId,否则有
		 List<Department> departments = null;
		if(parentId==null){
		   departments  = departmentService.getTopDepartmentList();
		}else{
			departments =departmentService.getChildrenDepartmentList(parentId);
			Department parent = departmentService.getById(parentId);
			getValueStack().push(parent);
			
		}
        getValueStack().set("departments", departments);
		return "list";
	}
	
	public String addUI(){
	   //List<Department> departments =  departmentService.getAll();
	   List<Department> topDepartments = departmentService.getTopDepartmentList();
	   List<Department> departments = DepartmentTreeUtils.getTreeList(topDepartments,null);
	   getValueStack().set("departments", departments);
	   
	   return "changeUI";
	}
	
	public String add(){
		//判断parentId是否为空，添加是顶级部门，不需要设置parent属性
		//否则,设置parent属性
		if(parentId==null){
			model.setParent(null);
		}else{
			Department parent = departmentService.getById(parentId);
			model.setParent(parent);
		}
		departmentService.save(model);
		
		return "toList";
	}
	
	public String delete(){	
		departmentService.delete(model.getId());
		
		return "toList";
	}
	
	public String updateUI(){
		//1.所有部门的信息
		//List<Department> departments = departmentService.getAll();
		//2.要修改的部门信息
		Department department = departmentService.getById(model.getId());
		List<Department> topDepartments = departmentService.getTopDepartmentList();
		List<Department> departments = DepartmentTreeUtils.getTreeList(topDepartments,model.getId());
		getValueStack().set("departments", departments);
		getValueStack().push(department);
		
		if(department.getParent()!=null){
			parentId = department.getParent().getId();
		}

		return "changeUI";
	}
	
	public String update(){
		//首先获得要修改的部门信息
		Department department = departmentService.getById(model.getId());
		// 然后把修改的信息赋值给要修改的部门
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		//是否修改了父部门
		if(parentId==null){
			department.setParent(null);
		}else{
			Department parent = departmentService.getById(parentId);
			department.setParent(parent);
		}
		
		departmentService.update(department);
		

		return "toList";
	}

}
