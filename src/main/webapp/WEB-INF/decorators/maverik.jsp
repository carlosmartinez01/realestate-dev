<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- This line is only for search option -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maverik Construction Management
<sitemesh:write	property='title' /></title>
</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/jquery/jquery-ui.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script	src="<%=request.getContextPath()%>/resources/jquery/external/jquery/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/jquery/jquery-ui.js"></script>
<script	src="<%=request.getContextPath()%>/resources/jquery/plugin/jquery.simplemodal-1.4.4.js"></script>
<script	src="<%=request.getContextPath()%>/resources/jquery/plugin/jquery.formatDateTime.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/styles/maverikStyle.css"
	rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/utils.js"></script>
<sitemesh:write property='head' />
</head>
<body>
	<div id="mainContainer">
		<div class="mainHeader">
			<div class="logo">
				<img class="maverikLogo"
					src="<%=request.getContextPath()%>/resources/styles/images/logo1.png"
					alt="Maverik">
			</div>
			<div class="profileDetails">
				<p>Welcome <a class="profileInfo" href="<%=request.getContextPath()%>/profile"><span><c:out value="${userFullName}" /></span></a></p>
				<p id="time" class="dateTime"></p>
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
					<form action="${logoutUrl}" method="post" id="logoutForm"></form>
					<script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>
				<a href="javascript:formSubmit()"><img class="logoutImg" title="LOGOUT" src="<%=request.getContextPath()%>/resources/styles/images/logout.png" ></a>
			</div>
		</div>
		<div class="menu">
			<div id="cssmenu">
				<ul>
					<li><a href="<%=request.getContextPath()%>">Dashboard</a></li>
<%-- 					<li><a href="<%=request.getContextPath()%>/projects"><span>Projects</span></a></li> --%>
					<li><a href='<%=request.getContextPath()%>/properties/all'><span>Properties</span></a>
					<li><a href='<%=request.getContextPath()%>/admin'><span>Administration</span></a>

<%-- 					<li class='active has-sub'><a href='<%=request.getContextPath()%>/admin'><span>Administration</span></a> --%>
<!-- 						<ul> -->
<!-- 							<li class='has-sub'><a -->
<%-- 								href='<%=request.getContextPath()%>/property'><span>Property --%>
<!-- 										Administration</span></a></li> -->
<!-- 							<li class='has-sub'><a -->
<%-- 								href='<%=request.getContextPath()%>/role'><span>Role --%>
<!-- 										Administration</span></a></li> -->
<!-- 							<li class='has-sub'><a -->
<%-- 								href='<%=request.getContextPath()%>/company'><span>Company --%>
<!-- 										Administration</span></a></li> -->
<!-- 							<li class='has-sub'><a -->
<%-- 								href='<%=request.getContextPath()%>/project'><span>Project --%>
<!-- 										Administration</span></a></li> -->
<!-- 							<li class='has-sub'><a -->
<%-- 								href='<%=request.getContextPath()%>/profile/all'><span>Users --%>
<!-- 										Administration</span></a></li> -->
<!-- 						</ul> -->
					</li>
				</ul>
			</div>
		</div>
		<div class="contentContainer">
			<sitemesh:write property='body' />
		</div>
		<div class="footer">
			<p>
				&copy; All rights reserved. Design by <a href="http://www.maverikrealestate.com/"
					rel="nofollow">MAVERIK</a>
			</p>
		</div>
	</div>
</body>
</html>