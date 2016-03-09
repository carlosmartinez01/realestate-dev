<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script>
function projectMain() {
window.location="<%=request.getContextPath()%>/project";
}
</script>
<script type="text/javascript" note_url='<%=request.getContextPath()%>/note' obj_type='<%=request.getAttribute("objectType")%>' obj_id='<%=request.getAttribute("projectOID")%>'
src="<%=request.getContextPath()%>/resources/js/notes.js"></script>
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
									<h2 class="title">Add Project</h2>
								</c:when>								
								<c:when test="${action == 'Update'}">
									<h2 class="title">Update Project</h2>
								</c:when>
								<c:otherwise>
			     					<h2 class="title">Project Information</h2>
			 					</c:otherwise>
							</c:choose>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<div class="entry">
								<br>
								<spring:url value="/project/${requestScope.action}/addOrUpdate"
									var="addOrUpdateProject" />
								<form:form method="POST" action="${addOrUpdateProject}"
									commandName="projectView">
									<div class="objListDetails">
										<div class="tblRowDetails">
											<div class="tblColDetails">Project Name</div>
											<div class="tblColBoxDetails">
												<form:input path="projectName" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColError">
												<form:errors path="projectName" cssClass="errorValMsg"
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
										<div class="tblRowDetails">
											<div class="tblColDetails">Status</div>
											<div class="tblColBoxDetails">
												<form:input path="status" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="status" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Comments</div>
											<div class="tblColBoxDetails">
												<form:input path="comments" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="comments" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Address</div>
											<div class="tblColBoxDetails">
												<form:input path="address" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="address" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">City</div>
											<div class="tblColBoxDetails">
												<form:input path="city" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="city" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">State</div>
											<div class="tblColBoxDetails">
												<form:select path="state" >
   												<form:option value="NONE" label="--- Select ---"/>
   												<form:options items="${applicationScope.usStatesLst}" />
												</form:select>
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="state" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Zip Code</div>
											<div class="tblColBoxDetails">
												<form:input path="zipCode" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="zipCode" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
									</div>
									<br>
									<input class="bttnAction" type="button" value="&lt;&#45;Back"
												onclick="javascript:projectMain()" />
									<input type="hidden" value="${requestScope.projectOID}"
										name="projectId" />
									<input class="bttnAction" type="submit"
										value="${requestScope.action}">
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${action != 'Add'}">
			<div style="clear: both;">&nbsp;</div>
			<div style="clear: both;">&nbsp;</div>
			<div class="divNoteContainer">
				<div class="noteTitle">Notes</div>
				<div class="notesDiv">
					<div class="notesContainer" id="axNotesContainer"></div>
					<div id="axNoteBox"></div>
				</div>
			</div>
		</c:if>
		<div style="clear: both;">&nbsp;</div>
	</div>
</body>
</html>