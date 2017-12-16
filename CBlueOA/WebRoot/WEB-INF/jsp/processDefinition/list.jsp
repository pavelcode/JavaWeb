<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审批流程列表</title>
<%@ include file="/WEB-INF/jsp/public/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/easyui/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/easyui/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/easyui/demo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.easyui.min.js"></script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 审批流程管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="200px">流程名称</td>
				<td width="80px">最新版本</td>
                <td width="300px">说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

	  <script>
		    function aaa(deploymentId){
		       
		        var url = "processDefinition_getImage.do?id="+deploymentId;
		        //alert(url);
				   $.get(url, function(result){
				       //alert(result);
				      $("#showimage").attr("src",result);
				  });
		        $('#image').dialog('open');
		    }
		  </script>
		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="processDefList">
           <s:iterator value="processDefinitions" var="processDef">
			<tr class="TableDetail1 template">
					<td>${processDef.name}&nbsp;</td>
					<td align="CENTER">${processDef.version}&nbsp;</td>
					<td>${processDef.description}&nbsp;</td>
					<td><s:a onClick="return window.confirm('你确定要删除吗')" action="processDefinition_delete?key=%{key}">删除</s:a>
						<a  href="#" id="getImage" onclick="$('#image_${deploymentId}').dialog('open')">查看流程图</a>
					</td>
			</tr>
			<div id="image_${deploymentId}" class="easyui-dialog" title="流程图"style="width:500px;height:300px;max-width:800px;padding:10px;" data-options="closed:true,modal:true">
			     <img src='${pageContext.request.contextPath}/processDefinition_getImage.do?id=${deploymentId}'>
			</div>
		  </s:iterator>
		  
	
		  
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <table border="0" cellspacing="0" cellpadding="10" align="left">
                <tr>
			        <td><div class="FuncBtn">
                            <div class=FuncBtnHead></div>
                            <div class=FuncBtnMemo>
                                <s:a action="processDefinition_deployUI" namespace="/">部署流程定义文档</s:a>
                            </div>
                            <div class=FuncBtnTail></div>
                        </div></td>
                </tr>
			</table>
        </div>
    </div>
</div>

<div class="Description">
	说明：<br />
	1，列表显示的是所有流程定义（不同名称）的最新版本。<br />
	2，删除流程定义时，此名称的所有版本的流程定义都会被删除。<br />
</div>

</body>
</html>