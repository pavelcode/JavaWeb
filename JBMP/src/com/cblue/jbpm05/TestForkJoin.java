package com.cblue.jbpm05;

import java.util.List;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.junit.Before;
import org.junit.Test;

public class TestForkJoin {
	
	
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
		deployment.addResourceFromClasspath("com/cblue/jbpm05/forkjoin.jpdl.xml");
		deployment.addResourceFromClasspath("com/cblue/jbpm05/forkjoin.png");
		// 完成部署,获得部署id
		String id = deployment.deploy();
	    System.out.println(id);
	}
	
	/**
	 * 启动流程
	 * 执行完之后，查看数据表task，发现两条记录
	 */
	@Test
	public void startProcess(){
		 String pdkey = "一次淘宝交易";
		 ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(pdkey);
   	     System.out.println(processInstance.getId());
	}

	/**
	 * 查询任务
	 * 先查卖家，再买家
	 */
	@Test
	public void testQueryTask(){
		String assigement="卖家";
		List<Task> tasks = processEngine.getTaskService().findPersonalTasks(assigement);
		for(Task task:tasks){
			System.out.println(task.getId()+"---"+task.getName());
		}
	}
	
	/**
	 * 办理任务
	 * 先让卖家办理，让卖家继续办理，在execution表中依然有记录
	 * 然后让买家办理，所有任务都办完了，在execution表中没有记录了
	 * 
	 */
	@Test
	public void testDoTask(){
		String taskId="370001";
		processEngine.getTaskService().completeTask(taskId);
	}
	
}
