package recipe.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import recipe.model.dao.RecipeDAO;
import recipe.model.vo.Recipe;
import recipe.model.vo.RecipeFile;
import recipe.model.vo.RecipeIngredient;

public class RecipeService {

	private JDBCTemplate jdbcTemplate;
	
	public RecipeService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	public int registerRecipe(Recipe recipe, List<RecipeIngredient> ingredList, List<RecipeFile> rFileList) {
		int result = Integer.MIN_VALUE;
		Connection conn = null;
		RecipeDAO recipeDAO = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			//result는 등록된 레시피 번호
			result = recipeDAO.insertRecipe(conn,recipe);
			
			if(result > Integer.MIN_VALUE) {
				
				for(RecipeFile tmp : rFileList) {
					if(recipeDAO.insertRecipeFile(conn, tmp, result) <= 0) 
						throw new SQLException("error");
				}
					
				for(RecipeIngredient tmp : ingredList) {
					if(recipeDAO.insertRecipeIngred(conn, tmp ,result) <= 0) 
						throw new SQLException("error");
				}
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			JDBCTemplate.rollback(conn);
			//e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	

}
