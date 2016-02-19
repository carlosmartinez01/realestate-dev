<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Real Estate</title>
<link href="<%=request.getContextPath() %>/resources/styles/login.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body onload='document.loginForm.username.focus();'>
 
	<div id="login-box">
 		
		<h2>Reset your password</h2><br>
 
		<c:if test="${not empty resetPassMsg}">
			<div class="loginMsg">${resetPassMsg}</div>
		</c:if>
		
		<c:if test="${not empty noUserMessage}">
			<div class="loginError">${noUserMessage}</div>
		</c:if>
 
 		<c:if test="${render}">
		<form name='loginForm'
			action="resetUserPassword" method='POST'>
		  <table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td colspan='2' style="padding-top: 10px"><input name="submit" type="submit" size="300%"
					value="Reset" /></td>
			</tr>
		  </table>
		</form>
		</c:if>
		<br><p><a class="forgotPassLink" href="<%=request.getContextPath() %>">&lt;--RETURN</a></p>
	</div>
</body>
</html>