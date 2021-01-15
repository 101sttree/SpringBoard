<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

function login()
{
	if($("#id").val()=="")
		{
			alert("아이디를 입력하십시오");
			return;
		}
	if($("#pw").val()=="")
	{
		alert("비밀번호를 입력하십시오");
		return;
	}
	
	
	$.ajax
	({
		url: "/login",
		type: "post",
		data:
			{
				id:$("#id").val(),
				Pw:$("#pw").val()
			}	
		,
		dataType: "json",
		success:function(data) 
		{
			console.log(data);
			if(data.check != "success")
			{
				alert("로그인에 실패하였습니다.");
				return;
			}
			if(data.result == "idfail")
			{
				alert("존재하지 않는 아이디 입니다.");
				return;	
			}
			if(data.result == "pwfail")
			{
				alert("비밀번호가 틀렸습니다.");
				return;
			}
			if(data.result == "loginok")
			{
				alert("환영합니다.");
				location.href = "/";
			}
		}
		
		
	});
}
</script>
</head>
<body>
<form id="loginfrm" name="loginfrm">
	<table style="height: 40%; margin: auto; margin-top: 20%;">
		<tr>
			<td>아이디 :</td>
			<td><input type="text" name="id" id="id"></td>
			<td><input type="button" value="로그인" onclick="login()"></td>
		</tr>
		<tr>
			<td>비밀번호 :</td>
			<td><input type="password" name="pw" id="pw"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="/join">회원가입</a>
				<a href="/">메인으로</a>
			</td>
		</tr>
	</table>
</form>
	
</body>
</html>