<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
<script type="text/javascript">
$(document).ready(function(){
	$("#adminButtonContainer").hide();
	$("#adminButtonContainer").show('blind', 1000);
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
							<c:if test="${not empty messageForm}">
								<div class="msg">${messageForm}</div>
							</c:if>
							<br>
							<div class="entry">
							<div class="adminButtonContainer" id="adminButtonContainer">
								<div class="divAdminButton"><a href="<%=request.getContextPath()%>/property" class="propertyAdminBtn" id="propertyButton">Property Administration</a></div>
								<div class="divAdminButton"><a href="<%=request.getContextPath()%>/role" class="roleAdminBtn" id="roleButton">Role Administration</a></div>
								<div class="divAdminButton"><a href="<%=request.getContextPath()%>/company" class="companyAdminBtn" id="CompanyButton">Company Administration</a></div>
								<div class="divAdminButton"><a href="<%=request.getContextPath()%>/profile/all" class="usersAdminBtn" id="usersButton">Users Administration</a></div>								
							</div>
							<div style="clear: both;">&nbsp;</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>