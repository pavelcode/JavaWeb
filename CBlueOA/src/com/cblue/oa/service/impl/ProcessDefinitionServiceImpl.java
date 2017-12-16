package com.cblue.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.service.IProcessDefinitionService;

@Service
@Transactional
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService {

	@Autowired
	private ProcessEngine processEngine;

	public void deploy(File resource) {
		// TODO Auto-generated method stub
		// 通过工作流引擎，获得部署对象
		NewDeployment newDeployment = processEngine.getRepositoryService()
				.createDeployment();
		// 添加部署文件
		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(resource));
			newDeployment.addResourcesFromZipInputStream(zipInputStream);
			// 开始部署
			String id = newDeployment.deploy();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (zipInputStream != null) {
				try {
					zipInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public List<ProcessDefinition> getLastList() {
		// TODO Auto-generated method stub
		ProcessDefinitionQuery query = processEngine.getRepositoryService()
				.createProcessDefinitionQuery();
		// 添加一个排序条件，按照版本从小到大
		query.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION);

		List<ProcessDefinition> list = query.list();

		// 保存数据的map
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();

		for (ProcessDefinition processDefinition : list) {

			map.put(processDefinition.getKey(), processDefinition);
		}

		return new ArrayList<ProcessDefinition>(map.values());
	}

	public void deleteByKey(String key) {
		// TODO Auto-generated method stub
		// 根据key查询所有的流程部署
		ProcessDefinitionQuery query = processEngine.getRepositoryService()
				.createProcessDefinitionQuery();
		query.processDefinitionKey(key);
		List<ProcessDefinition> list = query.list();
		for (ProcessDefinition processDefinition : list) {
			System.out.println(key + "---"
					+ processDefinition.getDeploymentId());
			// processEngine.getRepositoryService().deleteDeployment(processDefinition.getDeploymentId());
			processEngine.getRepositoryService().deleteDeploymentCascade(
					processDefinition.getDeploymentId());

		}

	}

	public InputStream getImageInputStream(String id) {
		// TODO Auto-generated method stub
		ProcessDefinitionQuery query = processEngine.getRepositoryService()
				.createProcessDefinitionQuery();
		query.deploymentId(id);
		ProcessDefinition processDefinition = query.uniqueResult();
		// 获得图片文件名
		String name = processDefinition.getImageResourceName();
		// 获得图片的文件流
		return processEngine.getRepositoryService().getResourceAsStream(id,name);
	}

	

}
