<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>CblueOA</title>
    


  </head>
  
	 <frameset rows="100,*,25" framespacing="0" border="0" frameborder="0">
	    <frame src="home_header.do" name="TopMenu"  scrolling="no" noresize />
	    <frameset cols="180,*" id="resize">
	        <frame noresize name="menu" src="home_left.do" scrolling="yes" />
	        <frame noresize name="right" src="home_right.do" scrolling="yes" />
	    </frameset>
	    <frame noresize name="status_bar" scrolling="no" src="home_footer.do" />
	</frameset>
	<noframes>
	<body>
	</body>
	</noframes>
</html>
