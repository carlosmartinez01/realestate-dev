<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/resources/styles/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

	<div class="post">
				<div class="bg1">
					<div class="bg2">
						<div class="bg3">
							<div class="entry">								
								<p>You are trying to reach out <b> ${resultBean.text}. </b> </p>							
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>