let idcklet = "";


var idckr = /^[a-z]+[a-z0-9]{5,19}$/g;
var pwckr = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;




//회원가입 유효성 검사
function join()
{
	
	if($("#jid").val() == "")
	{
		alert("아이디를 입력하세요");
		return;
	}
	
	if($("#jpw").val() == "")
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

//아이디 중복 확인
function idck()
{
    
	if( !idckr.test( $("input[name=id]").val())) 
	{
	    alert("아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.");
	    return;
	}

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
				$('#pw').focus();
			}
        },
        error: function(xhr, status, error)
        {
            console.log(error);
        }
    });
}
$(document).ready(function()
{
	//비밀번호 정규식 검사
	$('#jpw').blur(function() 
	{
		if(!pwckr.test($("#pw").val()))
		{
			alert("비밀번호는 특수문자와 숫자를 포함한 8~15자리로 이루어져야 합니다.");
		}
		
	});

	//비밀번호 확인 검사 
	$('#pwck').blur(function() 
	{
		
		if($("#pwck").val() != $("#pw").val())
		{
			alert("비밀번호가 일치하지 않습니다.");
		}
		
	});
});

//로그인
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

//로그인 엔터 키 작동 함수
function pressEnter(){
    if(event.keyCode == 13){
    	login();
    }
}





