package com.cblue.jbpm02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.junit.Before;
import org.junit.Test;

/**
 * 流程变量
 * @author pavel
 *
 */
public class TestVariable {
	
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
		deployment.addResourceFromClasspath("vacate.jpdl.xml");
		deployment.addResourceFromClasspath("vacate.png");
		// 完成部署,获得部署id
		String id = deployment.deploy();
	    System.out.println(id);
	}
	
	//启动流程
	@Test
	public void startProcess(){
		 String pdkey = "请假流程";
		 ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(pdkey);
   	     System.out.println(processInstance.getId());
	}
	
	@Test
	public void setVar(){
		//使用ExecutionService
		/**
		 * 保存单个数据
		 * 第一个参数是executid，第二个参数是key，第三个参数是value
		 * 查看variable表
		 */
		//processEngine.getExecutionService().setVariable("请假流程.180001", "key1","value1");
		/**
		 * 保存多个数据
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("key2","value2");
//		map.put("key3", "value3");
//		processEngine.getExecutionService().setVariables("请假流程.180001",map);
		
		
		
		//使用TaskService  第一个参数是taskId
//		String taskid="160001";
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("key4","value4");
//		processEngine.getTaskService().setVariables(taskid,map);
		
		
		//启动流程实例
//		String pdkey="请假流程";
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("key5","value5");
//		processEngine.getExecutionService().startProcessInstanceByKey(pdkey,map);
		
		//办理任务
		 String taskId="180002";
		 Map<String,String> map = new HashMap<String,String>();
		 map.put("key6","value6");
    	 processEngine.getTaskService().completeTask(taskId,"to task2",map);
	
	}
	
	@Test
	public void getVar(){
		 //使用ExecutionService获得数据
//		Set<String> names = processEngine.getExecutionService().getVariableNames("请假流程.180001");
//		for(String name:names){
//			System.out.println("name="+name);
//			String value = (String)processEngine.getExecutionService().getVariable("请假流程.180001",name);
//			System.out.println(value);
//		}
		//使用TaskService获得数据
		String taskId="250002";
		Set<String> names = processEngine.getTaskService().getVariableNames(taskId);
		Map<String,Object> map = processEngine.getTaskService().getVariables(taskId,names);
		for(String name:names){
			System.out.println("key="+name);
			System.out.println("value="+map.get(name));
		}
	}

}
