<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script>
function companyMain() {
window.location="<%=request.getContextPath()%>/company";
}
</script>
<script>
$( function() {
	$("#update-company").on("click", function(e){;
    	e.preventDefault();
    	$('#companyForm').attr('action', '<%=request.getContextPath()%>/company/updateCompany')[0].submit();
	});
});
</script>
<script type="text/javascript"
	note_url='<%=request.getContextPath()%>/note'
	obj_type='<%=request.getAttribute("objectType")%>'
	obj_id='<%=request.getAttribute("companyOID")%>'
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
									<h2 class="title">Add Company</h2>
								</c:when>
								<c:when test="${action == 'Update'}">
									<h2 class="title">Update Company</h2>
								</c:when>
								<c:otherwise>
									<h2 class="title">Company Information</h2>
								</c:otherwise>
							</c:choose>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<div class="entry">
								<br>
								<spring:url value="/company/addCompany" var="addCompany" />
								<form:form method="POST" action="${addCompany}"
									commandName="companyView" id="companyForm">
									<div class="objListDetails">
										<div class="tblRowDetails">
											<div class="tblColDetails">Company Name</div>
											<div class="tblColBoxDetails">
												<form:input path="companyName" type="text"
													cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="companyName" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Address</div>
											<div class="tblColBoxDetails">
												<form:textarea path="address" rows="2" cols="30" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="address" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Suite</div>
											<div class="tblColBoxDetails">
												<form:input path="suite" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="suite" cssClass="errorValMsg"
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
												<form:select path="state">
													<form:option value="NONE" label="--- Select ---" />
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
									<c:if test="${action == 'Add'}">
										<input class="bttnAction" type="submit" value="Add">
									</c:if>
									<c:if test="${action != 'Add'}">
										<input class="bttnAction" type="button" id="update-company"
											value="Update" />
									</c:if>
									<input class="bttnAction" type="button" value="&lt;&#45;Back"
										onclick="javascript:companyMain()" />
									<form:hidden path="id" />
									<form:hidden path="active" />
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