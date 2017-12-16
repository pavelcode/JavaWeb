package com.cblue.oa.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cblue.oa.entity.Student;
import com.cblue.oa.service.IStudentService;

public class StudentTest {
	
	@Test
	public void testSpring(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IStudentService studentService = (IStudentService)context.getBean("studentServiceImpl");
		Student studnet = new Student();
		studnet.setName("abc");
		
		//studentService.add(studnet);
		
		//studentService.delete(1L);
	}

}
