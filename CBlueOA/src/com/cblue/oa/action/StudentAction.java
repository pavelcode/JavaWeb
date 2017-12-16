package com.cblue.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cblue.oa.base.BaseAction;
import com.cblue.oa.entity.Student;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Student student = new Student();
		student.setName("xiaohei");
		studentService.add(student);
		 
		return null;
	}
	
}
