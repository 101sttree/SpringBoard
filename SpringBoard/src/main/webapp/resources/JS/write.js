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

$(document).ready(function()
{
	$("#file").on("change", handleImgFileSelect);
});

function handleImgFileSelect(e)
{
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f)
	{
		if(!f.type.match("image.*"))
		{
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}
		
		sel_file = f;
		
		var reader = new FileReader();
		reader.onload = function(e)
		{
			$("#img").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
		
	});
	
}





