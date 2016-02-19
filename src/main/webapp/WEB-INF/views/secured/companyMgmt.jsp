<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script>
	$(function() {
		$("#dialog-confirm").dialog({
			autoOpen : false,
			resizable : false,
			height : "auto",
			width : "auto",
			modal : true,
			buttons : {
				"Delete company" : function() {
					var elementID = $("#dialog-confirm").data("url");
					var urlTo = $("#" + elementID).val();
					$(location).attr('href', urlTo);
				},
				Cancel : function() {
					$(this).dialog("close");
				}
			}
		}).prev(".ui-dialog-titlebar").css("background", "red");
		$('.objList').on(
				'click',
				'.bttnCaution',
				function() {
					$("#dialog-confirm").dialog("open").data('url',
							$(this).attr("id"));
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
							<h2 class="title">Company Information</h2>
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<br>
							<div class="entry">
								<div class="objList">
									<div class="tblRow">
										<div class="tblColHdr">Company Name</div>
										<div class="tblColHdr">Address</div>
										<div class="tblColHdr" style="width: 11%">City</div>
										<div class="tblColHdr" style="width: 5%">State</div>
										<div class="tblColHdr">Action</div>
									</div>
									<spring:url value="/company/add" var="addUrl" />
									<c:forEach var="company" items="${requestScope.lstCompany }">
										<div class="tblRow">
											<div class="tblCol">
												<c:out value="${company.companyName}" />
											</div>
											<div class="tblCol">
												<c:out value="${company.address}" />
											</div>
											<div class="tblCol" style="width: 11px">
												<c:out value="${company.city}" />
											</div>
											<div class="tblCol" style="width: 5px">
												<c:out value="${company.state}" />
											</div>
											<div class="tblCol">
												<spring:url value="/company/${company.id}/delete"
													var="deleteUrl" />
												<spring:url value="/company/${company.id}/update"
													var="updateUrl" />
												<spring:url value="/company/${company.id}/userToCompany"
													var="addUserUrl" />
												<button class="bttnNormal"
													onclick="location.href='${updateUrl}'">Update</button>
												<!-- 														<button class="bttnCaution" -->
												<%-- 															onclick="location.href='${deleteUrl}'">Delete</button> --%>
												<button id="confirm${company.id}" value="${deleteUrl}"
													class="bttnCaution">Delete</button>
												<button class="bttnSpecialAction"
													onclick="location.href='${addUserUrl}'">&#40;&#43;&#41;User</button>
											</div>
										</div>
									</c:forEach>
								</div>
								<br>
								<button class="bttnAction" onclick="location.href='${addUrl}'">Add
									Company</button>
								<div id="dialog-confirm" title="Warning">
									<p>
										<span class="ui-icon ui-icon-alert"
											style="float: left; margin: 0 7px 20px 0;"></span>You are
										going to delete the Company. Are you sure?
									</p>
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