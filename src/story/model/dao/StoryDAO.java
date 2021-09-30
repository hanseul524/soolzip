package story.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import story.model.vo.Story;
import story.model.vo.StoryFile;

public class StoryDAO {

	public int insertStory(Connection conn, Story story) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=0;
		String query ="SELECT SEQ_STORY.NEXTVAL AS SEQ_STORY FROM DUAL";
		String sql ="INSERT INTO STORY VALUES(?,?,?,DEFAULT,?,DEFAULT,DEFAULT,DEFAULT,?)";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			int seqStory =Integer.MIN_VALUE;
			
			while(rset.next()) {
				seqStory=rset.getInt("SEQ_STORY");
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,seqStory);
			pstmt.setString(2,story.getStoryContents()); //스토리 내용
			pstmt.setString(3,story.getStoryTag()); // 스토리 태그
			pstmt.setString(4,story.getUserId());
			pstmt.setString(5,story.getFileNo()); //첨부파일
			pstmt.executeUpdate();
			result=seqStory;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertFile(Connection conn, StoryFile storyFile) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query="SELECT SEQ_STORY_FILE.NEXTVAL AS FILE_NO from DUAL";
		String sql="INSERT INTO STORY_FILE VALUES(?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			int fileNo = Integer.MIN_VALUE;
			while(rset.next()) {
				fileNo=rset.getInt("FILE_NO");
			}
			result =fileNo;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fileNo);
			pstmt.setString(2,storyFile.getFilePath());
			pstmt.setString(3, storyFile.getFileName());
			pstmt.setLong(4, storyFile.getFileSize());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		return result;
	}

	public List<Story> selectAllStory(Connection conn, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPageNavi(Connection conn, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
