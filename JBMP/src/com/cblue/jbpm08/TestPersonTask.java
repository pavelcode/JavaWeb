package com.cblue.jbpm08;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

public class TestPersonTask {

	
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
		deployment.addResourceFromClasspath("com/cblue/jbpm08/persontask.jpdl.xml");
		deployment.addResourceFromClasspath("com/cblue/jbpm08/persontask.png");
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
		 String pdkey = "个人任务";
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("username","one person");
		 ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(pdkey,map);
   	     System.out.println(processInstance.getId());
	}
}
