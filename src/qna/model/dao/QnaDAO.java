package qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import qna.model.vo.Qna;
import recipe.model.vo.Recipe;

public class QnaDAO {

	public List<Qna> selectQna(String userId , Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Qna> qList = null;

		String query="SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY QNA_NO) AS NUM, QNA_NO, USER_ID, QNA_TITLE,"
				+ "QNA_CONTENT, QNA_STATUS,reply_content , QNA_WRITEDATE FROM QNA)"
				+ " WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
//			 WHERE NUM BETWEEN ? AND ?
			pstmt.setString(1, userId);
//			pstmt.setInt(2, 10);
			rset = pstmt.executeQuery();
			qList = new ArrayList<Qna>();
			
			while(rset.next()) {
				Qna qna = new Qna();
				qna.setReplyContent(rset.getString("reply_content"));
				qna.setQnaNo(rset.getInt("QNA_NO"));
				qna.setUserId(rset.getString("USER_ID"));
				qna.setQnaTitle(rset.getString("QNA_TITLE"));
				qna.setQnaContent(rset.getString("QNA_CONTENT"));
				qna.setQnaStatus(rset.getString("QNA_STATUS"));
				qna.setQnaWriteDate(rset.getDate("QNA_WRITEDATE"));
				qList.add(qna);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return qList;
	}

	public int insertQna(Connection conn, Qna qna) {
		PreparedStatement pstmt = null;
		int result =0;
		String query = "INSERT INTO QNA VALUES(SEQ_QNA.NEXTVAL,?,?,?,'N',DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qna.getUserId());
			pstmt.setString(2, qna.getQnaTitle());
			pstmt.setString(3, qna.getQnaContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Qna selectQnaDetail(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna qnaOne = null;
		String query = "SELECT * FROM QNA WHERE QNA_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				qnaOne = new Qna();
				qnaOne.setQnaNo(rset.getInt("QNA_NO"));
				qnaOne.setQnaTitle(rset.getString("QNA_TITLE"));
				qnaOne.setQnaContent(rset.getString("QNA_CONTENT"));
				qnaOne.setReplyTitle(rset.getString("REPLY_TITLE"));
				qnaOne.setQnaContent(rset.getString("REPLY_CONTENT"));
				qnaOne.setReplyWriteDate(rset.getDate("REPLY_WRITEDATE"));
				qnaOne.setReplyUserName(rset.getString("REPLY_USERNAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		return qnaOne;
	}
	// 관리자 페이지 문의사항 전체 조회
	public List<Qna> selectAllQna(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Qna> qList = null;
		String query = "SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY QNA_NO ASC) AS NUM,"
				+ "QNA_NO, QNA_TITLE, QNA_CONTENT, USER_ID, QNA_WRITEDATE, QNA_STATUS,"
				+ "REPLY_CONTENT, REPLY_WRITEDATE, REPLY_USERNAME FROM QNA)"
				+ "WHERE NUM BETWEEN ? AND ? ORDER BY QNA_STATUS ASC";
		
		try {
			pstmt = conn.prepareStatement(query);
			int viewCountPerPage = 10;
			int start = currentPage*viewCountPerPage-(viewCountPerPage-1);
			int end = currentPage*viewCountPerPage;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery(); //rset에 쿼리문 값 저장
			qList = new ArrayList<Qna>();
			while(rset.next()) {
				Qna qna = new Qna();
				qna.setQnaNo(rset.getInt("QNA_NO"));
				qna.setQnaTitle(rset.getString("QNA_TITLE"));
				qna.setQnaContent(rset.getString("QNA_CONTENT"));
				qna.setUserId(rset.getString("USER_ID"));
				qna.setQnaWriteDate(rset.getDate("QNA_WRITEDATE"));
				qna.setQnaStatus(rset.getString("QNA_STATUS"));
				qna.setReplyContent(rset.getString("REPLY_CONTENT"));
				qna.setReplyWriteDate(rset.getDate("REPLY_WRITEDATE"));
				qna.setReplyUserName(rset.getString("REPLY_USERNAME"));
				qList.add(qna);
			}
			System.out.println(qList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return qList;
	}
	// 문의사항 페이징 처리
	public String getPageNavi(Connection conn, int currentPage) {
		int pageCountPerView = 5;
		int viewTotalCount = totalCount(conn);
		int viewCountPerPage = 10;
		int pageTotalCount = 0;
		int pageTotalCountMod = viewTotalCount%viewCountPerPage;
		
		if(pageTotalCountMod > 0) {
			pageTotalCount = viewTotalCount/viewCountPerPage+1;
		}else {
			pageTotalCount = viewTotalCount/viewCountPerPage;
		}
		
		int startNavi = ((currentPage-1)/pageCountPerView)*pageCountPerView+1;
		int endNavi = startNavi+pageCountPerView-1;
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a href='/admin/qnalist?currentPage=" + (startNavi-1) + "'> [이전] </a>");
		}
		
		for(int i=startNavi; i<=endNavi; i++) {
			if(i == currentPage) {
				sb.append(i + " ");
			}else {
				sb.append("<a href='/admin/qnalist?currentPage=" + i +"'>" + i + "</a>");
			}
		}
		if(needNext) {
			sb.append("<a href='/notice/list?currentPage=" + (endNavi+1) + "'> [다음] </a>");
		}
 		return sb.toString();
	}
	
	// 전체 문의사항 갯수 세줄 메소드
	private int totalCount(Connection conn) {
		int totalValue = 0;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM QNA";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				totalValue = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return totalValue;
	}
	// 관리자 문의사항 댓글등록
	public int updateReply(Connection conn, int qnaNo, String replyContent, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE QNA SET QNA_STATUS = 'Y', REPLY_CONTENT = ?, REPLY_USERNAME = ? WHERE QNA_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, replyContent);
			pstmt.setString(2, userId);
			pstmt.setInt(3, qnaNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}


