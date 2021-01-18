<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
let idcklet = "";

	function join()
	{
		
		if($("#id").val() == "")
		{
			alert("아이디를 입력하세요");
			return;
		}
		if($("#pw").val() == "")
		{
			alert("비밀번호를 입력하세요");
			return;
		}
		if($("#pwck").val() == "")
		{
			alert("비밀번호 확인을 입력하십시오.");
			return;
		}
		if($("#pwck").val() != $("#pw").val())
		{
			alert("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		if($("#name").val() == "")
		{
			alert("이름을 입력하세요");
			return;
		}
		if(idcklet == "")
		{
			alert("아이디 중복확인을 해주십시오.");
			return;
		}	
		
		$("#joinfrm").submit();
	}
	

	function idck()
	{
	    $.ajax
	    ({
	        url : "/idck",
	        type  : "post",
	        data  : { id : $("#id").val() },
	        dataType : "json",
	        success: function(data)
	        {
	            console.log(data);
				if($("#id").val() == "")
				{
					alert("아이디를 입력하세요");
					return;
				}
				if(data.result == "no")
				{
					alert("중복된 아이디 입니다.");
				}
				if(data.result == "ok")
				{
					alert("사용 가능한 아이디입니다.");
					idcklet = "ok";
				}
	        },
	        error: function(xhr, status, error)
	        {
	            console.log(error);
	        }
	    });
	}

</script>
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