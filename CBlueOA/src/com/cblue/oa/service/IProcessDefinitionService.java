package com.cblue.oa.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;

public interface IProcessDefinitionService {

	public void deploy(File resource);

	public List<ProcessDefinition> getLastList();

	public void deleteByKey(String key);

	public InputStream getImageInputStream(String id);


}
