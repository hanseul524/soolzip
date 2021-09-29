package story.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import story.model.dao.StoryDAO;
import story.model.vo.Story;
import story.model.vo.StoryFile;

public class StoryService {
	private JDBCTemplate jdbcTemplate;
	
	public StoryService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	
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
}
