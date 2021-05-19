<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="style.css">

<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	
        <div class = "comics_reader">
			<div class = "comics_nav text-center">
				<a class = "btn btn-dark btn-sm" href="comicses.do?name=${comicses.get(0).getSeries_id() }">Назад к серии</a> 
        	
        		
        	</div>
        	<div class = "comics_nav text-center">
	        	<c:if test="${!(isFirst && isLast) }">	
	       	        <fmt:parseNumber var = "id" type = "number" value ="${name }" />
	        	
					<c:if test="${!isFirst}">
						<a class = "btn btn-dark btn-sm" href="comics.do?name=${id-1 }"><<</a> 
					</c:if>
					<div class="dropdown">
					  <button class="dropbtn">Выбрать главу</button>
					  <div class="dropdown-content">
					  <c:forEach var="comics" items="${comicses }">
				
						  <a href="comics.do?name=${comics.getComics_id() }">${comics.getC_name() }</a>
					  </c:forEach>
					  </div>
					</div>
					<c:if test="${!isLast}">
						<a class = "btn btn-dark btn-sm" href="comics.do?name=${id+1 }">>></a> 
					</c:if>
			</c:if>
			</div>
			
			<c:forEach var="page" items="${pages}">
			
            <img src="${page.getUrl() }" class="main_comics">
           	</c:forEach>
            <div class = "comics_nav text-center">
	        	<c:if test="${!(isFirst && isLast) }">	
	       	        <fmt:parseNumber var = "id" type = "number" value ="${name }" />
	        	
					<c:if test="${!isFirst}">
						<a class = "btn btn-dark btn-sm" href="comics.do?name=${id-1 }"><<</a> 
					</c:if>
					<div class="dropup">
					  <button class="dropbtn">Выбрать главу</button>
					  <div class="dropup-content">
					  <c:forEach var="comics" items="${comicses }">
				
						  <a href="comics.do?name=${comics.getComics_id() }">${comics.getC_name() }</a>
					  </c:forEach>
					  </div>
					</div>
					<c:if test="${!isLast}">
						<a class = "btn btn-dark btn-sm" href="comics.do?name=${id+1 }">>></a> 
					</c:if>
			</c:if>
			</div>
        </div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>