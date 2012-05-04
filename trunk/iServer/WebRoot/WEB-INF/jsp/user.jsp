<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
	<body>
		<table>
			<tr>
				<td>
						<h3 style="color:gray">role name</h3>
				</td>
			</tr>
			<tr>
				<td>
				<input id="rid" name="roleId"  />
				<select>
				<option id="1">1</option>
				<option id="2">2</option>
				<option id="3">3</option>
				</select>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td >
					<h3 style="color:gray">id</h3>
				</td>
				<td>
					<h3 style="color:gray">name</h3>
				</td>
				<td>
					<h3 style="color:gray">password</h3>
				</td>
				<td></td>
			</tr>
			<c:set var="list" value="${list}"></c:set>
			<c:choose>
				<c:when test="${fn:length(list)==0}">
					<tr>
						<td colspan=3>
							no result!
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="user">
						<tr>
							<td>
								<input id="uid${user.userId}" type="text" value='${user.userId}'
									disabled=disabled />
							</td>
							<td>
								<input id="uname${user.userId}" type="text" value='${user.name}' />
							</td>
							<td>
								<input id="upassword${user.userId}" type="text"
									value='${user.password}' />
							</td>
							<td>
								<input type="button" value="update"
									onclick="update('${user.userId}')" />
							</td>
							<td>
								<input type="button" value="delete"
									onclick="del('${user.userId}')" />
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<form id="updateForm" action="/cloud/user" method="post">
			<input id="uid" name="userId" type="hidden" />
			<input id="uname" name="name" type="hidden" />
			<input id="upassword" name="password" type="hidden" />
			<input name="_method" value="put" type="hidden" />
		</form>
		<form id="deleteForm" action="/cloud/user" method="post">
			<input name="_method" value="delete" type="hidden" />
		</form>
		<form id="newForm" action="/cloud/user" method="post">
			<table>
				<tr>
					<td width=100>
						user_name:
						<input id="user_name" name="name" type="text" />
					</td>
				</tr>
				<tr>
					<td width=100>
						user_password:
						<input id="user_password" name="password" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="add" />
					</td>
				</tr>

			</table>
		</form>
	</body>
	<Script>
	function del(id) {
		document.getElementById('deleteForm').action = '/cloud/user/' + id;
		document.getElementById('deleteForm').submit();
	}

	function update(id) {
		var form=document.getElementById('updateForm');
		form.action = '/cloud/user/' + id;
		form.uid.value=document.getElementById('uid'+id).value;
		form.uname.value=document.getElementById('uname'+id).value;
		form.upassword.value=document.getElementById('upassword'+id).value;
		form.submit();
	}
</Script>
</html>