package topqna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import qna.model.vo.Qna;
import topqna.model.vo.TopQna;

public class TopQnaDAO {

	public List<TopQna> selectTopQna(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<TopQna> tqList = null;

		String query="SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY TOPQNA_NO DESC) AS NUM, TOPQNA_NO, TOPQNA_TITLE, TOPQNA_CONTENT, TOPQNA_WRITEDATE FROM TOPQNA)";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			tqList = new ArrayList<TopQna>();
			
			while(rset.next()) {
				TopQna topqna = new TopQna();
				topqna.setTopQnaNo(rset.getInt("TOPQNA_NO"));
				topqna.setTopQnaTitle(rset.getString("TOPQNA_TITLE"));
				topqna.setTopQnaContent(rset.getString("TOPQNA_CONTENT"));
				topqna.setTopQnaWriteDate(rset.getDate("TOPQNA_WRITEDATE"));
				
				tqList.add(topqna);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return tqList;
	}

	public int updateTopQna(Connection conn, TopQna topQna) {
		PreparedStatement pstmt = null;
		int result =0;
		String query ="UPDATE TOPQNA SET TOPQNA_TITLE = ?, TOPQNA_CONTENT =? WHERE TOPQNA_NO=?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, topQna.getTopQnaTitle());
			pstmt.setString(2, topQna.getTopQnaContent());
			pstmt.setInt(3, topQna.getTopQnaNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		

		
		return result;
	}
}
