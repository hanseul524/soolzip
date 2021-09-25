package recipe.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import recipe.model.vo.Recipe;
import recipe.model.vo.RecipeFile;
import recipe.model.vo.RecipeIngredient;

public class RecipeDAO {

	@SuppressWarnings("resource")
	public int insertRecipe(Connection conn, Recipe recipe) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		String querySeq = "SELECT SEQ_RECIPE.NEXTVAL AS SEQ_RECIPE FROM DUAL";
		String query = "INSERT INTO RECIPE VALUES(?,SEQ_USERNO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		try {
			pstmt= conn.prepareStatement(querySeq);
			rset = pstmt.executeQuery();
			int seqRecipe = Integer.MIN_VALUE;
			
			while(rset.next()) {
				seqRecipe = rset.getInt("SEQ_RECIPE");
			}
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, seqRecipe);
			pstmt.setString(2, recipe.getRecipeTitle());
			pstmt.setString(3, recipe.getRecipeContents());
			pstmt.setString(4, recipe.getRecipeMainDrink());
			pstmt.setInt(5, recipe.getRecipeAlcohol());
			pstmt.setString(6, recipe.getRecipeTag());
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

	public int insertRecipeFile(Connection conn, RecipeFile recipeFile, int result) {
		PreparedStatement pstmt = null;
		int result1 = 0;
		String query = "INSERT INTO recipe_file VALUES(SEQ_FILE.NEXTVAL,?,?,?,?,NULL,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, result);
			pstmt.setString(2, recipeFile.getFilePath());
			pstmt.setString(3, recipeFile.getFileName());
			pstmt.setLong(4,recipeFile.getFileSize());
			pstmt.setInt(5, recipeFile.getFileOrder());
			result1 = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result1;
	}
	

}
