package user.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import user.model.dao.UserDAO;
import user.model.vo.PageData;
import user.model.vo.User;

public class UserService {

	private JDBCTemplate jdbcTemplate;

	public UserService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	//로그인
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
	//회원가입
	public int registerUser(User user) {
		int result = 0;
		Connection conn = null;

		try {
			conn = jdbcTemplate.createConnection();
			result = new UserDAO().insertUser(conn, user);

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
	//아이디 찾기
	public User findUserId(String userName, String userEmail) {
		Connection conn = null;
		User userOne = null;
		try {
			conn = jdbcTemplate.createConnection();
			userOne = new UserDAO().selectOneById(conn, userName, userEmail);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return userOne;
	}
	//비밀번호 찾기
	public User findUserPwd(String userId, String userEmail) {
		Connection conn = null;
		User userOne = null;

		try {
			conn = jdbcTemplate.createConnection();
			userOne = new UserDAO().selectOneByPwd(conn, userId, userEmail);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return userOne;
	}	
	
	//비밀번호 변경
	public int changePwd(String userId, String authenticationKey) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new UserDAO().updateUserPwd(userId, authenticationKey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	//회원 리스트 보기
	public PageData printAllUser(int currentPage) {
		PageData pd = new PageData();
		Connection conn = null;
		UserDAO uDAO = new UserDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			List<User> uList = uDAO.selectAllUser(conn, currentPage);
			String pageNavi = uDAO.getPageNavi(conn, currentPage);
			pd.setUserList(uDAO.selectAllUser(conn, currentPage));
			pd.setPageNavi(uDAO.getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	//관리자 리스트 보기
	public PageData printAllAdmin(int currentPage) {
		PageData pd = new PageData();
		Connection conn = null;
		UserDAO uDAO = new UserDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			List<User> aList = uDAO.selectAllAdmin(conn, currentPage);
			String apageNavi = uDAO.getAPageNavi(conn, currentPage);
			pd.setAdminList(aList);
			pd.setApageNavi(apageNavi);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	//회원삭제
	public int removeUser(String users) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new UserDAO().deleteUser(conn, users);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	//관리자 삭제
	public int removeAdmin(String admins) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new UserDAO().deleteAdmin(conn, admins);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	public List<User> searchUser(String searchUser) {
		Connection conn = null;
		List<User> uList = null;
		try {
			conn = jdbcTemplate.createConnection();
			uList = new UserDAO().selectSearchUser(conn, searchUser);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return uList;
	}
}
