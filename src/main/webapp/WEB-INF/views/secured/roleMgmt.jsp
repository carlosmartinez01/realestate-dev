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
      "Delete role": function() {
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
							<h2 class="title">Role Information</h2>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<br>
							<div class="entry">
								<div class="objList">								
									<div class="tblRow">
										<div class="tblColHdr">Role Name</div>
										<div class="tblColHdr" style="width: 200px">Description</div>
										<div class="tblColHdr" style="width: 20%">Action</div>
									</div>
									<c:forEach var="role" items="${requestScope.lstRoles }">
										<div class="tblRow">
											<div class="tblCol">
												<c:out value="${role.roleName}" />
											</div>
											<div class="tblCol">
												<c:out value="${role.description}" />
											</div>
											<div class="tblCol">
												<c:choose>
													<c:when test="${unauthorized}">
														<spring:url value="/role/${role.id}/delete"
															var="deleteUrl" />
														<spring:url value="/role/${role.id}/update"
															var="updateUrl" />
														<spring:url value="/role/add" var="addUrl" />
														<button class="bttnNormal"
															onclick="location.href='${updateUrl}'">Update</button>
<!-- 														<button class="bttnCaution" -->
<%-- 															onclick="location.href='${deleteUrl}'">Delete</button> --%>														
														<button id="confirm${role.id}" value="${deleteUrl}" class="bttnCaution">Delete</button>
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
									<c:when test="${unauthorized}">
										<button class="bttnAction" onclick="location.href='${addUrl}'">Add
											Role</button>
									</c:when>
								</c:choose>
								<div id="dialog-confirm" title="Warning">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>You are going to delete the Role. Are you sure?</p>
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