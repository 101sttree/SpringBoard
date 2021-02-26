<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">Spring Board</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <c:if test="${user eq null}">
			<button type="button" class="btn btn-outline-success mt-3 mb-3" onclick="location.href='/login'">로그인</button>
		</c:if>
      </li>
      <li class="nav-item active">
        <c:if test="${user eq null}">
			<button type="button" class="btn btn-outline-success ml-3 mt-3 mb-3" onclick="location.href='/join'">회원가입</button>
		</c:if>
      </li>
       <li class="nav-item active">
        <c:if test="${user ne null}">			
			<h5><p class="badge badge-primary text-wrap mt-4" style="width: 6rem;">${id}</p>님환영합니다.</h5>
		</c:if>
      </li>
      <li class="nav-item active">
        <c:if test="${user ne null}">
			<button type="button" class="btn btn-outline-danger ml-3 mt-3" id="logout">로그아웃</button>			
		</c:if>
      </li>
     
    </ul>
    
  </div>
</nav>
