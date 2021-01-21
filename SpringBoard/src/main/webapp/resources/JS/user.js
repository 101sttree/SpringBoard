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