package com.cblue.jbpm04;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

/**
 * 判断活动
 * @author pavel
 *
 */
public class TestJudgement {
	
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
		deployment.addResourceFromClasspath("com/cblue/jbpm04/spendmany.jpdl.xml");
		deployment.addResourceFromClasspath("com/cblue/jbpm04/spendmany.png");
		// 完成部署,获得部署id
		String id = deployment.deploy();
	    System.out.println(id);
	}
	
	//启动流程
	@Test
	public void startProcess(){
		 String pdkey = "花钱";
		 ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(pdkey);
   	     System.out.println(processInstance.getId());
	}
	
	//判断钱数，走不同的流程
	@Test
	public void testJudgement(){
		 String taskId="290002";
		 Map<String,Double> map = new HashMap<String,Double>();
		 map.put("money",500.0);
    	 processEngine.getTaskService().completeTask(taskId,"to exclusive1",map);
	}
	
	
	

}
