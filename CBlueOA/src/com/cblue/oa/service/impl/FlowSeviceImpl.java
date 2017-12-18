package com.cblue.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.ProcessInstanceQuery;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IApplyDao;
import com.cblue.oa.dao.ICheckDao;
import com.cblue.oa.entity.Apply;
import com.cblue.oa.entity.Check;
import com.cblue.oa.entity.TaskView;
import com.cblue.oa.entity.User;
import com.cblue.oa.service.IFlowService;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

@Service
@Transactional
public class FlowSeviceImpl implements IFlowService {
	
	@Autowired
	private IApplyDao applyDao;
	@Autowired
	private ICheckDao checkDao;
	
	
	@Autowired
	ProcessEngine processEngine;

	public void submit(Apply apply) {
		// TODO Auto-generated method stub
		applyDao.add(apply);
		
		//启动流程
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("apply", apply);
		ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey(apply.getTemplate().getProcessDefinitionKey(),map);
		
		// 执行申请任务
		TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
		taskQuery.processInstanceId(processInstance.getId());
		Task task = taskQuery.uniqueResult();
		processEngine.getTaskService().completeTask(task.getId());
		
		
	}

	public PageBean getPageBean(HQLUtils hqlUtils, int currentPage) {
		// TODO Auto-generated method stub
		return applyDao.getPageBean(currentPage, hqlUtils);
	}

	public Apply getApplyById(Long applyId) {
		// TODO Auto-generated method stub
		return applyDao.getById(applyId);
	}

	public List<Check> getAllCheckById(Long applyId) {
		// TODO Auto-generated method stub
		return checkDao.getAllCheckById(applyId);
	}

	public List<TaskView> getMyCheckTaskList(User loginUser) {
		// TODO Auto-generated method stub
		//当前用户的所有任务
		List<Task> tasks = processEngine.getTaskService().findPersonalTasks(loginUser.getName());
		
		List<TaskView> taskViews = new ArrayList<TaskView>();
		
		for(Task task:tasks){
			//获得的所有任务下的申请对象
			Apply apply = (Apply)processEngine.getTaskService().getVariable(task.getId(),"apply");
			TaskView taskView = new TaskView(apply, task);
			taskViews.add(taskView);
			
		}
		return taskViews;
	}

	public void check(Check check, String taskId) {
		// TODO Auto-generated method stub
		//1 保存审核实体
		checkDao.add(check);
		
		//首先获取当前的任务
		Task task = processEngine.getTaskService().getTask(taskId);
		String exectionId =task.getExecutionId();
		
		//2 办理任务
		processEngine.getTaskService().completeTask(taskId);
		
		//判断下一个任务	
		ProcessInstanceQuery processInstanceQuery = processEngine.getExecutionService().createProcessInstanceQuery();
		processInstanceQuery.processInstanceId(exectionId);//exectionId
		ProcessInstance processInstance = processInstanceQuery.uniqueResult();	
		
		//3 当我点击同意的时候，进入下一个任务。下一个任务是否结束。如果结束，结束流程
		if(check.getIsPass()){
			
			//判断是否是最后一个任务
			if(processInstance==null){
			   check.getApply().setApplyStatus(Apply.STATUS_OK);	
			}
			
		}else{
			//当我点击不同的时候，修改申请状态
			 check.getApply().setApplyStatus(Apply.STATUS_LOSER);	
			 //结束流程
			 processEngine.getExecutionService().endProcessInstance(exectionId, ProcessInstance.STATE_ENDED);
			
		}
		
		
		
	}


	

}
