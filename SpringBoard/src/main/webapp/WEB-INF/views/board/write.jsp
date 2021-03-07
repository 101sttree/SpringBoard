<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>        
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
<title>글 작성</title>
<style type="text/css">
	img
	{
		width: 150px;
		height: 150px;
	}
	table 
	{
		margin: auto;
		margin-top: 2%;
		width: 1000px;
	}
	textarea
	{
		resize: none;
		width: 98%;
	}
	div
	{
		text-align: center;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/resources/JS/write.js"></script>
</head>
<body>
<%@ include file="../header.jsp" %>
	<form 			action="/board/write" method="post" 	name="writefrm" id="writefrm"
					enctype="multipart/form-data" >
		<input 		type="hidden" value="${user.uno }" 		name="uno">
		<input 		type="hidden" value="${id }" 			name="writer">
		<c:if   	test="${vo ne null}">
			<input 	type="hidden" value="${vo.bno}" 		name="bno">
			<input 	type="hidden" value="${vo.origino}" 	name="origino">
			<input 	type="hidden" value="${vo.groupord}" 	name="groupord">
			<input 	type="hidden" value="${vo.grouplayer}" 	name="grouplayer">
		</c:if>
		<table>
			<tr>
				<td colspan="3">
					<input 	type="text" 					class="form-control" 
							placeholder="제목을 입력하세요" 		aria-label="Username" 
							aria-describedby="basic-addon1" name="title" id="title" 
					>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<textarea 	class="form-control" 		aria-label="With textarea"
								placeholder="내용을 입력하세요" 	name="btext" id="btext"
								rows="20" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input class="btn btn-primary" type="file" name="file" id="file" style="display: none;"><br>
					<label class="btn btn-primary" for="file">파일 업로드</label>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<img id="img">
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right">
					<button type="button" class="btn btn-primary" onclick="writerok()">등록</button>
					<button type="button" class="btn btn-danger" onclick="location.href='/'">취소</button>
				</td>
			</tr>
		</table>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="/resources/JS/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>