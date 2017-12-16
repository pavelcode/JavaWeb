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

<title>设置权限</title>
<%@ include file="/WEB-INF/jsp/public/header.jsp"%>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/file.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />

<script type="text/javascript">
         $(function(){
			// 给所有的权限复选框添加事件
			$("[name=privilegeIds]").click(function(){
			   //选择父权限，选中父权限下的子权限
			    $(this).siblings("ul").find("input").attr("checked",this.checked);
			    
			    //选择子权限，选中它的父级权限
			    if(this.checked){
			       $(this).parents("li").children("input").attr("checked",true);
			    }else{
			       //当所有的子权限取消，父级权限取消
			       var size = $(this).parent().siblings("li").children("input:checked").size();
			       if(size ==0){
			          //设定父权限取消选中
			          $(this).parent().parent().siblings("input").attr("checked",false);
			          
			          var length= $(this).parent().parent().parent().siblings("li").children("input:checked").size();
			          if(length==0){
			             $(this).parent().parent().parent().parent().siblings("input").attr("checked",false);
			          }
			       }
			    }
			    //当任何一个input取消，都应该取消权限
			    //alert( $("[name=privilegeIds]").size());
			    //alert($("[name=privilegeIds]:checked").size())
			    var flag = $("[name=privilegeIds]").size() == $("[name=privilegeIds]:checked").size();
			    $("#cbSelectAll").attr("checked",flag);
			    

			});
			
			
			
			
		});
      

</script>
</head>

<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				配置权限
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<s:form action="role_setPrivilege" namespace="/" method="post">
			<s:hidden name="id"></s:hidden>
			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					正在为【${name}】配置权限
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<!--表头-->
						<thead>
							<tr align="LEFT" valign="MIDDLE" id="TableTitle">
								<td width="300px" style="padding-left: 7px;">
									<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
									<input type="CHECKBOX" id="cbSelectAll"  onclick="$('input[name=privilegeIds]').attr('checked',this.checked)"
								 />
									<label for="cbSelectAll">全选</label>
								</td>
							</tr>
						</thead>

						<!--显示数据列表-->
						<tbody id="TableData">
							<tr class="TableDetail1">
								<!-- 显示权限树 -->
								<td>
									<!--  <li id='li_46'>
				<input type='checkbox' name='resourceIdList' id='cb_46' onclick='doChecked("li_46", this.checked)'/>
				<label for='cb_46'><span class='folder' id='46'>部门管理</span></label>
	   </li>	 --> <%--   <s:checkboxlist list="privileges" name="privilegeIds" listKey="id" listValue="name"></s:checkboxlist> --%>

									<%-- 	<s:iterator value="privileges" var="privilege">
										<input type='checkbox' name='privilegeIds'
											<s:property value="%{id in privilegeIds?'checked':''}"/>
											value="${id}" id='cb_46'
											onclick='doChecked("li_46", this.checked)' />
										<label for='cb_46'><span class='folder' id='46'>${name}</span></label>
										<br />
									</s:iterator> --%>


									<ul id="root">
										<s:iterator value="privileges" var="privilege">
											<li><input type='checkbox' name='privilegeIds'
												<s:property value="%{id in privilegeIds?'checked':''}"/>
												value="${id}" id='cb_${id}'   /> 
												<label for='cb_${id}'><span
													class='folder' id='${id}'>${name}</span></label>
												<ul>
													<s:iterator value="children">
														<li id="li_${id}"><input type='checkbox' name='privilegeIds'
															<s:property value="%{id in privilegeIds?'checked':''}"/>
															
															value="${id}" id='cb_${id}' /> <label for='cb_${id}'><span
																class='folder' id='${id}'>${name}</span></label>
															<ul>
																<s:iterator value="children">
																	<li id="li_${id}"><input type='checkbox' name='privilegeIds'
																		<s:property value="%{id in privilegeIds?'checked':''}" />
																		
																		value="${id}" id='cb_${id}' /> <label for='cb_${id}'><span
																			class='folder' id='${id}'>${name}</span></label></li>
																</s:iterator>
															</ul></li>
													</s:iterator>
												</ul></li>
										</s:iterator>

									</ul> <!-- 展示为树状效果 --> <script type="text/javascript">
										$("#root").treeview();
									</script>



								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>

	<div class="Description">
		说明：<br /> 1，选中一个权限时：<br /> &nbsp;&nbsp;&nbsp;&nbsp; a，应该选中 他的所有直系上级。<br />
		&nbsp;&nbsp;&nbsp;&nbsp; b，应该选中他的所有直系下级。<br /> 2，取消选择一个权限时：<br />
		&nbsp;&nbsp;&nbsp;&nbsp; a，应该取消选择 他的所有直系下级。<br />
		&nbsp;&nbsp;&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并递归向上做这个操作。<br />

		3，全选/取消全选。<br /> 4，默认选中当前岗位已有的权限。<br />
	</div>
</body>
</html>
