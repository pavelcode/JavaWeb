<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
     <title>用户列表</title>
    <%@ include file="/WEB-INF/jsp/public/header.jsp" %>
  </head>
  
  <body>
   
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">登录名</td>
                <td width="100">姓名</td>
                <td width="100">所属部门</td>
                <td width="200">岗位</td>
                <td>备注</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
          <s:iterator value="users" var="user">
	            <tr class="TableDetail1 template">
	                <td>${user.loginName}&nbsp;</td>
	                <td>${user.name}&nbsp;</td>
	                <td>${user.department.name}&nbsp;</td>
	                <td>
	                 <s:iterator value="roles" var="role">
	                   ${name}&nbsp;
	                 </s:iterator>
	                </td>
	                <td>${user.description}&nbsp;</td>
	                <td><s:a onclick="return window.confirm('你确定要删除吗?')" action="user_delete?id=%{id}" namespace="/">删除</s:a>
	                    <s:a action="user_updateUI?id=%{id}" namespace="/">修改</s:a>
						<s:a onClick="return window.confirm('您确定要初始化密码为123吗？')" action="user_initPassword?id=%{id}" namespace="/">初始化密码</s:a>
	                </td>
	            </tr>
           </s:iterator>
        </tbody>
        
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="user_addUI" namespace="/">
              <img src="${pageContext.request.contextPath}/style/images/createNew.png" />
            </s:a>
        </div>
    </div>
</div>
  </body>
</html>
