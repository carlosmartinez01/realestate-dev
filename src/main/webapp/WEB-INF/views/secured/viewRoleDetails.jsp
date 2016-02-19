<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script>
function roleMain() {
window.location="<%=request.getContextPath()%>/role";
}
</script>
</head>
<body>
	<div id="page">
		<div id="content">
			<div class="post">
				<div class="bg1">
					<div class="bg2">
						<div class="bg3">
							<c:choose>
								<c:when test="${action == 'Add'}">
									<h2 class="title">Add Role</h2>
								</c:when>								
								<c:when test="${action == 'Update'}">
									<h2 class="title">Update Role</h2>
								</c:when>
								<c:otherwise>
			     					<h2 class="title">Role Information</h2>
			 					</c:otherwise>
							</c:choose>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<div class="entry">
								<br>
								<!-- 								<button class="bttnAction" -->
								<%-- 									onclick="location.href='<%=request.getContextPath() %>/property'">Back</button>	 --%>
								<spring:url value="/role/${requestScope.action}/addOrUpdate"
									var="addOrUpdateRole" />
								<form:form method="POST" action="${addOrUpdateRole}"
									commandName="roleView">
									<div class="objListDetails">
										<div class="tblRow">
											<div class="tblColDetails">Role Name</div>
											<div class="tblColBoxDetails">
												<form:input path="role" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="role" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Description</div>
											<div class="tblColBoxDetails">
												<form:textarea path="description" rows="2" cols="30" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="description" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
									</div>
									<br>
									<input class="bttnAction" type="button" value="&lt;&#45;Back"
												onclick="javascript:roleMain()" />
									<input type="hidden" value="${requestScope.roleOID}"
										name="roleId" />
									<input class="bttnAction" type="submit"
										value="${requestScope.action}">
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>