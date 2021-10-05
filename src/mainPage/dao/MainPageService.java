package mainPage.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import mainPage.service.MainPageDAO;
import recipe.model.vo.Recipe;

public class MainPageService {

	private JDBCTemplate jdbcTemplate;

	public MainPageService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}

	public List<Recipe> regendRecipe() {
		Connection conn = null;
		List<Recipe> rList = null;
		MainPageDAO mDao = new MainPageDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			rList = mDao.regendLecipe(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return rList;
	}
	
}
