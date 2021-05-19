<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>profile</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	 <div class = "content">
      <div class = "main_container">
        <div>
          <div class = "main_left main_blocks">
         	  <img src="Images/profile.png" class="welcome_img" alt="profile img" style ="height:164px; width :450px;">
          </div>
        </div>
        <div class = "main_right main_blocks">
            <h4>User name: ${user.getUsername() }</h4>
            <h5>Email:  ${user.getEmail() }</h5>
        </div>
        <div class="main_down main_blocks">
          <p class="container_name">Ваши избранные комиксы</p>
    	<c:forEach var="seria" items="${user_list}">        
          <div class = "comics">
        	<a href = "comicses.do?name=${seria.getSeries_id() }" >
              <img src="${seria.getPosterurl() }" class="img">
            </a>
            <div>
            	<form action = "profile.do?name=${seria.getSeries_id() }" method = "post">
	            	<input type="submit" name = "delete" class="btn btn-dark btn-sm" value="Убрать из списка">
	            </form>
          </div> 
          </div>
        </c:forEach>
        </div>
      </div>
    </div>
	
	<div style="height: 672px;	width: 100%;"></div>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>