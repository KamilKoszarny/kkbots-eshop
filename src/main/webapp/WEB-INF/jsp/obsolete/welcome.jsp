<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<h2>Welcome on KKBots page!</h2>
	
	<form method="post" action="uservalidation">
		Login: 
		<input type="text" name="login" />
		<br/>Password:
		<input type="password" name="password" />
		<br/>
		<input type="submit" value="Log in">
	</form>
</body>
</html>