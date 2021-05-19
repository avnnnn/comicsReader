<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	 <div class = "content">
	
	      <div class = "main_container">
	        <div>
	          <div class = "main_left main_blocks">
	            <img src="Images/welcome.png" class="welcome_img" alt="SDU Welcome!">
	          </div>
	
	          <div class = "main_right main_blocks">
	            <p class="container_name">Случайная серия комиксов</p>
	
	            <p class="random_img">
			      <a href = "comicses.do?name=${random.getSeries_id() }" >
		              <img src="${random.getPosterurl() }" class="img">
		              <div>
		              	${ random.getS_name() }
		              </div>
	              </a>
	            </p>
	          </div>
	        </div>
	        
	        <div class="main_down main_blocks">
	          <p class="container_name">Популярные комиксы сайта</p>
			  <c:forEach var="seria" items="${series}">
		          <div class = "comics">
				      <a href = "comicses.do?name=${seria.getSeries_id() }" >
			              <img src="${seria.getPosterurl() }" class="img">
			              <div>
			              	${ seria.getS_name() }
			              </div>
		            </a>
		          </div>
	          </c:forEach>
	          
	
	          
	        </div>
	      </div>
	    </div>



	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>