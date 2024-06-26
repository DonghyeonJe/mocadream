<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="vo.Mc_notice"%>
<%
Mc_notice article = (Mc_notice)request.getAttribute("article");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
#articleForm {
	width: 500px;
	height: 500px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

#basicInfoArea {
	position: relative;
	height: 40px;
	text-align: center;
}

#articleContentArea {
	background: orange;
	margin-top: 20px;
	height: 350px;
	text-align: center;
	overflow: auto;
}

#commandList {
	margin: auto;
	width: 500px;
	text-align: center;
}

/* #imgDiv { */
/* 	display: none; */
/* 	position: absolute; */
/* 	top:0; */
/* 	left: 20px; */
/* 	width: 90%; */
/* 	height: 250px; */
/* 	border: 1px solid #ccc; */
/* 	border-radius: 8px; */
/* 	overflow : hidden; */
/* 	padding: 0px; */
/* 	background-size: cover; */
/* 	background-repeat : no-repeat; */

/* } */
</style>
<script>
// function showDiv(){
// 	document.getElementById("imgDiv").style.display = "block";
// }

// function hideDive(){
// 	document.getElementById("imgDiv").style.display = "none";
// }
</script>
</head>

<body>
	<!-- 게시판 수정 -->
	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<section id="basicInfoArea">
			제 목 :
			<%=article.getNt_title()%>
			
		</section>
		<section id="articleContentArea"><%=article.getNt_content() %></section>
	</section>
	<section id="commandList">
<%-- 		<%if (session.getAttribute("id") == "admin") {%> --%>
			<a href="boardModifyForm.mc?nt_no=<%=article.getNt_no() %>&page=${page }"> [수정] </a>
			<a href="boardDeleteForm.mc?nt_no=<%=article.getNt_no() %>&page=${page }"> [삭제] </a>
			<a href="boardList.mc?page=${page }"> [목록] </a>&nbsp;&nbsp;
<%-- 		<%} else {%> --%>
<%-- 			<a href="boardList.mc?page=${page }"> [목록] </a>&nbsp;&nbsp; --%>
<%-- 		<%} %> --%>
	</section>
</body>
</html>