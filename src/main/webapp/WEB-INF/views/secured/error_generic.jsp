<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title></title>
</head>
<body>
			<div class="post">
				<div class="bg1">
					<div class="bg2">
						<div class="bg3">
							<h2 class="title">Oops! Something went wrong</h2>
							<div class="entry">	
							<br>
							<div class="error">
								<p>Error Message
								<br>
								${errMsg }</p>				
								<p>Error Code
								<br>
								${errMsgStack }</p>									
							</div>
							
							<p><strong><a href="<%=request.getContextPath() %>/profile/userMgmt">&lt;--RETURN</a></strong></p><br>	
												
							</div>
						</div>
					</div>
				</div>
			</div>

</body>
</html>
