<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script>
function profilesMain() {
window.location="<%=request.getContextPath()%>/profile/all";
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
									<h2 class="title">Add User</h2>
								</c:when>								
								<c:when test="${action == 'Update'}">
									<h2 class="title">Update User</h2>
								</c:when>
								<c:otherwise>
			     					<h2 class="title">User Profile Information</h2>
			 					</c:otherwise>
							</c:choose>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<div class="entry">
								<br>
								<spring:url value="/profile/${requestScope.action}/addOrUpdate"
									var="addOrUpdateUserProfile" />
								<form:form method="POST" action="${addOrUpdateUserProfile}"
									commandName="userView">
									<div class="objListDetails">
										<div class="tblRowDetails">
											<div class="tblColDetails">UserName</div>
											<div class="tblColBoxDetails">
												<form:input path="username" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="username" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Email</div>
											<div class="tblColBoxDetails">
												<form:input path="email" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="email" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">First Name</div>
											<div class="tblColBoxDetails">
												<form:input path="firstName" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="firstName" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
<!-- 										<div class="tblRowDetails"> -->
<!-- 											<div class="tblCol">State</div> -->
<!-- 											<div class="tblCol"> -->
<%-- 												<form:select path="state" > --%>
<%--    												<form:option value="NONE" label="--- Select ---"/> --%>
<%--    												<form:options items="${applicationScope.usStatesLst}" /> --%>
<%-- 												</form:select> --%>
<!-- 											</div> -->
<!-- 											<div class="tblColError"> -->
<%-- 												<form:errors path="state" cssClass="errorValMsg" --%>
<%-- 													element="div" /> --%>
<!-- 											</div> -->
<!-- 										</div> -->
										<div class="tblRowDetails">
											<div class="tblColDetails">Last Name</div>
											<div class="tblColBoxDetails">
												<form:input path="lastName" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="lastName" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Cell Phone</div>
											<div class="tblColBoxDetails">
												<form:input path="cellPhone" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="cellPhone" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Office Phone</div>
											<div class="tblColBoxDetails">
												<form:input path="officePhone" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="officePhone" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Role</div>
											<div class="tblColBoxDetails">
											<select name="role" style="width: 200px" >
															<option value="${requestScope.roleSelected }" selected="selected">${requestScope.roleSelected }</option>
															<c:forEach items="${requestScope.lstRoles }" var="role">
																<option value="${role.roleName }">${role.roleName }</option>
															</c:forEach>
													</select>
											</div>
<!-- 											<div class="tblColErrorDetails"> -->
<%-- 												<form:errors path="roles" cssClass="errorValMsg" --%>
<%-- 													element="div" /> --%>
<!-- 											</div> -->
										</div>
									</div>
									<br>
									<input class="bttnAction" type="button" value="&lt;&#45;Back"
												onclick="javascript:profilesMain()" />
<%-- 									<input type="hidden" value="${requestScope.userOID}" --%>
<!-- 										name="userId" /> -->
<%-- 									<input type="hidden" value="${requestScope.active}" --%>
<!-- 										name="active" /> -->
									<form:hidden path="id" />
									<form:hidden path="active" />
									<form:hidden path="password" />
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