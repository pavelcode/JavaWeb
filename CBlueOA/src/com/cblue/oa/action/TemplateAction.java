package com.cblue.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Encoder;

import com.cblue.oa.base.BaseAction;
import com.cblue.oa.entity.Template;
import com.cblue.oa.service.IProcessDefinitionService;

@Controller
@Scope("prototype")
public class TemplateAction extends BaseAction<Template> {

	private File resource;

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	@Autowired
	private IProcessDefinitionService processDefinitionService;

	public String list() {
		List<Template> templates = templateService.getAll();
		getValueStack().set("templates", templates);
		return "list";
	}

	public String addUI() {
		// 准备流程信息
		List<ProcessDefinition> processDefinitions = processDefinitionService
				.getLastList();
		getValueStack().set("processDefinitions", processDefinitions);
		return "changeUI";
	}

	public String add() {
		// 上传文件
		String filePath = uploadFile(resource);
		model.setFilePath(filePath);
		templateService.add(model);
		return "toList";
	}

	/**
	 * 上传的文件 放在不同的日期目录（yyyy/MM/dd） 这个文件不能重复（重命名）
	 * 
	 * @param file
	 * @return
	 */
	private String uploadFile(File file) {
		// TODO Auto-generated method stub
		// 获得上传路径
		String saveDir = ServletActionContext.getServletContext().getRealPath(
				"/WEB-INF/template");

		// 创建日期文件夹
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy-MM-dd/");
		String dateName = simpleDateFormat.format(new Date());
		String realDir = saveDir + dateName;
		File createFile = new File(realDir);
		if (!createFile.exists()) {
			createFile.mkdirs();
		}

		// 修改文件（不重复）UUID
		String fileName = realDir + UUID.randomUUID().toString() + ".doc";
		File realFile = new File(fileName);

		// 上传文件
		file.renameTo(realFile);

		return fileName;
	}

	public String updateUI() {
		// 准备模板数据
		Template template = templateService.getById(model.getId());
		getValueStack().push(template);

		// 准备下拉列表的数据
		List<ProcessDefinition> processDefinitions = processDefinitionService
				.getLastList();
		getValueStack().set("processDefinitions", processDefinitions);

		return "changeUI";
	}

	public String update() {
		// 先查询后修改
		Template template = templateService.getById(model.getId());
		template.setName(model.getName());
		template.setProcessDefinitionKey(model.getProcessDefinitionKey());

		// 可能不改文件，也可能修改
		if (resource != null) {
			// 上传文件
			String filePath = uploadFile(resource);

			// 删除原文件
			String oldFilePath = template.getFilePath();
			File oldFile = new File(oldFilePath);
			if (oldFile.exists()) {
				oldFile.delete();
			}
			template.setFilePath(filePath);
		}
		templateService.update(template);

		return "toList";
	}

	public String delete() {
		templateService.delete(model.getId());
		return "toList";
	}

	private InputStream downloadInputStream;
	private String fileName;

	public InputStream getDownloadInputStream() {
		return downloadInputStream;
	}

	public void setDownloadInputStream(InputStream downloadInputStream) {
		this.downloadInputStream = downloadInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String download() {

		Template template = templateService.getById(model.getId());

		downloadInputStream = getFileInputStream(template.getFilePath());

		String agent = ServletActionContext.getRequest().getHeader("user-agent");		
		try {
			fileName = encodeDownloadFilename(template.getName() + ".doc", agent) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "download";
	}

	public InputStream getFileInputStream(String filePath) {
		// TODO Auto-generated method stub
		InputStream input = null;
		try {
			input = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return input;
	}

	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * 
	 * @param filename
	 *            下载文件名
	 * @param agent
	 *            客户端浏览器(通过request.getHeader("user-agent")获得)
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public String encodeDownloadFilename(String filename, String agent)
			throws IOException {
		if (agent.contains("Firefox")) { // 火狐浏览器
			filename = "=?UTF-8?B?"
					+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
					+ "?=";
		} else { // IE及其他浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
}
