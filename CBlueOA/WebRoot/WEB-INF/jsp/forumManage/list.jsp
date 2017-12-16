<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>版块列表</title>
    
	 <%@ include file="/WEB-INF/jsp/public/header.jsp" %>

  </head>
  
  <body>
   <div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 版块管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="250px">版块名称</td>
                <td width="300px">版块说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="forumList">
         <s:iterator value="recordList" var="forum" status="s">
			<tr class="TableDetail1 template">
				<td>${forum.name}&nbsp;</td>
				<td>${forum.description}&nbsp;</td>
				<td><s:a onclick="return window.confirm('你确定要删除吗')" namespace="/" action="forumManage_delete?id=%{id}">删除</s:a>
					<s:a action="forumManage_updateUI?id=%{id}" namespace="/">修改</s:a>
						<s:if test="#s.first">
						  上移
						</s:if>
						<s:else>
						 <s:a action="forumManage_moveUp?id=%{id}" namespace="/">上移</s:a>
						</s:else>
						<s:if test="#s.last">
						  下移
						</s:if>
						<s:else>
					       <s:a action="forumManage_moveDown?id=%{id}" namespace="/">下移</s:a>
					    </s:else>
				</td>
			</tr>
		  </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="forumManage_addUI" namespace="/">
            <img src="${pageContext.request.contextPath}/style/images/createNew.png" />
            </s:a>
        </div>
    </div>
</div>

<!--分页信息-->
		<s:form id="pageForm" action="forumManage_list" namespace="/" method="post"></s:form>
		<%@include file="/WEB-INF/jsp/public/pageView.jsp"%>
  </body>
</html>
