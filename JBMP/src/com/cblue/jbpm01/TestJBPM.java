package com.cblue.jbpm01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.task.Task;
import org.junit.Before;
import org.junit.Test;



/**
 * 控制JBPM流程
 * @author pavel
 *
 */
public class TestJBPM {
	
	private ProcessEngine processEngine;
	
	@Before
	public void testGetProcessEngine(){
		// 获得流程引擎对象
		processEngine = new Configuration().buildProcessEngine();
	}

	/**
	 * 使用xml和png文件部署工作流
	 */
	@Test
	public void testDeploy01() {
	
		// 通过流程引擎对象，获得部署对象
		NewDeployment deployment = processEngine.getRepositoryService().createDeployment();
		// 添加流程资源文件
		deployment.addResourceFromClasspath("vacate.jpdl.xml");
		deployment.addResourceFromClasspath("vacate.png");
		// 完成部署,获得部署id
		String id = deployment.deploy();
		System.out.println(id);

	}

	/**
	 * 使用zip文件部署工作流
	 */
	@Test
	public void testDeploy02() throws Exception {
		// 通过流程引擎对象，获得部署对象
		NewDeployment deployment = processEngine.getRepositoryService().createDeployment();
		// 添加流程资源文件
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new File("/Users/pavel/Documents/workspace/JBMP/process/vacate.zip")));
		deployment.addResourcesFromZipInputStream(zipInputStream);
		// 完成部署,获得部署id
		String id = deployment.deploy();
		System.out.println(id);

	}
	
	
	/**
	 *  查询部署的流程信息
	 */
     @Test
     public void testQuery(){
    	 //获得工作流定义查询对象
    	 ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
    	 
    	 //现在部署的流程个数
    	 Long count = query.count();
    	 System.out.println("现在部署的流程个数:"+count);
    	 
    	 //获得工作流定义对象
    	 List<ProcessDefinition> list = query.list();
    	 for(ProcessDefinition pd:list){
    		 System.out.println("pdid="+pd.getId());
    		 System.out.println("pdkey="+pd.getKey());
    		 System.out.println("pdname="+pd.getName());
    		 System.out.println("pdversion="+pd.getVersion());
    	 }
    	 
     }
     
     /**
      * 获得流程文件
      */
     @Test
     public void testFile()throws Exception{
    	 String deploydId = "1";
    	 Set<String> fileNames = processEngine.getRepositoryService().getResourceNames(deploydId);
    	 for(String fileName:fileNames){
    		 System.out.println("文件名："+fileName);
    		 InputStream inputStream =  processEngine.getRepositoryService().getResourceAsStream(deploydId, fileName);
    		 //保存文件
    		 OutputStream out = new FileOutputStream(new File("/Users/pavel/Documents/"+fileName));
    		 byte[] b = new byte[1024];
    		 int len = 0;
    		 while((len= inputStream.read(b))!=-1){
    			 out.write(b, 0, len);
    		 }
    	 }
    	
    	 
     }
     
     
     /**
      * 删除部署流程
      */
     @Test
     public void testDelete(){
    	 String deploydId = "10001";
    	 processEngine.getRepositoryService().deleteDeployment(deploydId);
     } 
     
     
     /**
      * 启动流程
      */
     @Test
     public void testStartById(){
    	 String pdid="请假流程-4";
    	 //流程实例对象
    	 ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceById(pdid);
    	 // processEngine.getExecutionService().startProcessInstanceByKey(pdkey);
    	 System.out.println(processInstance.getId());
     }
     
     /**
      * 查询任务列表
      */
     @Test
     public void testStartByKey(){
    	 String userId="张三";
    	 TaskQuery query = processEngine.getTaskService().createTaskQuery();
    	 query.assignee(userId);//过滤条件
    	 List<Task> list = query.list();
    	 for(Task task:list){
    		 System.out.println(task.getId()+"-----"+task.getName());
    	 }
    	
     }
     
     /**
      * 办理任务
      * task表中state字段是completed
      */
     @Test
     public void testDoTask(){
    	 String taskId="110001";
    	 processEngine.getTaskService().completeTask(taskId);
    	 
     }
     
     /**
      * 跳过任务
      * 注意：只能跳过下一个任务，不能隔任务
      * task表中state字段是obsolete（废弃）
      * 
      */
     @Test
     public void testSkipTask(){
    	 String executeid="请假流程.130001";//task表中executtion_id字段
    	 processEngine.getExecutionService().signalExecutionById(executeid,"to end1");//后一个参数是xml中的transition名字
     }
     
     
     
     /**
      * 获取所有流程的最新的流程定义
      * 思路：使用map的特性，当key相同，value不同的时候，后面的会覆盖前面的
      */
     @Test
     public void testNewProcessDefinition(){
    	 //首先获得所有流程定义
    	 ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
    	 //根据版本搜索
    	 query.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION);
    	 List<ProcessDefinition> list = query.list();
    	 
    	 //保存数据的map
    	 Map<String,ProcessDefinition> map = new HashMap<String,ProcessDefinition>();
    	 
    	 //循环展示下
    	 for(ProcessDefinition pd:list){
    		 System.out.println(pd.getId()+"---"+pd.getKey()+"--"+pd.getVersion());
    		 map.put(pd.getKey(), pd);
    	 }
    	 
    	 System.out.println("--------------");
    	 
    	 //展示处理后的效果
    	 List<ProcessDefinition> pdList = new ArrayList<ProcessDefinition>(map.values());
    	 for(ProcessDefinition pd:pdList){
    		 System.out.println(pd.getId()+"---"+pd.getKey()+"--"+pd.getVersion());
    	 }
    	 
     }
     
}
