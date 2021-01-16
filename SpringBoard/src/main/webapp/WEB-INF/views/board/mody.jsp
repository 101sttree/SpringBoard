<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글 상세 보기</title>
</head>
<body>
	<form action="/board/mody" method="post" name="frm">
		<input type="hidden" name="bno" value="${vo.bno }">
		<table style="margin: auto; margin-top: 15%;">
			<tr>
				<th>작성자</th>
				<td>${vo.writer}</td>
				<th>작성일</th>
				<td>${vo.bdate}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					<input type="text" placeholder="제목을 입력하세요" style="width: 98%;" name="title" value="${vo.title}">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="20" cols="50" placeholder="내용을 입력하세요" name="btext">${vo.btext}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<input type="submit" value="등록하기">
					<input type="button" value="취소" onclick="location.href='/board/detail?bno=${vo.bno}'">
					<input type="button" value="메인으로" onclick="location.href='/'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>