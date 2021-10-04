package mainPage.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import recipe.model.vo.Recipe;

public class MainPageDAO {

	public List<Recipe> regendLecipe(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Recipe> rList = null;
		String query = "select recipe_no, user_id, recipe_title, F.file_name,recipe_enrollDate, (select count(*) from recipe_reply where recipe_no=r.recipe_no)as RECIPE_REPLYCOUNT, recipe_LikeCount, recipe_legendstate from recipe R,recipe_file F where  R.file_no = F.file_no and r.file_no is not null and recipe_savestate='1' and RECIPE_LEGENDSTATE='1' order by recipe_no";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rList = new ArrayList<Recipe>();
			while(rset.next()) {
				Recipe r = new Recipe();
				r.setRecipeNo(rset.getInt("RECIPE_NO"));
				r.setUserId(rset.getString("USER_ID"));
				r.setRecipeTitle(rset.getString("RECIPE_TITLE"));
				r.setFileName(rset.getString("FILE_NAME"));
				r.setRecipeEnrollDate(rset.getDate("RECIPE_ENROLLDATE"));
				r.setRecipeReplyCount(rset.getInt("RECIPE_REPLYCOUNT"));
				r.setRecipeLikeCount(rset.getInt("RECIPE_LIKECOUNT"));
				r.setRecipeLegendState(rset.getInt("RECIPE_LEGENDSTATE"));
				rList.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return rList;
	}

}
