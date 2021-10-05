package vote.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import vote.model.vo.RecipeCandidate;

public class VoteDAO {

	public List<RecipeCandidate> selectVoteRecipe(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecipeCandidate> cList = null;
		String query = "";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				//
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cList;
	}

}
