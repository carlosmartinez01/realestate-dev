<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- This line is only for search option -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maverik Real Estate Login
<sitemesh:write	property='title' /></title>
</title>
<link
	href="<%=request.getContextPath()%>/resources/styles/maverikStyle.css"
	rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/jquery/jquery-ui.css" />
<script	src="<%=request.getContextPath()%>/resources/jquery/external/jquery/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/jquery/jquery-ui.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/utils.js"></script> --%>

<sitemesh:write property='head' />
</head>
<body>
	<div id="mainContainer">
		<div class="mainHeader">
			<div class="logo">
				<img
					src="<%=request.getContextPath()%>/resources/styles/images/logo1.png"
					alt="Maverik">
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