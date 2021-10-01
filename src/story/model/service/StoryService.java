package story.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import common.JDBCTemplate;
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
