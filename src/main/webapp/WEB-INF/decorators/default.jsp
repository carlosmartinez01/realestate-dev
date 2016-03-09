<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- This line is only for search option -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Maverik Construction Management<sitemesh:write
		property='title' /></title>
<link href="<%=request.getContextPath()%>/resources/styles/style.css"
	rel="stylesheet" type="text/css" media="screen" />
<sitemesh:write property='head' />
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<div id="logo">
				<h1>
					<a href="#">Maverik</a>
				</h1>
				<p>Adventure's First Stop</p>
			</div>
			<div id="search">
				<form method="get" action="">
					<fieldset>
						<input type="text" name="s" id="search-text" size="15" /> <input
							type="submit" id="search-submit" value="GO" />
					</fieldset>
				</form>
			</div>

		</div>


		<div id="cssmenu">
			<ul>
				<li><a href="<%=request.getContextPath()%>">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/user/projects"><span>Projects</span></a></li>
				<li><a href="<%=request.getContextPath()%>/profile"><span>Profile</span></a></li>
				<li><a
					href="<%=request.getContextPath()%>/admin/adminProjects"><span>Working</span></a></li>
				<%-- 			<li><a href="<%=request.getContextPath() %>/property">Property</a></li> --%>

				<li class='active has-sub'><a href='#'><span>Administration</span></a>
					<ul>
						<li class='has-sub'><a
							href='<%=request.getContextPath()%>/property'><span>Property
									Administration</span></a></li>
						<li class='has-sub'><a
							href='<%=request.getContextPath()%>/role'><span>Role Administration</span></a>
						</li>
						<li class='has-sub'><a
							href='<%=request.getContextPath()%>/company'><span>Company Administration</span></a>
						</li>
						<li class='has-sub'><a
							href='<%=request.getContextPath()%>/project'><span>Project Administration</span></a>
						</li>
						<li class='has-sub'><a
							href='<%=request.getContextPath()%>/profile/all'><span>Users Administration</span></a>
						</li>
					</ul></li>

				<%-- 			<li><a href="<%=request.getContextPath() %>/testing">Testing</a></li> --%>
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm"></form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
				<li><a href="javascript:formSubmit()"><span>Logout</span></a></li>
			</ul>
		</div>
		<sitemesh:write property='body' />

	</div>
	<div id="footer-wrapper">
		<div id="footer">
			<p>
				&copy; All rights reserved. Design by <a href="http://templated.co"
					rel="nofollow">MAVERIK</a>
			</p>
		</div>
		<!-- end #footer -->
	</div>

</body>
</html>