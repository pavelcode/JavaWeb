<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>用户信息</title>
    
	<%@ include file="/WEB-INF/jsp/public/header.jsp" %>
	<script type="text/javascript">
	    $(function(){
	       //document.getElementById("loginName")
	       $("#loginName").blur(function(){
	          //alert("222");
	          var value =  $(this).val();
	          //alert(value);
	          if(value!=null&&value.trim().length>0){
	            //使用Ajax发送数据到后台
	            var url="http://localhost:8080/CBlueOA/user_getLoginNameIsExist.do";
	          
	            /**
                                                参数1 访问url 
                                                参数2 提交的参数
                                                参数3 回调函数
	            */
	            $.post(url,{'loginName':value},function(data){
	                  if(data=='0'){
	                    //登录名不存在
	                      $("#ok").val("1");
	                      $("#showMsg").html("<font color='green'>可以使用</font>");
	                  }else{
	                    //登录名存在
	                     $("#ok").val("0");
	                     $("#showMsg").html("<font color='red'>不能使用</font>");
	                  }
	            });
	          
	          }
	          
	       });
	     
	    });
	    
	    function checkLoginName(){
	       //alert("111");
	        var flag = false;
	        var value = $("#ok").val();
	        if(value=="1"){
	           flag = true;
	        }
	        return flag;
	    }
	
	</script>

  </head>
  
  <body>
   
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="user_%{id==null?'add':'update'}" namespace="/" method="post" onsubmit="return checkLoginName()">
       <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 用户信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td><!-- <select name="departmentId" class="SelectStyle">
                                <option value="0" selected="selected">请选择部门</option>
                                <option value="7">┠总经理室</option>
                                <option value="1">┠市场部</option>
                                <option value="2">　┠咨询部</option>
                                <option value="3">　┠招生部</option>
                                <option value="4">┠教学部</option>
                                <option value="5">┠后勤部</option>
                            </select>  -->
                            <s:select list="departments" name="departmentId" cssClass="SelectStyle" listKey="id" listValue="name" headerKey="" headerValue="请选择部门"></s:select>
                        </td>
                    </tr>
                    <tr><td>登录名</td>
                        <td><s:textfield id="loginName" name="loginName" cssClass="InputStyle"/><span id="showMsg">111</span> *
							（登录名要唯一）
						</td>
						<s:hidden id="ok"  value="%{id==null?0:1}"></s:hidden>
                    </tr>
                    <tr><td>姓名</td>
                        <td><s:textfield  name="name" cssClass="InputStyle"/> *</td>
                    </tr>
					<tr><td>性别</td>
                        <td><!-- <input type="RADIO" name="sex" value="男" id="male"/><label for="male">男</label>
							<input type="RADIO" name="sex" value="女" id="female"/><label for="female">女</label> -->
						    <s:radio  name="sex" list="#{'0':'男','1':'女'}"></s:radio>
						</td>
                    </tr>
					<tr><td>联系电话</td>
                        <td><s:textfield name="phone" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><s:textfield name="email" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>备注</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位设置 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">岗位</td>
                        <td><!-- <select name="roleIdList" multiple="true" size="10" class="SelectStyle">
                                <option value="1">程序员</option>
                                <option value="2">行政秘书</option>
                                <option value="3">出纳</option>
                                <option value="4">总经理</option>
                                <option value="5">测试员</option>
                            </select> -->
                            <s:select list="rolesList" listKey="id" listValue="name"  name="roleIdList" multiple="true" size="10" cssClass="SelectStyle"></s:select>
                            按住Ctrl键可以多选或取消选择
                        </td>
                    </tr>
                </table>
            </div>
        </div>		
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	说明：<br />
	1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
	2，新建用户后，密码被初始化为"1234"。<br />
	3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
	4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
	5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br />
	6，修改用户信息时，登录名不可修改。
</div>
  </body>
</html>
