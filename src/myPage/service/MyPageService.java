package myPage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import message.model.vo.Message;
import myPage.dao.MyPageDAO;
import recipe.model.dao.RecipeDAO;
import recipe.model.vo.PageData;
import recipe.model.vo.Recipe;
import recipe.model.vo.RecipeReply;
import story.model.vo.Story;
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

	// 마이페이지 내 공개 레시피 조회
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
	//마이페이지 내 임시 레시피 조회
	public List<Recipe> myCacheRecipe(String userId) {
		Connection conn = null;
		List<Recipe> cList = null;
		MyPageDAO mDao = new MyPageDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			cList = mDao.myCacheRecipe(conn,userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return cList;
	}
	//마이페이지 스토리 조회
	public List<Story> myStory(String userId) {
		Connection conn = null;
		List<Story> sList = null;
		MyPageDAO mDao = new MyPageDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			sList = mDao.myStory(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return sList;
	}
	//스크랩리스트
	public List<Recipe> myScrap(String userId) {
		Connection conn = null;
		List<Recipe> scList = null;
		MyPageDAO mDao = new MyPageDAO();
		
		try {
			conn=jdbcTemplate.createConnection();
			scList = mDao.myScrap(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return scList;
	}
	//내가쓴 댓글 레시피
	public List<RecipeReply> myRecipeReply(String userId) {
		Connection conn = null;
		List<RecipeReply> reList = null;
		MyPageDAO mDao = new MyPageDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			reList = mDao.myRecipeReply(conn,userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return reList;
	}
	//내가쓴 쪽지 리스트
	public List<Message> myMessageSendList(String userId) {
		Connection conn = null;
		List<Message> msList = null;
		MyPageDAO mDao = new MyPageDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			msList = mDao.myMessageSendList(conn,userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return msList;
	}
	//받은쪽지 리스트
	public List<Message> myMessageGetList(String userId) {
		Connection conn = null;
		List<Message> mgList = null;
		MyPageDAO mDao = new MyPageDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			mgList = mDao.myMessageGetList(conn,userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return mgList;
	
	}

	public List<Message> myGetMessageDetail(int msgNo) {
		Connection conn = null;
		List<Message> mdList = null;
		MyPageDAO mDao = new MyPageDAO();
		
		try {
			conn= jdbcTemplate.createConnection();
			mdList = mDao.myGetMessageDetail(conn,msgNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return mdList;
	}

	//메세지 삭제
	public int deleteMsg(int msgNo) {
		Connection conn = null;
		int result = 0;
		MyPageDAO mDao = new MyPageDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = mDao.deleteMsg(conn,msgNo);
			
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
	
	//마이페이지 받은쪽지 답장
	public int replyMessage(Message msg) {
		int result = 0;
		Connection conn = null;
		MyPageDAO mDao = new MyPageDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			result = mDao.replyMessage(conn,msg);
			
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

}
