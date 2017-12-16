package com.cblue.jbpm06;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

/**
 * 自定义活动
 * @author pavel
 *
 */
public class TestCustom {
	

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
		deployment.addResourceFromClasspath("com/cblue/jbpm06/custom.jpdl.xml");
		deployment.addResourceFromClasspath("com/cblue/jbpm06/custom.png");
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
		 String pdkey = "custom";
		 ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(pdkey);
   	     System.out.println(processInstance.getId());
	}
	
	
	/**
	 * 发给信号，完成自定义活动
	 * 打印出signal
	 */
	@Test
	public void testCustom(){
		String executionId="custom.410001";
		processEngine.getExecutionService().signalExecutionById(executionId, "to end1");
	}
	
	
	
	

}
