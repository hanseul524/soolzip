package user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.JDBCTemplate;
import user.model.vo.User;

public class UserDAO {

	public User selectLogin(Connection conn, String userId, String userPwd) {
		User user = null;
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String query = "SELECT * FROM USERS WHERE USER_ID = ? AND USER_PWD =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userId);
			pstmt.setString(2,userPwd);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				user = new User();
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPwd(rset.getString("USER_PWD"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return user;
	}
	//회원정보 수정
	public int updateUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE USERS SET USER_PWD = ?, USER_EMAIL =?, USER_PHONE=? WHERE USER_ID=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserPwd());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserPhone());
			pstmt.setString(4, user.getUserId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
