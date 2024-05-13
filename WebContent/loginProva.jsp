<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null){
			for (String error: errors){ %>
				<%=error %> <br>		
			<%
			}
		}
	%>

	<form action="Login" method="post"> 
	<fieldset>
	     <legend>Login Custom</legend>
	     <label for="username">Username</label>
	     <input id="username" type="text" name="username" placeholder="enter username">
	     <br>   
	     <label for="password">Password</label>
	     <input id="password" type="password" name="password" placeholder="enter password">
	     <br>
	     <input type="submit" value="Login"/>
	     <input type="reset" value="Reset"/>
	</fieldset>
	</form> 
</body>
</html>