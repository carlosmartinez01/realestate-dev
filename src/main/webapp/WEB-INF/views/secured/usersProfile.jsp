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
      "Delete user": function() {
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
							<h2 class="title">Users Information</h2>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<c:if test="${not empty errorMessage}">
								<div class="error">${errorMessage}</div>
							</c:if>
							<br>
							<div class="entry">
								<div class="objList">								
									<div class="tblRow">
										<div class="tblColHdr">UserName</div>
										<div class="tblColHdr">First Name</div>
<!-- 										<div class="tblColHdr" style="width: 11%">City</div> -->
<!-- 										<div class="tblColHdr" style="width: 5%">State</div> -->										
										<div class="tblColHdr">Last Name</div>
										<div class="tblColHdr">Company</div>
										<div class="tblColHdr">Action</div>
									</div>
									<c:forEach var="user" items="${requestScope.lstUsers }">
										<div class="tblRow">
											<div class="tblCol">
												<c:out value="${user.username}" />
											</div>
											<div class="tblCol">
												<c:out value="${user.firstName}" />
											</div>
											<div class="tblCol">
												<c:out value="${user.lastName}" />
											</div>
											<div class="tblCol">
												<c:out value="${user.company.companyName}" />
											</div>
											<div class="tblCol">
												<c:choose>
													<c:when test="${authorized}">
														<spring:url value="/profile/${user.id}/${user.username }/delete"
															var="deleteUrl" />
														<spring:url value="/profile/${user.id}/details"
															var="detailsUrl" />
														<spring:url value="/profile/all/add" var="addUrl" />

														<button class="bttnNormal"
															onclick="location.href='${detailsUrl}'">Update</button>													
														<button id="confirm${user.id}" value="${deleteUrl}" class="bttnCaution">Delete</button>
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
									<c:when test="${validateTHIS}">
										<button class="bttnAction" onclick="location.href='${addUrl}'">Add
											User</button>
									</c:when>
								</c:choose>
								<div id="dialog-confirm" title="Warning">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>You are going to delete the User. Are you sure?</p>
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