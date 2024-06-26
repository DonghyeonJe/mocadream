package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardDetailService;
import vo.ActionForward;
import vo.Mc_notice;

 public class BoardDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
//		System.out.println("chk8");
		int nt_no=Integer.parseInt(request.getParameter("nt_no"));
		String page = request.getParameter("page");
//		System.out.println("chk9");
		BoardDetailService boardDetailService = new BoardDetailService();
		Mc_notice article = boardDetailService.getArticle(nt_no);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
//	   	System.out.println("chk12");
   		forward.setPath("/board/qna_board_view.jsp");
//   		System.out.println("chk13");
   		return forward;

	 }
	 
}