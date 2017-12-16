<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>发表新主题</title>
<%@ include file="/WEB-INF/jsp/public/header.jsp"%>
<script src="${pageContext.request.contextPath}/script/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/script/ckeditor/samples/sample.css">
</head>

<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" /> 发表新主题
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id="MainArea">
		<s:form action="forum_save" namespace="/" method="post" style="margin: 0; padding: 0;">
		    <s:hidden name="forumId"></s:hidden>
			<div id="PageHead"></div>
			<center>
				<div class="ItemBlock_Title1">
					<div width=85% style="float:left">
						<font class="MenuPoint"> &gt; </font> 
						<s:a action="forum_list" namespace="/">论坛</s:a> 
						<font class="MenuPoint">
							&gt; </font> 
							<s:a action="forum_show?id=%{id}" namespace="/">
							${name}
							</s:a> 
							<font
							class="MenuPoint"> &gt;&gt; </font> 发表新主题
					</div>
				</div>
				<div class="ItemBlockBorder">
					<table border="0" cellspacing="1" cellpadding="1" width="100%"
						id="InputArea">
						<tr>
							<td class="InputAreaBg" height="30"><div class="InputTitle">标题</div></td>
							<td class="InputAreaBg"><div class="InputContent">
									<input type="text" name="title" class="InputStyle"
										style="width:100%" />
								</div></td>
						</tr>
						<tr height="240">
							<td class="InputAreaBg"><div class="InputTitle">内容</div></td>
							<td class="InputAreaBg"><div class="InputContent">
									<textarea id="content" name="content"></textarea>
									<script>

							// Replace the <textarea id="editor"> with an CKEditor
							// instance, using default configurations.
							CKEDITOR.replace( 'content', {
								uiColor: '#abcdef',
								toolbar: [
									[ 'Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink' ],
									[ 'TextColor', 'BGColor' ]
								]
							});

		</script>
								</div></td>
						</tr>
						<tr height="30">
							<td class="InputAreaBg" colspan="2" align="center"><input
								type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"
								style="margin-right:15px;" /> <a
								href="javascript:history.go(-1);"><img
									src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png" /></a></td>
						</tr>
					</table>
				</div>
			</center>
		</s:form>
	</div>

	<div class="Description">
		说明：<br />

	</div>
</body>
</html>