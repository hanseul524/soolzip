package story.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import common.JDBCTemplate;
import recipe.model.dao.RecipeDAO;
import story.model.dao.StoryDAO;
import story.model.vo.PageData;
import story.model.vo.Story;
import story.model.vo.StoryFile;
import story.model.vo.StoryReply;

public class StoryService {
	private JDBCTemplate jdbcTemplate;
	
	public StoryService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	// 스토리 등록
	public int registerStory(Story story, StoryFile storyFile) {
		int result = Integer.MIN_VALUE;
		Connection conn = null;
		StoryDAO storyDAO = new StoryDAO();
		try {
			conn=jdbcTemplate.createConnection();
			if(story.getStoryFile().getFileName()!=null) {
				story.setFileNo(String.valueOf(storyDAO.insertFile(conn,story.getStoryFile())));
			}
			result = storyDAO.insertStory(conn,story); // 스토리 시퀀스 
			if(result > Integer.MIN_VALUE) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	//전체 리스트 조회
	public PageData printAllStory(int currentPage) {
		PageData page = new PageData();
		Connection conn = null;
		StoryDAO sDAO = new StoryDAO();
		try {
			conn=jdbcTemplate.createConnection();
			page.setStoryList(sDAO.selectAllStory(conn,currentPage));
			page.setPageNavi(sDAO.getPageNavi(conn,currentPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return page;
	}
	//상세 조회
	public Story pintOneStroy(int storyNo) {
		Story storyOne = null;
		Connection conn = null;
		List<StoryReply> list = null;
		StoryDAO sDAO = new StoryDAO();
		try {
			conn = jdbcTemplate.createConnection();
			storyOne = sDAO.selectOneStroy(conn, storyNo);
			//댓글 storyReply
			list = sDAO.selectAllStoryReply(conn, storyNo);
			storyOne.setReplies(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return storyOne;
	}
	//스토리 댓글 등록
	public int registerStoryReply(String userId, int storyNo, String replyContents, Timestamp uploadTime) {
		int result = 0;
		Connection conn = null;
		StoryDAO sDao = new StoryDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = sDao.insertStoryReply(conn,userId,storyNo,replyContents,uploadTime);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	//스토리 댓글 삭제
	public int removeStoryReplyOne(int replyNo) {
		int result = 0;
		Connection conn = null;
		StoryDAO sDao = new StoryDAO();
		try {
			conn=jdbcTemplate.createConnection();
			result = sDao.deleteStoryReplyOne(conn,replyNo);
			if(result>0) {
				JDBCTemplate.close(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	//스토리 댓글 수정
	public int modifyStoryReplyOne(int replyNo, String replyContents) {
		int result = 0;
		Connection conn = null;
		StoryDAO rDao = new StoryDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.updateStoryReplyOne(conn, replyNo, replyContents);
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
	//스토리 삭제
	public int removeStoryOne(int storyNo) {
		int result = 0;
		Connection conn = null;
		StoryDAO sDao = new StoryDAO();
		try {
			conn =jdbcTemplate.createConnection();
			result = sDao.deleteStoryOne(conn, storyNo);
			if(result > 0) {
				if(sDao.deleteStoryReplyOne(conn, storyNo)>0) {
					JDBCTemplate.commit(conn);
				}
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	//좋아요 취소
	public int removeStoryLike(int storyNo, String userId) {
		int result = 0;
		Connection conn =null;
		StoryDAO sDao = new StoryDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result= sDao.deleteStoryLike(conn,storyNo,userId);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	//좋아요 등록
	public int insertStoryLike(int storyNo, String userId) {
		int result = 0;
		Connection conn =null;
		StoryDAO rDao = new StoryDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.insertRecipeLike(conn, storyNo, userId);
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
	//조회수 1업
	public int StoryViewCount(int storyNo) {
		int result = 0;
		Connection conn = null;
		StoryDAO sDao = new StoryDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = sDao.updateStoryViewCount(conn,storyNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
}
