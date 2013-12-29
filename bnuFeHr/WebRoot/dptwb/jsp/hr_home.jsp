<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<s:set name="userList" value="pl.objectList"/>

<div class="m_right">

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form class="form-search">
				编号：<input class="input-medium search-query" type="text" name="bianhao" value="<s:property value="bianhao"/>"/> 
				姓名：<input class="input-medium search-query" type="text" name="truename" value="<s:property value="truename"/>"/> 
				<button type="submit" class="btn">查找</button>
			</form>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			
			<s:if test="#userList != null && !#userList.isEmpty()">
			<table class="table table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th>ID</th>
						<th>编号</th>
						<th>姓名</th>
						<th>机构</th>
						<th>级别</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#userList" status="listSt">
					<s:if test="#listSt.index % 4 == 0"><tr class="success"></s:if>
					<s:elseif test="#listSt.index % 4 == 1"><tr class="error"></s:elseif>
					<s:elseif test="#listSt.index % 4 == 2"><tr class="warning"></s:elseif>
					<s:elseif test="#listSt.index % 4 == 3"><tr></s:elseif>
					<s:else><tr class="info"></s:else>
						<td><s:property value="userid"/></td>
						<td><a href="<%=path %>/hrDetail.action?bianhao=<s:property value="bianhao"/>"><s:property value="bianhao"/></a></td>
						<td><s:property value="truename"/></td>
						<td><s:property value="jigou"/></td>
						<td><s:property value="jibie"/></td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			
			<div class="pagination">
				<ul><s:property value="pl.pageShowString" escape="false"/></ul>
            </div>
			</s:if>
			
		</div>
	</div>
</div>

</div><!-- m_right -->
</div><!-- m_cont -->
</div><!-- zwp_main_c2_a -->

</body>
</html>