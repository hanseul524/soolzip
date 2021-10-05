package vote.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import vote.model.vo.RecipeCandidate;

public class VoteDAO {

	public List<RecipeCandidate> selectVoteRecipe(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecipeCandidate> cList = null;
		String query = "select * from recipe_candidate a left outer join (select* from recipe c left outer join recipe_file d on c.file_no = d.file_no where recipe_savestate = 1 ) b on a.recipe_no = b.recipe_no";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			cList = new ArrayList<RecipeCandidate>();
			while(rset.next()) {
				RecipeCandidate recipeCandidate = new RecipeCandidate();
				recipeCandidate.setCandidateNo(rset.getInt("candidate_no"));
				recipeCandidate.setRecipeNo(rset.getInt("recipe_no"));
				recipeCandidate.setFixdDate(rset.getDate("Fixed_Date"));
				recipeCandidate.setVoteCount(rset.getInt("vote_count"));
				recipeCandidate.setFileName(rset.getString("file_name"));
				recipeCandidate.setRecipeTitle(rset.getString("recipe_title"));
				recipeCandidate.setRecipeUserId(rset.getString("user_id"));
				cList.add(recipeCandidate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return cList;
	}

}
