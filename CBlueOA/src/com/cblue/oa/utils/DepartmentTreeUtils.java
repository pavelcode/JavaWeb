package com.cblue.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


import com.cblue.oa.entity.Department;

public class DepartmentTreeUtils {
	
	
	/**
	 * 把顶级列表转化树形结构
	 * @param  topDepartment 顶级部门
	 * @param  removeId 不显示部门的父ID
	 * @return  树形结构的List
	 */
	public static List<Department> getTreeList(List<Department> topDepartment,Long removeId){
		  List<Department> treeList  = new ArrayList<Department>();
		  showTree(topDepartment,treeList,"┣",removeId);
		  return treeList;
	}
	
	public static void showTree(Collection<Department> list,List<Department> treeList ,String prefix,Long removeId) {
		for(Department d : list){
			//判断是否是该移除父部门id
			if(removeId!=null&&removeId.equals(d.getId())){
					continue;
			}
			Department copy = new Department();
			copy.setId(d.getId());
			copy.setName(prefix+d.getName());
			treeList.add(copy);  //  1  ┣部门
		
			//子树
			Set<Department> children = d.getChildren();
			showTree(children,treeList,"　　　" + prefix,removeId);
		}
	}

}
