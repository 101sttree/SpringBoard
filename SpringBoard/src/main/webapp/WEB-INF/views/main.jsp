<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>메인화면</title>
<style type="text/css">
	td, th
	{
		border-bottom: 1px solid black;
	}
</style>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<table style="width: 60%; height: 40%; margin: auto; margin-top: 15%;">
		<tr>
			<th colspan="5"><h1>스프링 게시판</h1></th>
		</tr>
		<c:if test="${user eq null}">
			<tr>
				<td colspan="5" align="right"><input type="button" value="로그인" onclick="location.href='/login'"></td>
			</tr>
		</c:if>
		<c:if test="${user ne null}">
			<tr>
				<td colspan="5" align="right"><input type="button" value="로그아웃" onclick="location.href='/login'"></td>
			</tr>
			<tr>
				<td colspan="5" align="left">${user.name }님 환영합니다.</td>
			</tr>
		</c:if>
		
		<tr>
			<td colspan="5" align="center">
				<select>
					<option>제목</option>
					<option>작성자</option>
					<option>내용</option>
					<option>제목+내용</option>
				</select>
				<input type="text" placeholder="검색어를 입력하십시오">
			</td>
		</tr>
		
		<tr>
			<th>글번호</th>
			<th width="50%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="list" items="${list }" begin="0" end="${fn:length(list)}">
			<tr>
				<th>${list.bno }</th>
				<th><a href="/board/detail?bno=${list.bno }">${list.title }</a></th>
				<th>${list.writer }</th>
				<th>${list.bdate }</th>
				<th>${list.hit }</th>
			</tr>
		</c:forEach>
		<tr>
			<td align="center" colspan="5">
				<a href="#">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<a href="#">5</a>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="5">
				<input type="button" value="글쓰기" onclick="location.href='/write'">
			</td>
		</tr>
	</table>
</body>
</html>