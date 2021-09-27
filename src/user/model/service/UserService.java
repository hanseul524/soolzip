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

	public int registerUser(User user) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new UserDAO().insertUser(conn, user);
			
			if(result > 0) {
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

	public User findUserId(String userName, String userEmail) {
		User user = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			user = new UserDAO().selectOneById(conn, userName, userEmail);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return user;
	}
}
