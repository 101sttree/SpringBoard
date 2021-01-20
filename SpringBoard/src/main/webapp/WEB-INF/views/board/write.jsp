<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>
	<form 		action="/board/write" method="post" name="frm"
				enctype="multipart/form-data">
		<input 	type="hidden" value="${user.uno }" 	name="uno">
		<input 	type="hidden" value="${id }" 		name="writer">
		<table 	style="margin: auto; margin-top: 15%;">
			<tr>
				<th>작성자</th>
				<td>${id }</td>
				<th>작성일</th>
				<td>2020-01-12</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
					<input type="text" placeholder="제목을 입력하세요" style="width: 98%;" name="title">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="20" cols="50" placeholder="내용을 입력하세요" name="btext"></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<input type="file" name="file">
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<input type="submit" value="등록">
					<input type="button" value="취소" onclick="location.href='/'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>