package com.cblue.oa.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cblue.oa.entity.Privilege;
import com.cblue.oa.service.IPrivilegeService;

public class OAInitListener implements ServletContextListener {



	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		/* for(int i=0;i<100;i++)
		 System.out.println("*****contextInitialized***");*/
		//获得所有的权限
/*		ApplicationContext context = new ClassPathXmlApplicationContext("");
		context.getBean("privilegeService")*/
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		IPrivilegeService privilegeService = (IPrivilegeService)context.getBean("privilegeServiceImpl");
		List<Privilege> topPrivilege = privilegeService.getTopList();
		//把数据放入到Application中
		sce.getServletContext().setAttribute("topPrivilege", topPrivilege);
		//获得所有权限的action的url
		List<String> allUrl = privilegeService.getAllUrl();
		//把数据放入到Application中
		sce.getServletContext().setAttribute("allUrl", allUrl);
		
	}
	
	
	
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
