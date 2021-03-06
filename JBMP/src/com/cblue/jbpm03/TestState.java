package com.cblue.jbpm03;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.junit.Before;
import org.junit.Test;


/**
 * 状态活动
 * @author pavel
 *
 */
public class TestState {
	
	
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
		deployment.addResourceFromClasspath("state.jpdl.xml");
		deployment.addResourceFromClasspath("state.png");
		// 完成部署,获得部署id
		String id = deployment.deploy();
	    System.out.println(id);
	}
	
	//启动流程
	@Test
	public void startProcess(){
		 String pdkey = "state";
		 ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(pdkey);
   	     System.out.println(processInstance.getId());
	}
	
	//exection表中数据没有了
	@Test
	public void testChangeState(){
		String executeid="state.270001";
		processEngine.getExecutionService().signalExecutionById(executeid,"to end1");
	}

}
