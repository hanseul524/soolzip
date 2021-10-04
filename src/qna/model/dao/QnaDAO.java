package qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		String query="SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY QNA_NO) AS NUM, QNA_NO, USER_ID, QNA_TITLE, QNA_CONTENT, QNA_STATUS, QNA_WRITEDATE FROM QNA)"
				+ "   WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
//			 WHERE NUM BETWEEN ? AND ?
			pstmt.setString(1, userId);
//			pstmt.setInt(2, 10);
			rset = pstmt.executeQuery();
			qList = new ArrayList<Qna>();
			
			while(rset.next()) {
				Qna qna = new Qna();
				qna.setQnaNo(rset.getInt("QNA_NO"));
				qna.setUserId(rset.getString("USER_ID"));
				qna.setQnaTitle(rset.getString("QNA_TITLE"));
				qna.setQnaContent(rset.getString("QNA_CONTENT"));
				qna.setQnaStatus(rset.getString("QNA_STATUS"));
				qna.setQnaWriteDate(rset.getDate("QNA_WRITEDATE"));

				qList.add(qna);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}


