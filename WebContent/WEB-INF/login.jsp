<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>	
	<jsp:include page="header.jsp"></jsp:include>

	 <div class = "login_container">
	 	<div>
		<c:forEach var="error" items="${form.formErrors}">
			<h3 style="color:red"> ${error} </h3>
		</c:forEach>
		</div>
			${nouser} 
		<form action ="login.do" method="post">	
            <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" name="email"  class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1">
            </div>
			<input type = "submit" name="action" value ="Login" class ="btn btn-success" >
        </form>
    </div>
	<div style="	height: 338px;
	width: 100%;	"></div>
    
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>