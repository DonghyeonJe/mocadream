package dao;


import static util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Mc_notice;

public class MocaDAO {
   public static MocaDAO instance;
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
   DataSource ds;
   Statement st;
   
   public static MocaDAO getInstance(){
      if(instance == null){
         instance = new MocaDAO();
      }
      return instance;
   }
   public void setConnection(Connection con){
		this.con = con;
	}
 //글의 개수 구하기.
   public int selectListCount() {

		int listCount= 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try{


			System.out.println("getConnection");
			ps=con.prepareStatement("select count(*) from mc_notice");
			rs = ps.executeQuery();

			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(ps);
		}

		return listCount;

	}
 //글 목록 보기.	
 	public ArrayList<Mc_notice> selectArticleList(int page,int limit){

 		PreparedStatement ps = null;
 		ResultSet rs = null;
 		String board_list_sql="select * from (select rownum rnum, A.* from (select * from mc_notice order by nt_no desc ) A ) where rnum between ? and ?";
// 		String board_list_sql="select * from (select rownum rnum, A.* from (select * from board order by BOARD_RE_SEQ desc ,BOARD_NUM desc ) A ) where rnum between ? and ?";
 		ArrayList<Mc_notice> articleList = new ArrayList<Mc_notice>();
 		int startrow=(page-1)*10+1; //읽기 시작할 row 번호..	
 		int endrow=(page-1)*10+10;

 		try{
 			ps = con.prepareStatement(board_list_sql);
 			ps.setInt(1, startrow);
 			ps.setInt(2, endrow);
 			rs = ps.executeQuery();

 			while(rs.next()){
 				Mc_notice mc_notice = new Mc_notice();
 				mc_notice.setNt_no(rs.getInt("nt_no"));
 				mc_notice.setNt_title(rs.getString("nt_title"));
 				mc_notice.setNt_content(rs.getString("nt_content"));
 				mc_notice.setNt_writer(rs.getString("nt_writer"));
 				mc_notice.setNt_date(rs.getDate("nt_date"));
 				articleList.add(mc_notice);
 			}

 		}catch(Exception ex){
 			System.out.println("getBoardList 에러 : " + ex);
 		}finally{
 			close(rs);
 			close(ps);
 		}

 		return articleList;

 	}
 	
 	//글 내용 보기.
 		public Mc_notice selectArticle(int nt_no){

 			PreparedStatement ps = null;
 			ResultSet rs = null;
 			Mc_notice mc_notice = null;

 			try{
 				ps = con.prepareStatement( "select * from mc_notice where nt_no = ?");
 				ps.setInt(1, nt_no);
 				rs= ps.executeQuery();

 				if(rs.next()){
 					mc_notice = new Mc_notice();
 					mc_notice.setNt_no(rs.getInt("nt_no"));
 					mc_notice.setNt_title(rs.getString("nt_title"));
 					mc_notice.setNt_content(rs.getString("nt_content"));
 					mc_notice.setNt_writer(rs.getString("nt_writer"));
 					mc_notice.setNt_date(rs.getDate("nt_date"));
 				}
 			}catch(Exception ex){
 				System.out.println("getDetail 에러 : " + ex);
 			}finally{
 				close(rs);
 				close(ps);
 			}

 			return mc_notice;

 		}
 		//글 등록.
 		public int insertArticle(Mc_notice mc_notice){

 			PreparedStatement ps = null;
 			ResultSet rs = null;
 			int num =0;
 			String sql="";
 			int insertCount=0;

 			try{
 				ps=con.prepareStatement("select max(nt_no) from mc_notice");
 				rs = ps.executeQuery();

 				if(rs.next())
 					num =rs.getInt(1)+1;
 				else
 					num=1;

 				sql="insert into mc_notice (nt_no, nt_title, nt_content, nt_writer,";
 				sql+="nt_date) values(?,?,?,?,SYSDATE)";

 				ps = con.prepareStatement(sql);
 				ps.setInt(1, num);
 				ps.setString(2, mc_notice.getNt_title());
 				ps.setString(3, mc_notice.getNt_content());
 				ps.setString(4, mc_notice.getNt_writer());
 				insertCount=ps.executeUpdate();

 			}catch(Exception ex){
 				System.out.println("boardInsert 에러 : "+ex);
 			}finally{
 				close(rs);
 				close(ps);
 			}

 			return insertCount;

 		}
 		//글 수정.
 		public int updateArticle(Mc_notice mc_notice){

 			int updateCount = 0;
 			PreparedStatement ps = null;
 			String sql="update mc_notice set nt_title=?,nt_content=? where nt_no=?";

 			try{
 				ps = con.prepareStatement(sql);
 				ps.setString(1, mc_notice.getNt_title());
 				ps.setString(2, mc_notice.getNt_content());
 				ps.setInt(3, mc_notice.getNt_no());
 				updateCount = ps.executeUpdate();
 			}catch(Exception ex){
 				System.out.println("boardModify 에러 : " + ex);
 			}finally{
 				close(ps);
 			}

 			return updateCount;

 		}
 		//글 삭제.
 		public int deleteArticle(int nt_no){
 			//db에서 가장큰 nt_no를 가져옴, 받아온 nt_no랑 비교해서 같으면 그것만 삭제하는 로직
 			String select_sql = "select max(nt_no) from mc_notice";
 			String m_delete_sql="delete from mc_notice where nt_no=?";
 			
 			String board_delete_sql="delete from mc_notice where nt_no=?";
 			String update_sql = "UPDATE mc_notice \r\n"
 					+ "SET nt_no = nt_no - 1 \r\n"
 					+ "WHERE nt_no > ? AND nt_no <= (SELECT MAX(nt_no) FROM mc_notice)";
 			int deleteCount=0;
 			
 			try {
 				ps=con.prepareStatement(select_sql);
 				rs=ps.executeQuery();
 				if(rs.next())
 				if(nt_no == rs.getInt(1)){
 					try {
 						ps=con.prepareStatement(m_delete_sql);
 						ps.setInt(1, nt_no);
 						deleteCount=ps.executeUpdate();	
 					}catch(Exception e) {
 						e.printStackTrace();
 					}finally {
 						close(rs);
 						close(ps);
 					}
 					
 				}else {
 					try{
 						ps=con.prepareStatement(board_delete_sql);
 						ps.setInt(1, nt_no);
 						deleteCount=ps.executeUpdate();
 						
 						ps=con.prepareStatement(update_sql);
 						ps.setInt(1, nt_no);
 						deleteCount=ps.executeUpdate();
 					}catch(Exception ex){
 						System.out.println("boardDelete 에러 : "+ex);
 					}	finally{
 						close(ps);
 					}
 					
 				}
 		
 		}catch(Exception e) {
 			e.printStackTrace();
 		}finally {
 			close(rs);
 			close(ps);
 		}
 			return deleteCount;
 		}
 		//글쓴이인지 확인.
 		public boolean isArticleBoardWriter(int nt_no){

 			PreparedStatement ps = null;
 			ResultSet rs = null;
 			String board_sql="select * from mc_notice where nt_no=?";
 			boolean isWriter = false;

 			try{
 				ps=con.prepareStatement(board_sql);
 				ps.setInt(1, nt_no);
 				rs=ps.executeQuery();
 				rs.next();

 				if(nt_no == (rs.getInt("nt_no"))){
 					isWriter = true;
 				}
 			}catch(SQLException ex){
 				System.out.println("isBoardWriter 에러 : "+ex);
 			}
 			finally{
 				close(ps);
 			}

 			return isWriter;

 		}

}