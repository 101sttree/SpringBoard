<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�� �� ����</title>
</head>
<body>
	<form>
		<table style="margin: auto; margin-top: 15%;">
			<tr>
				<th>�ۼ���</th>
				<td>ȫ�浿</td>
				<th>�ۼ���</th>
				<td>2020-01-12</td>
			</tr>
			<tr>
				<th>����</th>
				<td colspan="3">
					�����Դϴ�.
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td colspan="3">
					<textarea rows="20" cols="50" placeholder="������ �Է��ϼ���" readonly="readonly">�����Դϴ�.</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="�����ϱ�" onclick="location.href='/board/mody'">
					<input type="button" value="��������" onclick="location.href='/'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>