<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
     <title>待我审批</title>
    
	 <%@ include file="/WEB-INF/jsp/public/header.jsp" %>

  </head>
  
  <body>
   <div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 待我审批
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--
<div id="QueryArea">
	<div style="height: 30px">
		<form action="#">
		<table border=0 cellspacing=3 cellpadding=5>
			<tr>
				<td>按条件查询：</td>
				<td><select name="arg1" class="SelectStyle">
						<option value="">查看全部模板</option>
						<option value="0">领款单</option>
						<option value="10">工作报告</option>
						<option value="13">设备采购单</option>
						<option value="21">员工请假单</option>
						<option value="22">加班申请</option>
					</select>
				</td>
				<td><a href=""><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a></td>
			</tr>
		</table>
	
		</form>
	</div>
</div>
-->
<form>
	<input type="hidden" name="pageNum" value="1" />
</form>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="250">标题</td>
				<td width="115">申请人</td>
				<td width="115">申请日期</td>
				<td>相关操作</td>
			</tr>
		</thead>		
		
		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
           <s:iterator value="taskViews">
			<tr class="TableDetail1 template">
				<td>${apply.title}</td>
				<td>${apply.applyUser.name}&nbsp;</td>
				<td><s:date name="apply.applyDate" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
				<td><s:a action="flow_checkUI?applyId=%{apply.id}&taskId=%{task.id}" namespace="/">审批处理</s:a>
					<!-- <a href="showForm.html">查看申请信息</a> -->
					<s:a action="flow_historyRecord?applyId=%{apply.id}" namespace="/">查看流转记录</s:a>
				</td>
			</tr>
			 </s:iterator>
        </tbody>
       
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail"><div id="TableTail_inside"></div></div>
</div>

<!--分页信息-->

<div class="Description">
	说明：<br />
	1，这里列出的所有的表单状态都为"审批中"。
</div>

  </body>
</html>
