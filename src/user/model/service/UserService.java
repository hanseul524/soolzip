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
	//회원정보 수정
	public int modifyUser(User user) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new UserDAO().updateUser(conn,user);
			
			if(result>0) {
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
}
