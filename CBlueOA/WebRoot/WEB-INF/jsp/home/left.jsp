<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>导航菜单</title>
    <%@ include file="/WEB-INF/jsp/public/header.jsp" %>
    <script language="JavaScript" src="${pageContext.request.contextPath}/script/menu.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/menu.css" />

  </head>
  
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
        <s:iterator value="#application.topPrivilege">
           
		    <s:if test="#session.loginUser.hasPrivilegeByName(name)">
			      <li class="level1">
			            <div onClick="menuClick(this)" class="level1Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/${id}.gif" class="Icon" />
			               ${name}
			            </div>
			            <ul style="display: none;" class="MenuLevel2">
			                <s:iterator value="children">
			                    <s:if test="#session.loginUser.hasPrivilegeByName(name)">
					                <li class="level2">
					                    <div class="level2Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" /> 
					                       <s:a action="%{url}" namespace="/" target="right">${name}</s:a>
					                    </div>
					                </li>
				                </s:if>
			                </s:iterator>
			               
			            </ul>
			        </li> 
		  </s:if> 
        </s:iterator>
      </ul>

</div>
</body>
</html>
