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
      "Delete property": function() {
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
							<h2 class="title">Property Information</h2>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<br>
							<div class="entry">
								<div class="objList">								
									<div class="tblRow">
										<div class="tblColHdr" style="width: 18%">Property Name</div>
										<div class="tblColHdr" style="width: 22%">Address</div>
										<div class="tblColHdr">Latitude</div>
										<div class="tblColHdr">Longitude</div>
										<div class="tblColHdr">Action</div>
									</div>
									<spring:url value="/property/add" var="addUrl" />
									<c:forEach var="property" items="${requestScope.lstProperties }">
										<div class="tblRow">
											<div class="tblCol" style="width: 150px">
												<c:out value="${property.name}" />
											</div>
											<div class="tblCol" style="width: 180px">
												<c:out value="${property.address}" />
											</div>
											<div class="tblCol">
												<c:out value="${property.latitude}" />
											</div>
											<div class="tblCol">
												<c:out value="${property.longitude}" />
											</div>
											<div class="tblCol">
												<c:choose>
													<c:when test="${authorized}">
														<spring:url value="/property/${property.id}/delete"
															var="deleteUrl" />
														<spring:url value="/property/${property.id}/update"
															var="updateUrl" />
														<button class="bttnNormal"
															onclick="location.href='${updateUrl}'">Update</button>
<!-- 														<button class="bttnCaution" -->
<%-- 															onclick="location.href='${deleteUrl}'">Delete</button> --%>														
														<button id="confirm${property.id}" value="${deleteUrl}" class="bttnCaution">Delete</button>
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
								<button class="bttnAction" onclick="location.href='${addUrl}'">Add
											Property</button>
								<div id="dialog-confirm" title="Warning">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>You are going to delete the Property. Are you sure?</p>
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