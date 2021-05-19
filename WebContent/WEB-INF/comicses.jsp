<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <div class = "description_container">
                    <h4>${seria.getS_name()}</h4>
                    <h5>${eria.getOriginal_name()}</h5>
                    <p>Издательство: <a href = "series.do?name=${publisherId }" class = "content_name_type">${seria.getPublisher_name()}</a></p>
                    <p>Год выпуска: ${seria.getYear()}</p>
                   	<c:if test="${user != null }">
                   		<c:if test="${inSeria == true }">
			            	<form action = "comicses.do?name=${seria.getSeries_id() }" method = "post">
				            	<input type="submit" name = "delete" class="btn btn-dark btn-sm" value="Убрать из списка">
				            </form>
                            <p><a class = "btn btn-dark btn-sm" href = "comics.do?name=${last_chapter.getComics_id() }">Продолжит читать с <br />
              																							                 ${last_chapter.getOriginal_name() }</a></p>
                   		</c:if>
      		            <c:if test="${inSeria == false}">
							<form action = "comicses.do?name=${seria.getSeries_id()}" method = "post">
									<input name = "seria" class="btn btn-dark btn-sm" type="submit" value="Добавить в избранное">
							</form>	
						</c:if>															
					</c:if>					
                </div>
                
                <div class="description_image">
                    <img src="${seria.getPosterurl() }">
                    <p><a class = "btn btn-dark btn-sm" href = "comics.do?name=${comicses.get(0).getComics_id() }">Читать с первой главы</a></p>

                </div>
                
				<div style="	height: 450px;
				width: 100%;	"></div>
               	<c:forEach var="comics" items="${comicses}">

	                <div class = "list_comics_container">
	                    <div class = "left_comics">
						<a href = "comics.do?name=${comics.getComics_id() }" >
	                            <img src="${comics.getPosterurl() }" class="left_comics_image">
	                        </a>
	                    </div>
	                    <div class = "right_comics">
	                        <a class = "right_comics_name" href = "comics.do?name=${comics.getComics_id() }">${comics.getC_name() }</a>
	                        <div class = "right_comics_description">${comics.getOriginal_name()}</div>
	                    </div>	
	                </div>
				</c:forEach>
				
				
	          
		                
            </div>       
        </div>

        
        <div class = "main_right main_blocks main_container right">
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