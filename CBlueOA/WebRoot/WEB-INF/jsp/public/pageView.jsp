<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id=PageSelectorBar>
			<div id=PageSelectorMemo>
				页次：${currentPage}/${pageCount}页 &nbsp;
				每页显示：${pageSize}条 &nbsp;
				总记录数：${recordCount}条
			</div>
			<div id=PageSelectorSelectorArea>
				<!--
				<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage2.png"/>
				-->
				<a href="javascript:void(0)" onclick="gotoPageNum(1)" title="首页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/></a>
				
				<s:iterator begin="beginIndex" end="endIndex" var="s">
				  <s:if test="currentPage==#s">
					  <span class="PageSelectorNum" style="color:red">
					      <s:property value="#s"/>
					  </span>
				  </s:if>
				  <s:else>
				     <span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum( <s:property value="#s"/>);">
					      <s:property value="#s"/>
					  </span>
				  </s:else>
				</s:iterator>
				
				<!--
				<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage2.png"/>
				-->
				<a href="#" title="尾页" onclick="gotoPageNum(${pageCount})" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/></a>
				
				转到：
				<select id="select" onchange="gotoPageNum(this.value)">
				    <s:iterator begin="1" end="pageCount" var="s">
				      <option value="<s:property value='#s'/>"><s:property value="#s"/></option>
				    </s:iterator>
				</select>
				<script type="text/javascript">
				    $("#select").val(${currentPage});
				</script>
			<!-- 	
			<input onFocus="this.select();" maxlength="2" class="inputStyle" type="text" value="1" name="currPage" tabindex="0"/>		
			 -->	
			<!--  <input type="submit" name="goBtn" value="Go" class="MiddleButtonStyle" /> -->
			</div>
		</div>
		
		<script type="text/javascript">
		   function gotoPageNum(i){
		      //$("#currentPage").val(i);
		      //alert("<input type='hidden' name='currentPage' value='"+i+"'>");
		      $("#pageForm").append("<input type='hidden' name='currentPage' value='"+i+"'>");
		      $("#pageForm").submit();
		   }
		</script>
