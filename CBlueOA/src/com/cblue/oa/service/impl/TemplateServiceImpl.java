package com.cblue.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.ITemplateDao;
import com.cblue.oa.entity.Template;
import com.cblue.oa.service.ITemplateService;

@Service
@Transactional
public class TemplateServiceImpl implements ITemplateService {

	
	@Autowired
	private ITemplateDao templateDao;

	public List<Template> getAll() {
		// TODO Auto-generated method stub
		return templateDao.getAll();
	}

	public void add(Template model) {
		// TODO Auto-generated method stub
		templateDao.add(model);
		
	}

	public Template getById(Long id) {
		// TODO Auto-generated method stub
		return templateDao.getById(id);
	}

	public void update(Template template) {
		// TODO Auto-generated method stub
		templateDao.update(template);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
		//删除文件
		Template template = templateDao.getById(id);
		String filePath = template.getFilePath();
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		
		templateDao.delete(id);
		
	}
	
	
}
