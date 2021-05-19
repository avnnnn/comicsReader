<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class = "content">
        <div class = "left_main_container"> 
            <div class = "left_container">
            	<p class="text-center">Издаетли</p>
            	<c:forEach var="publisher" items="${publishers}">
	                <div class = "container">
						<a class="right_comics_name" href = "series.do?name=${publisher.getPublisher_id()}">${publisher.getP_name()}</a>
	                </div>
	                
                </c:forEach>
            </div>
        </div>

        <div class = "main_right main_blocks main_container right">
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


	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>