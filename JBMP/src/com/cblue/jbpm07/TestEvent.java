package com.cblue.jbpm07;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

public class TestEvent {

	
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
			deployment.addResourceFromClasspath("com/cblue/jbpm07/event.jpdl.xml");
			deployment.addResourceFromClasspath("com/cblue/jbpm07/event.png");
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
			//直接跳过Custom
			 String pdkey = "event";
			 ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(pdkey);
	   	     System.out.println(processInstance.getId());
		}
		
		 /**
	      * 办理任务
	      * task表中state字段是completed
	      */
	     @Test
	     public void testDoTask(){
	    	 String taskId="450002";
	    	 processEngine.getTaskService().completeTask(taskId);
	    	 
	     }
}
