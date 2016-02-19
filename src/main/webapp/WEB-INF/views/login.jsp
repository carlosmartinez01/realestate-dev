<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Real Estate</title>
<link href="<%=request.getContextPath() %>/resources/styles/login.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body onload='document.loginForm.j_username.focus();'>
 
	<div id="login-box">
 
		<h2>Login with Username and Password</h2>
 
		<c:if test="${not empty error}">
			<div class="loginError">${error}</div>
		</c:if>
		
		<c:url value="/j_spring_security_check" var="loginUrl" />
 
		<form name='loginForm'
			action="${loginUrl}" method='POST'>
		  <table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td colspan='2' style="padding-top: 10px"><input name="submit" type="submit"
					value="Submit" /></td>
			</tr>
		  </table>
		</form>
		<br>
		<a class="forgotPassLink" href="reset">FORGOT YOUR PASSWORD?</a>		
	</div>
</body>
</html>