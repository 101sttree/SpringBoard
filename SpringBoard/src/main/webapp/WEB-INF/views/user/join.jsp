<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/resources/JS/user.js"></script>
</head>
<body>
	<form id="joinfrm" name="joinfrm" method="post" action="/joinok">
		<table style="height: 40%; margin: auto; margin-top: 20%;">
			<tr>
				<td>아이디 :</td>
				<td><input type="text" name="id" id="id"></td>
				<td><input type="button" value="중복확인" onclick="idck()"></td>
			</tr>
			<tr>
				<td>비밀번호 :</td>
				<td colspan="2"><input type="password" name="pw" id="pw"></td>
			</tr>
			<tr>
				<td>비밀번호확인 :</td>
				<td colspan="2"><input type="password" name="pwck" id="pwck"></td>
			</tr>
			<tr>
				<td>이름 :</td>
				<td colspan="2"><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="button" value="가입" onclick="join()">
					<input type="button" value="취소" onclick="location.href='/'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>