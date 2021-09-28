package user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	//마이페이지 입장시 유저vo에 유저 정보담기
	public User addUser(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		String query = "SELECT USER_NO, USER_ID, USER_PWD, USER_EMAIL, USER_PHONE FROM USERS WHERE USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				user = new User();
				user.setUserNo(rset.getInt("USER_NO"));
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPwd(rset.getString("USER_PWD"));
				user.setUserEmail(rset.getString("USER_EMAIL"));
				user.setUserPhone(rset.getString("USER_PHONE"));
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return user;
	}
	//마이페이지 내가쓴 공개 레시피 갯수
	public int countRecipe(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rset=null;
		String query = "SELECT COUNT(*) FROM RECIPE WHERE USER_ID=? AND RECIPE_SAVESTATE='1'";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	//마이페이지 내가쓴 스토리 갯수
	public int countStory(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String query = "SELECT COUNT(*) FROM STORY WHERE USER_NO = (SELECT USER_NO FROM USERS WHERE USER_ID = ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count=rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	public int insertUser(Connection conn, User user) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO USERS VALUES(SEQ_USER_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserName());
			pstmt.setString(5, user.getUserPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public User selectOneById(Connection conn, String userName, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User userOne = null;
		String query = "SELECT USER_ID FROM USERS WHERE USER_NAME = ? AND USER_EMAIL = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				userOne = new User();
				userOne.setUserId(rset.getString("USER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return userOne;
	}

	public User selectOneByPwd(Connection conn, String userId, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		return null;
	}
}
