<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Комикс</title>
  <link rel="shortcut icon" href="http://unicomics.ru/img/favicon.ico">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 
</head>

<header>
    <nav class="navbar navbar-expand-lg navbar-light header_color">
      <a class="navbar-brand logo" href="home.do">
        <div class="white">
          SDU COMICS
        </div>
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link menu" href="publisher.do">
              <div class="white">
                ИЗДАТЕЛЬСТВА <span class="sr-only">(current)</span>
              </div>
            </a>
          </li>
          
          <li class="nav-item active">
              <a class="nav-link menu" href="series.do">
                <div class="white">
                  СЕРИИ <span class="sr-only">(current)</span>
                </div>
              </a>
          </li>
		  <c:if test = "${user == null}">

	          <li class="nav-item active">
	            <a class="nav-link menu" href="login.do">
	              <div class="white">
	                ВХОД <span class="sr-only">(current)</span>
	              </div>
	            </a>
	          </li>
	          <li class="nav-item active">
	            <a class="nav-link menu" href="register.do">
	              <div class="white">
	                РЕГИСТРАЦИЯ <span class="sr-only">(current)</span>
	              </div>
	            </a>
	          </li>
		 </c:if>
         <c:if test="${user != null }">
		 
	          <li class="nav-item active">
	            <a class="nav-link menu" href="profile.do">
	              <div class="white">
	                ПРОФИЛЬ <span class="sr-only">(current)</span>
	              </div>
	            </a>
	          </li>
	          <li class="nav-item active">
	            <a class="nav-link menu" href="logout.do">
	              <div class="white">
	                ВЫЙТИ <span class="sr-only">(current)</span>
	              </div>
	            </a>
	          </li>          
          </c:if>
        </ul>
        <form action="series.do" method="post" class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" name = "searching_seria_name" placeholder="Название серии" aria-label="Search">
          <input  class="btn btn-outline-success my-2 my-sm-0" type="submit" value ="Поиск">
        </form>
        
      </div>
    </nav>
</header>
</html>