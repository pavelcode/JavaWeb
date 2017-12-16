package com.cblue.jbpm09;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.junit.Before;
import org.junit.Test;

public class TestGoupTask {
	
	
ProcessEngine processEngine;
	
	@Before
	public void setBefor(){
		// 获得流程引擎对象
		processEngine = new Configuration().buildProcessEngine();
	}
	
	//部署流程
	@Test
	public void deployProcess(){
		// 通过流程引擎对象，获得部署对象
		NewDeployment deployment = processEngine.getRepositoryService().createDeployment();
		// 添加流程资源文件
		deployment.addResourceFromClasspath("com/cblue/jbpm09/grouptask.jpdl.xml");
		deployment.addResourceFromClasspath("com/cblue/jbpm09/grouptask.png");
		// 完成部署,获得部署id
		String id = deployment.deploy();
	    System.out.println(id);
	}
	
	/**
	 * 启动流程
	 *
	 */
	@Test
	public void startProcess(){
		//直接跳过Custom
		 String pdkey = "组任务";
		 ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(pdkey);
   	     System.out.println(processInstance.getId());
	}
	
	/**
	 * 查询个人任务，查不到
	 */
	@Test
	public void testPersonTask(){
		String userId="user1";
		List<Task> list = processEngine.getTaskService().findPersonalTasks(userId);
		for(Task task:list){
			System.out.println(task.getId()+"----"+task.getName());
		}
	}
	
	/**
	 * 查询组任务，可以查到
	 */
	@Test
	public void testGroupTask(){
		String userId="user1";
		List<Task> list = processEngine.getTaskService().findGroupTasks(userId);
		for(Task task:list){
			System.out.println(task.getId()+"----"+task.getName());
		}
	}
	
	/**
	 * 把组任务拾取成个人任务
	 */
	@Test
	public void testTakeTask(){
		processEngine.getTaskService().takeTask("560002", "user2");
	}
	
	/**
	 * 退回任务
	 */
	@Test
	public void testReturnTask(){
		processEngine.getTaskService().assignTask("560002", null);
	}
	
	/**
	 * 直接分配任务，不要走退回，在拾取
	 */
	@Test
	public void testAssignTask(){
		processEngine.getTaskService().assignTask("560002","user3");
	}
	

	
}
