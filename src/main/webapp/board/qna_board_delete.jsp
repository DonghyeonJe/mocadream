<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	int nt_no=(Integer)request.getAttribute("nt_no");
    String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>공지 삭제확인</title>
<style>

	#passForm{
		width : 250px;
		margin:auto;
/* 		border : 1px solid orange; */
	}
	
	
</style>
</head>
<body>
<section id = "passForm">
<form name="deleteForm" action="boardDeletePro.mc?nt_no=<%=nt_no %>" method="post">
	<input type = "hidden" name = "page" value = "<%=nowPage %>">
	<table>
	
	<tr>
		<td>
			<input type="submit" value = "삭제하시겠습니까">&nbsp;&nbsp;
			<input type = "button" value = "돌아가기" onClick ="javascript:history.go(-1)">
		</td>
	</tr>
	</table>
</form>
</section>
</body>
</html>