package recipe.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import recipe.model.vo.Recipe;
import recipe.model.vo.RecipeFile;
import recipe.model.vo.RecipeIngredient;
import recipe.model.vo.RecipeMakeProcess;

public class RecipeDAO {

	public int insertRecipe(Connection conn, Recipe recipe) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		String querySeq = "SELECT SEQ_RECIPE.NEXTVAL AS SEQ_RECIPE FROM DUAL";
		String query = "INSERT INTO RECIPE VALUES(?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		try {
			pstmt= conn.prepareStatement(querySeq);
			rset = pstmt.executeQuery();
			int seqRecipe = Integer.MIN_VALUE;
			
			while(rset.next()) {
				seqRecipe = rset.getInt("SEQ_RECIPE");
			}
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, seqRecipe);
			pstmt.setString(2,recipe.getUserId());
			pstmt.setString(3, recipe.getRecipeTitle());
			pstmt.setString(4, recipe.getFileNo());
			pstmt.setString(5, recipe.getRecipeContents());
			pstmt.setString(6, recipe.getRecipeMainDrink());
			pstmt.setInt(7, recipe.getRecipeAlcohol());
			pstmt.setString(8, recipe.getRecipeTag());
			pstmt.executeUpdate();
			result = seqRecipe;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		 
		return result;
	}

	public int insertRecipeIngred(Connection conn, RecipeIngredient tmp, int result) {
		PreparedStatement pstmt= null;
		int result1= 0;
		String query = "INSERT INTO recipe_ingredient VALUES(SEQ_INGREDIENT.NEXTVAL,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, result);
			pstmt.setString(2, tmp.getIngredientName());
			pstmt.setString(3, tmp.getIngredientGram());
			result1 = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result1;
	}

	public int insertRecipeMakeProcess(Connection conn, RecipeMakeProcess tmp, int result) {
		PreparedStatement pstmt= null;
		int result1= 0;
		String query = "INSERT INTO recipe_make_process VALUES(SEQ_make.NEXTVAL,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, result);
			pstmt.setString(2, tmp.getFileNo());
			pstmt.setString(3, tmp.getMakeContents());
			result1 = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result1;
	}

	public int inserRecipeFile(Connection conn, RecipeFile tmp) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int result1= 0;
		String querySeq= "select seq_file.NEXTVAL as file_no from dual";
		String query = "INSERT INTO recipe_file VALUES(?,?,?,?, sysdate, ?)";
		try {
			pstmt= conn.prepareStatement(querySeq);
			rset = pstmt.executeQuery();
			int fileNo = Integer.MIN_VALUE;
			while(rset.next()) {
				fileNo = rset.getInt("file_no");
			}
			result1 = fileNo;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fileNo);
			pstmt.setString(2, tmp.getFilePath());
			pstmt.setString(3, tmp.getFileName());
			pstmt.setLong(4, tmp.getFileSize());
			pstmt.setString(5,tmp.getRegName());
			//pstmt.setString(5, 유저아이디넣어야함);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result1;
	}

	public List<Recipe> selectAllRecipe(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Recipe> rList = null;
		String query = "select user_id, recipe_title, F.file_name, recipe_contents, recipe_replycount, recipe_LikeCount\r\n"
				+ "from recipe r,recipe_file f\r\n"
				+ "where \r\n"
				+ "R.file_no = F.file_no and\r\n"
				+ "r.file_no is not null\r\n"
				+ "order by recipe_no";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rList = new ArrayList<Recipe>();
			while(rset.next()) {
				Recipe recipe= new Recipe();
				recipe.setUserId(rset.getString("USER_ID"));
				recipe.setRecipeTitle(rset.getString("RECIPE_TITLE"));
				recipe.setFileName(rset.getString(" F.file_name"));
				recipe.setRecipeContents(rset.getString("RECIPE_CONTENTS"));
				recipe.setRecipeReplyCount(rset.getInt("recipe_replycount"));
				recipe.setRecipeLikeCount(rset.getInt("recipe_LikeCount"));
				rList.add(recipe);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return rList;
	}

}
