<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<title>메인화면</title>
</head>
<body>
<%@ include file="./header.jsp" %>
	<table style="width: 60%; height: 40%; margin: auto; margin-top: 5%;"
	class="table table-striped">
		<tr>
			<form action="/" method="get">
				<td>
					<select name="searchType" class="form-control">
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="btext">내용</option>
						<option value="tt">제목+내용</option>
					</select>
				</td>
				<td colspan="3">		
					<input type="text" class="form-control" placeholder="검색어를 입력하십시오" aria-label="Username" aria-describedby="basic-addon1" name="searchText">
				</td>
				<td>
					<button type="submit" class="btn btn-info">검색</button>
				</td>
			</form>
		</tr>

		<tr>
			<th>글번호</th>
			<th width="60%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="list" items="${list }" begin="0" end="${fn:length(list)}">
			<c:if test="${list.grouplayer < 4}">
			<tr>
				<th>${list.bno }</th>
				<c:choose>
					<c:when test="${list.grouplayer eq 0}">
						<td><a href="/board/detail?bno=${list.bno }">${list.title }</a></td>
					</c:when>
					<c:when test="${list.grouplayer eq 1}">
						<td>
							&nbsp;&nbsp;ㄴ
							<a href="/board/detail?bno=${list.bno }">${list.title }</a>
						</td>
					</c:when>
					<c:when test="${list.grouplayer eq 2}">
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ㄴ
							<a href="/board/detail?bno=${list.bno }">${list.title }</a>
						</td>
					</c:when>
					<c:when test="${list.grouplayer eq 3}">
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ㄴ
							<a href="/board/detail?bno=${list.bno }">${list.title }</a>
						</td>
					</c:when>
				</c:choose>
				
				<th>${list.writer }</th>
				<th>${list.bdate }</th>
				<th>${list.hit }</th>
			</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td align="right" colspan="5">
			<button type="button" class="btn btn-primary" onclick="loginCheck()">글쓰기</button>	
		</tr>
		<tr>
			<td align="center" colspan="5">
				<c:if test="${paging.startPage != 1}">
					<a href="/?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
				</c:if> 
				<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
					<c:choose>
						<c:when test="${p == paging.nowPage}">
							<b>${p}</b>
						</c:when>
						<c:when test="${p != paging.nowPage}">
							<a href="/?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
						</c:when>
					</c:choose>
				</c:forEach> 
				<c:if test="${paging.endPage != paging.lastPage}">
					<a href="/?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="5">
				<div class="btn-toolbar" role="toolbar"
					aria-label="Toolbar with button groups">
					<div class="btn-group m-auto" role="group" aria-label="First group">
						<c:if test="${paging.startPage != 1}">
							<button type="button" class="btn btn-secondary" onclick="location.href='/?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}'">&lt;</button>
						</c:if> 
						<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
							<c:choose>
								<c:when test="${p == paging.nowPage}">
									<button type="button" class="btn btn-secondary">${p}</button>
								</c:when>
								<c:when test="${p != paging.nowPage}">
									<button type="button" class="btn btn-secondary" onclick="location.href='/?nowPage=${p}&cntPerPage=${paging.cntPerPage}'">${p}</button>
								</c:when>
							</c:choose>
						</c:forEach> 
						<c:if test="${paging.endPage != paging.lastPage}">
							<button type="button" class="btn btn-secondary" onclick="location.href='/?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}'">&gt;</button>
						</c:if>
					</div>
				</div>
			</td>
		</tr>


	</table>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="/resources/JS/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>