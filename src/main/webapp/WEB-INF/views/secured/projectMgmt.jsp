<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script>

$( function() {
  $( "#dialog-confirm" ).dialog({
	autoOpen: false,
    resizable: false,
    height:"auto",
    width:"auto",
    modal: true,
    buttons: {
      "Delete project": function() {
    	var elementID = $("#dialog-confirm").data("url");
    	var urlTo = $("#" + elementID).val();
    	$(location).attr('href', urlTo);
      },
      Cancel: function() {
        $( this ).dialog( "close" );
      }
    }
  }).prev(".ui-dialog-titlebar").css("background","red");
  $('.objList').on('click', '.bttnCaution', function() {
      $("#dialog-confirm").dialog("open").data('url', $(this).attr("id"));
    });
});

</script>
</head>
<body>
	<div id="page">
		<div id="content">
			<div class="post">
				<div class="bg1">
					<div class="bg2">
						<div class="bg3">
							<h2 class="title">Project Information</h2>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<br>
							<div class="entry">
								<div class="objList">								
									<div class="tblRow">
										<div class="tblColHdr">Project Name</div>
										<div class="tblColHdr" style="width: 200px">Description</div>
										<div class="tblColHdr" style="width: 50px">Status</div>
										<div class="tblColHdr" style="width: 50px">Comments</div>
										<div class="tblColHdr" style="width: 20%">Action</div>
									</div>
									<spring:url value="/project/add" var="addUrl" />
									<c:forEach var="project" items="${requestScope.lstProject }">
										<div class="tblRow">
											<div class="tblCol">
												<c:out value="${project.projectName}" />
											</div>
											<div class="tblCol">
												<c:out value="${project.description}" />
											</div>
											<div class="tblCol">
												<c:out value="${project.status}" />
											</div>
											<div class="tblCol">
												<c:out value="${project.comments}" />
											</div>
											<div class="tblCol">
												<c:choose>
													<c:when test="${authorized}">
														<spring:url value="/project/${project.id}/delete"
															var="deleteUrl" />
														<spring:url value="/project/${project.id}/update"
															var="updateUrl" />														
														<button class="bttnNormal"
															onclick="location.href='${updateUrl}'">Update</button>
<!-- 														<button class="bttnCaution" -->
<%-- 															onclick="location.href='${deleteUrl}'">Delete</button> --%>														
														<button id="confirm${project.id}" value="${deleteUrl}" class="bttnCaution">Delete</button>
													</c:when>
													<c:otherwise>
			     										No Actions
			 										</c:otherwise>
												</c:choose>
											</div>
										</div>
									</c:forEach>
								</div>
								<br>
								<c:choose>
									<c:when test="${authorized}">
										<button class="bttnAction" onclick="location.href='${addUrl}'">Add
											Project</button>
									</c:when>
								</c:choose>
								<div id="dialog-confirm" title="Warning">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>You are going to delete the Project. Are you sure?</p>
</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>