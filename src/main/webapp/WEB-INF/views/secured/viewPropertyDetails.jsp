<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script>
function propertyMain() {
window.location="<%=request.getContextPath()%>/property";
}
</script>
<script>
$( function() {
	$("#update-property").on("click", function(e){;
    	e.preventDefault();
    	$('#propertyForm').attr('action', '<%=request.getContextPath()%>/property/updateProperty')[0].submit();
	});
});
</script>
<script type="text/javascript" note_url='<%=request.getContextPath()%>/note' obj_type='<%=request.getAttribute("objectType")%>' obj_id='<%=request.getAttribute("propertyOID")%>'
src="<%=request.getContextPath()%>/resources/js/notes.js"></script>
</head>
<body>
	<div id="page">
		<div id="content">
			<div class="post">
				<div class="bg1">
					<div class="bg2">
						<div class="bg3">
							<h2 class="title">Property Information</h2>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<div class="entry">
								<br>
								<spring:url value="/property/addProperty"
									var="addProperty" />
								<form:form method="POST" action="${addProperty}"
									commandName="propertyView" id="propertyForm">
									<div class="objListDetails">
										<div class="tblRowDetails">
											<div class="tblColDetails">Property Name</div>
											<div class="tblColDetails">
												<form:input path="name" type="text" cssClass="genericBox" />
											</div>
											<div class="tblColDetailsErrorDetails">
												<form:errors path="name" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Address</div>
											<div class="tblColDetails">
												<form:textarea path="address" rows="2" cols="30" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Latitude</div>
											<div class="tblColDetails">
												<form:input path="latitude" type="text"
													cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="latitude" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
										<div class="tblRowDetails">
											<div class="tblColDetails">Longitude</div>
											<div class="tblColDetails">
												<form:input path="longitude" type="text"
													cssClass="genericBox" />
											</div>
											<div class="tblColErrorDetails">
												<form:errors path="longitude" cssClass="errorValMsg"
													element="div" />
											</div>
										</div>
									</div>
									<br>
									<c:if test="${action == 'Add'}">
										<input class="bttnAction" type="submit" value="Add">
									</c:if>
									<c:if test="${action != 'Add'}">
										<input class="bttnAction" type="button" id="update-property"
											value="Update" />
									</c:if>
									<input class="bttnAction" type="button" value="&lt;&#45;Back"
										onclick="javascript:propertyMain()" />
									<form:hidden path="id" />
									<form:hidden path="status" />
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