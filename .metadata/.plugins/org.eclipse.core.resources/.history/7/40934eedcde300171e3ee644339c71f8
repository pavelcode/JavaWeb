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
import com.cblue.oa.entity.Check;
import com.cblue.oa.entity.TaskView;
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
		downloadFileName = FileUtils.downloadFileName(template.getName());
		
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
	
	private Long applyId;
	
	
	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	//下载申请文件
	public String downloadApplyFile(){
		Apply apply = flowService.getApplyById(applyId);
		//获得下载流对象
		downloadInputStream = FileUtils.getFileInputStream(apply.getFilePath());
				//获得下载的文件名
		downloadFileName = FileUtils.downloadFileName(apply.getTemplate().getName());
		
		return "download";
	}
	
	/**
	 * 如果你要完成待我审核
	 * 1 获得该我审核的审核记录
	 * 
	 */
	
	//跳转到待我审核
	public String toMyCheckList(){
		List<TaskView> taskViews = flowService.getMyCheckTaskList(getLoginUser());
		ActionContext.getContext().getValueStack().set("taskViews", taskViews);
		
		return "myCheckList";
	}
	
	
	
	//查看历史记录
	public String  historyRecord(){
		
		List<Check> checks = flowService.getAllCheckById(applyId);
		ActionContext.getContext().getValueStack().set("checks", checks);
		return "checkHistory";
		
	}
	
	private String taskId;
	private String comment;
	private Boolean isPass;
	
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	//跳转到审核页面
	public String checkUI(){
		
		
		return "checkUI";
	}
	
	/**
	 * 1 保存审核实体
	 * 2 办理任务
	 * 3 当我点击同意的时候，进入下一个任务。下一个任务是否结束。如果结束，结束流程
	 * 4 当我点击不同的时候，结束流程
	 * @return
	 */
	
	//完成审核
	public String check(){
		
		Apply apply = flowService.getApplyById(applyId);
		
		//保存审核实体
		Check check = new  Check();
		check.setApply(apply);
		check.setCheckDate(new Date());
		check.setCheckIdea(getComment());
		check.setCheckUser(getLoginUser());
		check.setIsPass(getIsPass());
		
		flowService.check(check,getTaskId());
		
		
		return "toMyCheckList";
	}
	
	
	
	
	
	

}
