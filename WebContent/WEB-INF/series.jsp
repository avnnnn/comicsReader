<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${hz }</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

    <div class = "content">
        <div class = "left_main_container"> 
            <div class = "left_container">
                <nav aria-label="Page navigation example" class = "pagination_margin">
                    <ul class="pagination justify-content-center">
					<c:forEach var="p" items="${pages}">
					  <li class="page-item">
						<a class="page-link" href="series.do?
							<%
								if(request.getParameter("name")!=null){
									out.print("name="+request.getParameter("name")+"&");
								}
							%>
							page=${p}">
							${p}
						</a>
					  </li>
					</c:forEach>		
                    </ul>
              		<p class = "text-center">Читать комиксы <a href="series.do?name=${publisher.getPublisher_id() }" class = "right_comics_name">${publisher.getP_name() }</a> онлайн</p>				    
                </nav>
                
				<c:forEach var="seria" items="${series}">

	                <div class = "list_comics_container">
	                    <div class = "left_comics">
	                        <a href = "comicses.do?name=${seria.getSeries_id() }">
	                            <img src="${seria.getPosterurl() }" class="left_comics_image">
	                        </a>
	                    </div>
	                    <div class = "right_comics">
	                        <a class = "right_comics_name" href = "comicses.do?name=${seria.getSeries_id() }">${seria.getS_name() }</a>
	                        <div class = "right_comics_description">${seria.getOriginal_name()}</div>
	                    </div>	
	                </div>
				</c:forEach>
                    <nav aria-label="Page navigation example" class = "pagination_margin">
                    <ul class="pagination justify-content-center">
                     
					<c:forEach var="p" items="${pages}">
					  <li class="page-item">
						<a class="page-link" href="series.do?
							<%
								if(request.getParameter("name")!=null){
									out.print("name="+request.getParameter("name")+"&");
								}
							%>
							page=${p}">
							${p}
						</a>
					  </li>
					</c:forEach>		

                      
                    </ul>
                </nav>
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