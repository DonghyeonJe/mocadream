package svc;

import static util.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.MocaDAO;
import vo.Mc_notice;

public class BoardListService {

	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		MocaDAO mocaDAO = MocaDAO.getInstance();
		mocaDAO.setConnection(con);
		listCount = mocaDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<Mc_notice> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<Mc_notice> articleList = null;
		Connection con = getConnection();
		MocaDAO mocaDAO = MocaDAO.getInstance();
		mocaDAO.setConnection(con);
		articleList = mocaDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}
