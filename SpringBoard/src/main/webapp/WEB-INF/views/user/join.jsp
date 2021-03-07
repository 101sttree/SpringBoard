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
<title>Insert title here</title>
<style type="text/css">
	div
	{
		text-align: center;
	}
	#no
	{
		width: 150px;
	}
	#no
	{
		color: red;
	}
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
	<form id="joinfrm" name="joinfrm" method="post" action="/joinok">
		<table style="height: 40%; margin: auto; margin-top: 13%; width: 400px; ">
			<tr>
				<td colspan="3">
					<div class="input-group mb-3 mt-3">
							<input type="text" class="form-control" placeholder="아이디" aria-label="Username" aria-describedby="basic-addon1" name="id" id="jid">
					</div>	
				</td>
			</tr>
			<tr>
				<td colspan="3" align="left">
					<!-- <input type="button" value="중복확인" onclick="idck()"> -->
					<button type="button" class="btn btn-info" onclick="idck()">중복확인</button>
				</td>
			</tr>
			<tbody id="idno">
			</tbody>
			<tr>
				<td colspan="2">
					<div class="input-group mb-3">
							<input type="password" class="form-control" placeholder="비밀번호" aria-label="Username" aria-describedby="basic-addon1" name="pw" id="jpw">
					</div>	
				</td>
				<td></td>
			</tr>
			<tbody id="pwno">
			</tbody>
			<tr>
				<td colspan="3">
					<div class="input-group mb-3">
							<input type="password" class="form-control" placeholder="비밀번호 확인" aria-label="Username" aria-describedby="basic-addon1" name="pwck" id="pwck">
					</div>	
				</td>
				<td></td>
			</tr>
			<tbody id="pwckno">
			</tbody>
			<tr>
				<td colspan="3">
					<div class="input-group mb-3">
							<input type="text" class="form-control" placeholder="이름" aria-label="Username" aria-describedby="basic-addon1" name="name" id="name">
					</div>	
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<!-- <input type="button" value="가입" onclick="join()">
					<input type="button" value="취소" onclick="location.href='/'"> -->
					<button type="button" class="btn btn-info" onclick="join()">가입</button>
					<button type="button" class="btn btn-info" onclick="location.href='/'">취소</button>
				</td>
			</tr>
		</table>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="/resources/JS/user.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>