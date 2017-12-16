<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>岗位列表</title>
     <%@ include file="/WEB-INF/jsp/public/header.jsp" %>
  </head>
  
  <body>
    <div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 岗位管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">岗位名称</td>
                <td width="300px">岗位说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
           <s:iterator value="roles" var="role">
					<tr class="TableDetail1 template">
						<td>${role.name}&nbsp;</td>
						<td>${role.description}&nbsp;</td>
						<td><s:a onClick="return window.confirm('你确定删除这条记录吗')" action="role_delete?id=%{id}" namespace="/">删除</s:a>
							<s:a action="role_updateUI?id=%{id}" namespace="/">修改</s:a>
							<s:a action="role_setPrivilegeUI?id=%{id}" namespace="/">设置权限</s:a>
						</td>
					</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="role_addUI" namespace="/">
             <img src="${pageContext.request.contextPath}/style/images/createNew.png" />
            </s:a>
        </div>
    </div>
</div>
  </body>
</html>
