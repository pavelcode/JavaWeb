package com.cblue.oa.service;

import java.io.InputStream;
import java.util.List;

import com.cblue.oa.entity.Template;

public interface ITemplateService {

	public List<Template> getAll();

	public void add(Template model);

	public Template getById(Long id);

	public void update(Template template);

	public void delete(Long id);


}
