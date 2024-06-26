<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style type="text/css">
#registForm {
	width: 500px;
	height: 610px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 450px;
}

.td_left {
	width: 150px;
	background: orange;
}

.td_right {
	width: 300px;
	background: skyblue;
}

#commandCell {
	text-align: center;
}
</style>
</head>
<body>
<!-- 	공지등록 -->

	<section id="writeForm">
		<h2>공지사항 글등록</h2>
		<form action="boardWritePro.mc" method="post" enctype="multipart/form-data" name="boardform">
			<table>
				<tr>
					<td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
					<td class="td_right"><input type="text" name="nt_writer" id="nt_writer" value="관리자" required readonly></td>
				</tr>

				<tr>
					<td class="td_left"><label for="nt_title">제 목</label></td>
					<td class="td_right"><input name="nt_title" type="text" id="nt_title" required></td>
				</tr>
				<tr>
					<td class="td_left"><label for="nt_content">내 용</label></td>
					<td><textarea id="nt_content" name="nt_content" cols="40" rows="15" required></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" >
			</section>
		</form>
	</section>
	
</body>
</html>