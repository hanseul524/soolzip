package myPage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import myPage.dao.MyPageDAO;
import recipe.model.dao.RecipeDAO;
import recipe.model.vo.PageData;
import recipe.model.vo.Recipe;
import user.model.vo.User;

public class MyPageService {

	private JDBCTemplate jdbcTemplate;

	public MyPageService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}

	// 마이페이지 내가쓴 스토리 카운트 조회
	public int storyCount(String userId) {
		int count = 0;
		Connection conn = null;

		try {
			conn = jdbcTemplate.createConnection();
			count = new MyPageDAO().countStory(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return count;
	}

	// 마이페이지 내가쓴 공개레시피 갯수 조회
	public int recipeCount(String userId) {
		int count = 0;
		Connection conn = null;

		try {
			conn = jdbcTemplate.createConnection();
			count = new MyPageDAO().countRecipe(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return count;
	}

	// 마이페이지 입장시 유저vo에 유저 정보담기
	public User addUser(String userId) {
		User user = null;
		Connection conn = null;

		try {
			conn = jdbcTemplate.createConnection();
			user = new MyPageDAO().addUser(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return user;
	}

	// 회원정보 수정
	public int modifyUser(User user) {
		int result = 0;
		Connection conn = null;

		try {
			conn = jdbcTemplate.createConnection();
			result = new MyPageDAO().updateUser(conn, user);

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	// 마이페이지 내 레시피 조회
	public List<Recipe> myPagePrintAllRecipe(String userId) {
		Connection conn = null;
		MyPageDAO mDao = new MyPageDAO();
		List<Recipe> rList = null;
		try {
			conn = jdbcTemplate.createConnection();
			rList = mDao.myPageSelectAllRecipe(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return rList;
	}

}
