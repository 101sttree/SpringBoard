<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글 상세 보기</title>
<style type="text/css">
	a
	{
		padding: 3px;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	
	var nowPage = 1;	
	//화면 로딩시 댓글 목록 불러오기
	$(document).ready(function()
	{
		commentlist();
		commentpaginglist();
	});
	
	//댓글 쓰기 로그인 여부 체크
	function loginCheck()
	{
		$.ajax
	    ({
	        url		 : "/loginCheck",
	        dataType : "json",
	        success: function(data)
	        {
	            console.log(data);
				
				if(data.check == "loginno")
				{
					alert("로그인이 필요한 서비스 입니다.");
					location.href = "/login"
				}
	        },
	        error: function(xhr, status, error) 
	        {
	            console.log(error);
	        }
	    });
	}
	
	
	//댓글 불러오기
	function commentlist()
	{
	    $.ajax
	    ({
	        url   : "/commentlist",
	        type  : "get",
	        data  : 
					{
						bno : $("#bno").val(),
						nowPage : nowPage
					},
			datetype : "json",
	        success: function(data)
	        {
				if(data.check == "yes")
				{
					$("#nocoment").html("");

					$.each(data.list, function(index, item)
					{
						let str =	"";
							str += '<tr>';
							str += '<td></td>';
							str += '<td align="left" colspan="3" style="border-top: 1px solid black;">'+item.cwriter+'</td>';
							str += '</tr>';
							str += '<tr>';
							str += '<td></td>';
							str += '<td align="left" colspan="3">';
							str += item.ctext;
							str += '</td>';
							str += '</tr>';
							str += '<tr>';
							str += '<td></td>';
							str += '<td align="right" colspan="3" style="border-bottom: 1px solid black;">';
							str += item.cdate;
							str += '</td>';
							str += '</tr>';
						
						$("#comment").append(str);	
					});
				}
	        },
	        error: function(xhr, status, error)
	        {
	            console.log(error);
	        }
	    });
	}

	
	//댓글 작성 및 불러오기
	function commentwrite()
	{
	    loginCheck();
	
		$.ajax
	    ({
	        url   : "/commentwrite",
	        type  : "post",
	        data  : 
			{ 
				bno		: $("#bno").val(),
				uno		: $("#uno").val(),
				cwriter : $("#id").val(),
				ctext 	: $("#ctext").val() 
			},
	        success: function(data)
	        {
	            
	        },
	        error: function(xhr, status, error)
	        {
	            console.log(error);
	        }
	    });
		$("#comment").html("");
		$("#commentpaginglist").html("");
		commentlist();
		commentpaginglist();
	}
	
	//댓글 페이징 (<, > 미구현)
	function commentpaginglist()
	{
	    $.ajax
	    ({
	        url   : "/commentlist",
	        type  : "get",
	        data  : 
					{
						bno : $("#bno").val(),
						nowPage : nowPage
					},
			datetype : "json",
	        success: function(data)
	        {
				console.log(data);
				let paging = data.paging;
				for(var i = paging.startPage ; i <= paging.endPage ; i++)
				{
				 	if(paging.nowPage == i)
					{
						$("#commentpaginglist").append("<a>"+i+"</a>"); 
					}
					if(paging.nowPage != i)
					{
						$("#commentpaginglist").append("<a href='#' id='goPage' page='"+i+"'>"+i+"</a>"); 
					}
				}
				
	        },
	        error: function(xhr, status, error)
	        {
	            console.log(error);
	        }
	    });
	}	 
	
	$(document).on("click","#goPage",function()
			{
				nowPage = $(this).attr("page");
				$("#comment").html("");
				$("#commentpaginglist").html("");
				commentlist();
				commentpaginglist();
			});
	
	







	
	
</script>
</head>
<body>
	<form>
		<input type="hidden" id="bno" name="bno" 	value="${vo.bno}">
		<input type="hidden" id="uno" name="uno" 	value="${user.uno}">
		<input type="hidden" id="id"  name="id" 	value="${user.id}">
		<table style="margin: auto; margin-top: 10%;" id="tb">
			<tr>
				<th>작성자</th>
				<td>${vo.writer}</td>
				<th>작성일</th>
				<td>${vo.bdate}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					${vo.title}
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="20" cols="50" placeholder="내용을 입력하세요" readonly="readonly">${vo.btext}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
				<c:if test="${vo.uno == user.uno}">
					<input type="button" value="수정하기" onclick="location.href='/mody?bno=${vo.bno}'">
				</c:if>
				<c:if test="${vo.uno == user.uno}">
					<input type="button" value="삭제하기" onclick="location.href='/board/delete?bno=${vo.bno}'">
				</c:if>
					<input type="button" value="메인으로" onclick="location.href='/'">
				</td>
			</tr>
			<tr>
				<td>댓글</td>
				<td colspan="3">
					<textarea rows="7" cols="50" placeholder="댓글을 입력하세요" name="ctext" id="ctext"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="등록" onclick="commentwrite()">
				</td>
			</tr>
			<tbody id="nocoment">
				<tr>
					<td></td>
					<td align="center" colspan="3">
						등록된 댓글이 없습니다.
					</td>
				</tr>
			</tbody>
			<tbody id="comment">
				
			</tbody>
			<tr>
				<td colspan="4" align="center" id="commentpaginglist">
				
				</td>
			</tr>
		</table>
	</form>
</body>
</html>