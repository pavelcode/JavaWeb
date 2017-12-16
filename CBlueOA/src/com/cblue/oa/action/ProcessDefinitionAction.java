package com.cblue.oa.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cblue.oa.service.IProcessDefinitionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport {
	
	private File resource;
	private String key;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}


	@Autowired
	private IProcessDefinitionService processDefinitionService;
	
	public String list(){
		List<ProcessDefinition> processDefinitions = processDefinitionService.getLastList();
		ActionContext.getContext().getValueStack().set("processDefinitions", processDefinitions);
		return "list";
	}
	
	public String deployUI(){
		
		return "deployUI";
	}
	
	
	public String deploy(){
		//完成部署
		processDefinitionService.deploy(resource);
		return "toList";
	}
	
	/**
	 * 在编码中，我们涉及到编码：无非是这几种：utf-8，iso-8859-1，gbk，gb2312
	 * @return
	 */
	
	public String delete(){
		//乱码处理
		try {
			ServletActionContext.getRequest().setCharacterEncoding("utf-8");
			key = new String(key.getBytes("iso-8859-1"),"utf-8");
			
		/*	System.out.println(new String(key.getBytes("iso-8859-1"),"utf-8"));
			System.out.println(new String(key.getBytes("gbk"),"utf-8"));
			System.out.println(new String(key.getBytes("gb2312"),"utf-8"));
			
			System.out.println(new String(key.getBytes("iso-8859-1"),"gbk"));
			System.out.println(new String(key.getBytes("utf-8"),"gbk"));
			System.out.println(new String(key.getBytes("gb2312"),"gbk"));
			
			System.out.println(new String(key.getBytes("iso-8859-1"),"gb2312"));
			System.out.println(new String(key.getBytes("utf-8"),"gb2312"));
			System.out.println(new String(key.getBytes("gb2312"),"gb2312"));
			
			System.out.println(new String(key.getBytes("gbk"),"iso-8859-1"));
			System.out.println(new String(key.getBytes("utf-8"),"iso-8859-1"));
			System.out.println(new String(key.getBytes("gb2312"),"iso-8859-1"));*/
			
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		processDefinitionService.deleteByKey(key);
		return "toList";
		
	}
	
	public String getImage(){	 
	     
		//获得流程的发布Id值
		try {
			id = new String(id.getBytes("iso-8859-1"),"utf-8");
			System.out.println(id+"---");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//根据发布流程的Id值，
		InputStream imageStream = processDefinitionService.getImageInputStream(id);
		
		//把数据写入到页面中
		HttpServletResponse response = ServletActionContext.getResponse();  
		 byte[] b = new byte[1024];    
         int len = -1;    
         ServletOutputStream sos = null;    
         try {    
             sos = response.getOutputStream();    
             while ((len = imageStream.read(b, 0, 1024)) != -1) {    
                 sos.write(b, 0, len);    
             }    
         } catch (IOException e) {    
             e.printStackTrace();    
         } finally {    
             if (sos != null) {    
                 try {    
                     sos.close();    
                 } catch (IOException e) {    
                     // TODO Auto-generated catch block    
                     e.printStackTrace();    
                 }    
             }    
       }    
		
		return NONE;
	}
	
	
	

}
