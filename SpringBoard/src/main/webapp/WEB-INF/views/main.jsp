<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>메인화면</title>
<style type="text/css">
	td, th
	{
		border-bottom: 1px solid black;
	}
</style>
</head>
<body>
	<table style="width: 60%; height: 40%; margin: auto; margin-top: 15%;">
		<tr>
			<th colspan="5"><h1>스프링 게시판</h1></th>
		</tr>
		<tr>
			<td colspan="5" align="right"><input type="button" value="로그인" onclick="location.href='/login'"></td>
		</tr>
		<tr>
			<td colspan="5" align="center">
				<select>
					<option>제목</option>
					<option>작성자</option>
					<option>내용</option>
					<option>제목+내용</option>
				</select>
				<input type="text" placeholder="검색어를 입력하십시오">
			</td>
		</tr>
		<tr>
			<th>글번호</th>
			<th width="50%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<tr>
			<th>1</th>
			<th><a href="/board/detail">제목입니다.</a></th>
			<th>홍길동</th>
			<th>2021-01-12</th>
			<th>100</th>
		</tr>
		<tr>
			<th>1</th>
			<th>제목입니다.</th>
			<th>홍길동</th>
			<th>2021-01-12</th>
			<th>100</th>
		</tr>
		<tr>
			<th>1</th>
			<th>제목입니다.</th>
			<th>홍길동</th>
			<th>2021-01-12</th>
			<th>100</th>
		</tr>
		<tr>
			<td align="center" colspan="5">
				<a href="#">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<a href="#">5</a>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="5">
				<input type="button" value="글쓰기" onclick="location.href='/write'">
			</td>
		</tr>
	</table>
</body>
</html>