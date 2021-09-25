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
}
