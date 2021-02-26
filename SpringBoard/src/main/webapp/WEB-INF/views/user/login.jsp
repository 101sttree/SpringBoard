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
</style>
</head>
<body>

<%@ include file="../header.jsp" %>
<form id="loginfrm" name="loginfrm">
	<table style="height: 40%; margin: auto; margin-top: 10%; width: 500px;" >
		<tr>
			<td colspan="3" align="center">
				<h1>Spring Board</h1>
			</td>
		</tr>
		<tr>
			<td colspan="3"><input type="text" class="form-control mb-3 mt-5" placeholder="아이디를 입력하십시오" aria-label="Username" aria-describedby="basic-addon1" name="id" id="id"></td>
		</tr>
		<tr>
			<td colspan="3"><input type="password" class="form-control mb-3" placeholder="비밀번호를 입력하십시오" aria-label="Username" aria-describedby="basic-addon1" name="pw" id="pw" onkeydown="pressEnter()"></td>
		</tr>
		<tr>
			<td colspan="3"><button class="btn btn-lg btn-primary btn-block mb-3" onclick="login()">로그인</button></td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<a href="/join">회원가입</a>
				<a href="/">메인으로</a>
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