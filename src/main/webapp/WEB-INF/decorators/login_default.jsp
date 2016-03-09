<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
 <title>Maverik Real Estate<sitemesh:write property='title'/></title>
 <link href="<%=request.getContextPath() %>/resources/styles/style.css" rel="stylesheet" type="text/css" media="screen" />
 <link href="<%=request.getContextPath() %>/resources/styles/default.css" rel="stylesheet" type="text/css" media="screen" />
 <sitemesh:write property='head'/> 
 </head>
 
 <body> 
 <div id="wrapper">    
    	<div id="header">
			<div id="logo">
				<h1><a href="#">Maverik</a></h1>
				<p>Adventure's First Stop</p>
			</div>
			
		</div>
		
		<div id="content">
 			<sitemesh:write property='body'/>
		</div>        
  </div>
    <div id="footer-wrapper">
	<div id="footer">
		<p>&copy; All rights reserved. Design by <a href="http://www.maverik.com" rel="nofollow">MAVERIK</a></p>
	</div>
	<!-- end #footer -->
</div>

 </body>
</html>