<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.Mc_notice"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%Mc_notice article = (Mc_notice)request.getAttribute("article"); %>
<%
	ArrayList<Mc_notice> articleList=(ArrayList<Mc_notice>)request.getAttribute("articleList");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>공지사항</title>
<style type="text/css">
#registForm {
	width: 500px;
	height: 600px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 650px;
}

#tr_top {
	background: orange;
	text-align: center;
}

#pageList {
	margin: auto;
	width: 500px;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
}
</style>
</head>

<body>
	<!-- 게시판 리스트 -->

	<section id="listForm">
		<h2>
<%-- 		<%if (session.getAttribute("id") == "admin") {%> --%>
			글 목록<br><a href="boardWriteForm.mc">공지사항 글쓰기</a>
<%-- 		<%} else {%> --%>
<!-- 			글 목록 -->
<%-- 		<%} %> --%>
		</h2>
		<table>
<% if(articleList != null && listCount > 0){ %>
			<tr id="tr_top">
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
			</tr>
	<%for(int i=0;i<articleList.size();i++){ %>
			<tr>
				<td><%=articleList.get(i).getNt_no()%></td>
				<td>
						<a href="boardDetail.mc?nt_no=<%=articleList.get(i).getNt_no()%>&page=<%=nowPage%>"><%=articleList.get(i).getNt_title()%></a>
				</td>
				<td><%=articleList.get(i).getNt_writer() %></td>
				<td><%=articleList.get(i).getNt_date() %></td>

			</tr>
	<%} %>
		</table>
	</section>

	<section id="pageList">
		<%if(nowPage<=1){ %>
			[이전]&nbsp;
		<%}else{ %>
			<a href="boardList.mc?page=<%=nowPage-1 %>">[이전]</a>&nbsp;
		<%} %>

		<%for(int a=startPage;a<=endPage;a++){
				if(a==nowPage){%>
					[<%=a %>]
				<%}else{ %>
					<a href="boardList.mc?page=<%=a %>">[<%=a %>]</a>&nbsp;
				<%} %>
		<%} %>

		<%if(nowPage>=maxPage){ %>
			[다음]
		<%}else{ %>
			<a href="boardList.mc?page=<%=nowPage+1 %>">[다음]</a>
		<%} %>
	</section>
<% } else{ %>
	<section id="emptyArea">등록된 글이 없습니다.</section>
<% } %>

</body>
</html>