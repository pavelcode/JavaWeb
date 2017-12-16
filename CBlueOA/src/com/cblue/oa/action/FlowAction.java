package com.cblue.oa.action;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cblue.oa.entity.Apply;
import com.cblue.oa.entity.Template;
import com.cblue.oa.entity.User;
import com.cblue.oa.service.IFlowService;
import com.cblue.oa.service.ITemplateService;
import com.cblue.oa.utils.FileUtils;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class FlowAction extends ActionSupport {
	
	private Long templateId;
	
	
	
	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}


	@Autowired
	private ITemplateService templateService;
	
	//查询所有的申请模板
	public String templateList(){
		List<Template> templates = templateService.getAll();
		//ServletActionContext.getValueStack(ServletActionContext.getRequest()).set(arg0, arg1);
		ActionContext.getContext().getValueStack().set("templates", templates);
		return "templateList";
	}
	
	//跳转到申请提交页面
	public String submitUI(){
		
		return "submitUI";
	}
	
	
	
	private File resource;
	
	
	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	@Autowired
	private IFlowService flowService;
	/**
	 * 1 上传文件
	 * 2 保存申请记录
	 * 3 启动流程
	 * 4 执行申请任务
	 */
	public String submit(){
		//上传文件
		String filePath = FileUtils.uploadFile(resource, "apply");
		
		Template template = templateService.getById(templateId);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String title =  template.getName()+"_"+getLoginUser().getName()+"_"+simpleDateFormat.format(new Date());
		
		//保存申请记录
		Apply apply = new Apply();
		apply.setApplyDate(new Date());
		apply.setApplyStatus(Apply.STATUS_RUNNING);
		apply.setApplyUser(getLoginUser());
		apply.setFilePath(filePath);
		apply.setTemplate(template);
		apply.setTitle(title);
		
		flowService.submit(apply);
		
		return "toMyApplyList";
		
	}
	
	public User getLoginUser(){
		return (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
	
	private InputStream downloadInputStream;
	private String downloadFileName;
	
	
	
	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public InputStream getDownloadInputStream() {
		return downloadInputStream;
	}

	public void setDownloadInputStream(InputStream downloadInputStream) {
		this.downloadInputStream = downloadInputStream;
	}

	//下载模板
	public String  downloadTemplate(){
		Template template = templateService.getById(templateId);
		//获得下载流对象
		downloadInputStream = FileUtils.getFileInputStream(template.getFilePath());
		//获得下载的文件名
		downloadFileName = FileUtils.downloadFileName(template);
		
		return "download";
	}
	
	private int currentPage=1;
	private int status;
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	//进入我的申请查询
	public String toMyApplyList(){
		//申请模板列表
		List<Template> templates = templateService.getAll();
		ActionContext.getContext().getValueStack().set("templates", templates);
		
		//查询所有的申请
		HQLUtils hqlUtils = new HQLUtils(Apply.class);
		hqlUtils.addWhere(" o.applyUser=? ", getLoginUser());
		
		//如果选择不同的模板
		if(templateId!=null){
			hqlUtils.addWhere(" o.template.id=? ", templateId);
		}
		//根据状态选择
		if(status>0){
			String statusValue = null;
			switch (status) {
			case 1:
				statusValue = Apply.STATUS_RUNNING;
				break;

               case 2:
            	   statusValue = Apply.STATUS_LOSER;
				break;

               case 3:
            	   statusValue = Apply.STATUS_OK;
				break;
			}
			hqlUtils.addWhere(" o.applyStatus=? ", statusValue);
		}
		
		
		hqlUtils.addOrder(" o.applyDate ", false);
		
		PageBean pageBean = flowService.getPageBean(hqlUtils,currentPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		
		return "myApplyList";
	}
	
	//跳转到待我审核
	public String toMyCheckList(){
		
		
		return "myCheckList";
	}
	
	

}
