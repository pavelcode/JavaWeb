package com.cblue.oa.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.cblue.oa.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	/**
	 * 在进入action之前，判断用户是否有权访问这个action
	 *   
	 */
	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		//得到用户
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		//得到action访问的路径
		String actionName = ai.getProxy().getActionName();
		/*String nameSpace = ai.getProxy().getNamespace();
		String url = nameSpace+actionName;*/
		for(int i=0;i<100;i++)
		System.out.println(user+"----"+actionName);
		//add
		if(actionName.contains("UI")){
			actionName = actionName.substring(0, actionName.length()-2);
		}
		
		/**
		 * 当用户为空，
		 *    1 用户没有登录，就直接访问了里面Action
		 *    2 用户的跳转登录的action
		 */
		if(user==null){
			if("user_login".equals(actionName)){
				return ai.invoke();
			}else{
				return "loginUI";
			}
		}else{
			//如果用户存在，说明用户登录。看下用户是否有访问action的权限
			List<String> allUrl = (List<String>)ServletActionContext.getServletContext().getAttribute("allUrl");
			//判断action是否是我们需要管理的action，如果不是，直接放行
			if(allUrl.contains(actionName)){
				 //判断权限
				boolean flag = user.hasPrivilegeByUrl(actionName);
				if(flag){
					return ai.invoke();
				}else{
					return "noPrivilege";
				}
				
			}else{
				return ai.invoke();
			}
		}
		
	}

}
