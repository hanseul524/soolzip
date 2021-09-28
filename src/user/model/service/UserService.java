package user.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import user.model.dao.UserDAO;
import user.model.vo.User;

public class UserService {

	private JDBCTemplate jdbcTemplate;
	
	public UserService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}

	public User selectLogin(String userId, String userPwd) {
		User user = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			user = new UserDAO().selectLogin(conn, userId, userPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return user;
	}
<<<<<<< HEAD

	public int registerUser(User user) {
=======
	//회원정보 수정
	public int modifyUser(User user) {
>>>>>>> origin/taeuk
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
<<<<<<< HEAD
			result = new UserDAO().insertUser(conn, user);
			
			if(result > 0) {
=======
			result = new UserDAO().updateUser(conn,user);
			
			if(result>0) {
>>>>>>> origin/taeuk
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
<<<<<<< HEAD

	public User findUserId(String userName, String userEmail) {
		Connection conn = null;
		User userOne = null;
		try {
			conn = jdbcTemplate.createConnection();
			userOne = new UserDAO().selectOneById(conn, userName, userEmail);
=======
	//마이페이지 입장시 유저vo에 유저 정보담기
	public User addUser(String userId) {
		User user = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			user = new UserDAO().addUser(conn,userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return user;
	}
	//마이페이지 내가쓴 공개레시피 갯수 조회
	public int recipeCount(String userId) {
		int count = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			count = new UserDAO().countRecipe(conn, userId);
>>>>>>> origin/taeuk
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
<<<<<<< HEAD
		return userOne;
	}

	public User findUserPwd(String userId, String userEmail) {
		Connection conn = null;
		User userOne = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			userOne = new UserDAO().selectOneByPwd(conn, userId, userEmail);
		} catch (SQLException e) {

=======
		return count;
	}
	//마이페이지 내가쓴 스토리 카운트 조회
	public int storyCount(String userId) {
		int count = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			count = new UserDAO().countStory(conn, userId);
		} catch (SQLException e) {
>>>>>>> origin/taeuk
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
<<<<<<< HEAD
		return userOne;
=======
		return count;
>>>>>>> origin/taeuk
	}
}
