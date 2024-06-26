package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardListService;
import vo.ActionForward;
import vo.Mc_notice;
import vo.PageInfo;

 public class BoardListAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<Mc_notice> articleList=new ArrayList<Mc_notice>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")==null || request.getParameter("page").equals("")) page = 1;
		else page=Integer.parseInt(request.getParameter("page"));
		
		BoardListService boardListService = new BoardListService();
		int listCount=boardListService.getListCount(); //총 리스트 수를 받아옴.
		articleList = boardListService.getArticleList(page,limit); //리스트를 받아옴.
		//총 페이지 수.
   		int maxPage=(int)((double)listCount/limit+0.95);//1.35 //0.95를 더해서 올림 처리.
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage; //1.35

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage); //1.35
   		pageInfo.setListCount(listCount); //4
		pageInfo.setMaxPage(maxPage); //1.35
		pageInfo.setPage(page); //1
		pageInfo.setStartPage(startPage); //1	
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList); //4줄데이타
		
		ActionForward forward= new ActionForward();
   		forward.setPath("/board/qna_board_list.jsp");
   		return forward;
   		
	 }
	 
 }