package com.cblue.oa.service;

import java.util.List;
import com.cblue.oa.entity.*;

public interface IStudentService {
	
	   //添加
		public void add(Student entity);
		
		//删除
		public void delete(Long id);
		
		//修改
		public void update(Student entity);
		
		//根据id查询
		public Student getById(Long id);
		
		//一次查询多个对象
		public List<Student> getByIds(Long[] ids);
		
		//查询所有
		public List<Student> getAll();

}
