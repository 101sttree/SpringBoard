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
<title>글 상세 보기</title>
<style type="text/css">
	a
	{
		padding: 3px;
	}
	.cta
	{
		border: none;
		height : 50px;
		background: #E6E6E6;
	}
	
	textarea
	{
		resize: none;
		width: 100%;
		height: 100%;
		background: white;
		border: 0px;
	}
	th
	{
		width: 100px;
		border-bottom: 1px solid black; 
	}
	td
	{
		border-bottom: 1px solid black;
	}
	div
	{
		text-align: center;
	}
	table 
	{
		margin: auto;
		margin-top: 5%;
		width: 900px;
	}
	img
	{
		width: 150px;
		height: 150px;
	}
	
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/resources/JS/detail.js"></script>
</head>
<body>
<%@ include file="../header.jsp" %>
	<form id="detailform" action="/anwritego" method="get">
		<input type="hidden" id="bno" 	 	name="bno" 		  value="${vo.bno}">
		<input type="hidden" id="uno" 	 	name="uno" 		  value="${user.uno}">
		<input type="hidden" id="cno" 	 	name="cno" 		  value="">
		<input type="hidden" id="id"  	 	name="id" 		  value="${user.id}">
		<input type="hidden" id="fname"  	name="fname" 	  value="${fvo.fname}">
		<input type="hidden" id="origino"	name="origino" 	  value="${vo.origino}" >
		<input type="hidden" id="groupord"	name="groupord"   value="${vo.groupord}" >
		<input type="hidden" id="grouplayer"name="grouplayer" value="${vo.grouplayer}" >
		<table id="tb">
			
			<tr>
				<th>작성자</th>
				<td>${vo.writer}</td>
				<th>작성일</th>
				<td>${vo.bdate}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					${vo.title}
				</td>
				<th>조회수</th>
				<td>
					${vo.hit}
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea rows="20" disabled="disabled" >${vo.btext}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					첨부파일
				</th>
				<td colspan="3" align="left">
					<a href="#" id="file">${fvo.fname}</a><br>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
				<c:if test="${vo.uno == user.uno}">
					<button type="button" class="btn btn-primary" onclick="location.href='/mody?bno=${vo.bno}'">수정하기</button>
				</c:if>
				<c:if test="${vo.uno == user.uno}">
					<button type="button" class="btn btn-danger" id="delete">삭제하기</button>
				</c:if>
					<button type="button" class="btn btn-primary" onclick="bloginCheck()">답글</button>
					<button type="button" class="btn btn-info" onclick="location.href='/'">메인으로</button>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea rows="7" 		placeholder="댓글을 입력하세요" 
					class="form-control" 	aria-label="With textarea"
					name="ctext" id="ctext"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<button type="button" class="btn btn-primary" onclick="commentwrite()">등록</button>
				</td>
			</tr>
			<tbody id="nocoment">
				<tr>
					<td></td>
					<td align="center" colspan="3">
						등록된 댓글이 없습니다.
					</td>
				</tr>
			</tbody>
		</table>
		<table style="margin: auto; width:900px;" id="comment">
		
		</table>
		<table style="margin: auto; width: 900px;">
		<tr>
			<td></td>
				<td colspan="3" align="center" id="commentpaginglist">
				
				</td>
			</tr>
		</table>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="/resources/JS/detail.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
</body>
</html>