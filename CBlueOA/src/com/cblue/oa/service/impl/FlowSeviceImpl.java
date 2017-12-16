package com.cblue.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IApplyDao;
import com.cblue.oa.entity.Apply;
import com.cblue.oa.service.IFlowService;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

@Service
@Transactional
public class FlowSeviceImpl implements IFlowService {
	
	@Autowired
	private IApplyDao applyDao;
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
	
	

}
