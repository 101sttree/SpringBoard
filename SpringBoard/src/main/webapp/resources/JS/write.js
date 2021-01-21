function writerok()
	{
		
		if($("#title").val() == "")
		{
			alert("제목을 입력하세요");
			return;
		}
		if($("#btext").val() == "")
		{
			alert("내용을 입력하세요");
			return;
		}
		
		$("#writefrm").submit();
	}