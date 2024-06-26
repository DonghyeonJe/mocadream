package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardModifyProService;
import vo.ActionForward;
import vo.Mc_notice;

public class BoardModifyProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
//		System.out.println("chk2");
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int nt_no=Integer.parseInt(request.getParameter("nt_no"));
//		System.out.println("chk3");
		Mc_notice article=new Mc_notice();
//		System.out.println("chk4");
		BoardModifyProService boardModifyProService = new BoardModifyProService();
		boolean isRightUser=boardModifyProService.isArticleWriter(nt_no); //true
//		System.out.println("chk5");

		if(!isRightUser){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			article.setNt_no(nt_no);
			article.setNt_title(request.getParameter("nt_title"));
			article.setNt_content(request.getParameter("nt_content")); 
			isModifySuccess = boardModifyProService.modifyArticle(article); //true물고옴
//			System.out.println("chk6");

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("boardDetail.mc?getNt_no="+article.getNt_no()+"&page="+request.getParameter("page"));
//				System.out.println("chk7");
			}

		}

		return forward;
	}

}