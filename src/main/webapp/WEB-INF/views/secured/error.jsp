<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title></title>
</head>
<body>
<div id="page">
		<div id="content">
	<div class="post">
		<div class="bg1">
			<div class="bg2">
				<div class="bg3">
					<h2 class="title">Ooops!!!</h2>
					<c:if test="${not empty errMsg}">
						<div class="error">${errMsg}</div>
					</c:if>
					<c:if test="${not empty errMsgStack}">
						<div class="error">${errMsgStack}</div>
					</c:if>
					<br>
					<div class="entry">
					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>