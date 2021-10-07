package story.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import story.model.vo.Story;
import story.model.vo.StoryFile;
import story.model.vo.StoryReply;

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
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Story> sList = null;
		String sql="SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY STORY_NO DESC)AS NUM,STORY_NO,USER_ID, (SELECT COUNT(*) FROM STORY_LIKE L WHERE L.STORY_NO = S.STORY_NO) AS LIKE_CNT, (SELECT COUNT(*) FROM STORY_REPLY SR WHERE SR.STORY_NO = S.STORY_NO) AS REPLY_CNT,STORY_CONTENTS,STORYFILE_NAME,STORY_TAG,STORY_VIEWCOUNT FROM STORY S, STORY_FILE F WHERE S.FILE_NO = F.STORYFILE_NO) WHERE NUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(sql);
			int viewCountPage = 12; //페이지 당 보여줄 게시글 갯수
			int start = currentPage * viewCountPage - (viewCountPage -1);
			int  end = currentPage * viewCountPage;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			sList = new ArrayList<Story>();
			while(rset.next()) {
				Story story = new Story();
				story.setStoryNo(rset.getInt("STORY_NO"));
				story.setUserId(rset.getString("USER_ID"));
				story.setStoryContents(rset.getString("STORY_CONTENTS"));
				story.setFileName(rset.getString("STORYFILE_NAME"));
				story.setStoryTag(rset.getString("STORY_TAG"));
				story.setStoryReplyCount(rset.getInt("reply_cnt"));
				story.setStoryLikeCount(rset.getInt("like_cnt"));
				story.setStoryViewCount(rset.getInt("STORY_VIEWCOUNT"));
				sList.add(story);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return sList;
	}

	public String getPageNavi(Connection conn, int currentPage) {
		int pageCountPerView = 5;
		int viewTotalCount = totalCount(conn);
		int viewCountPage = 12;
		int pageTotalCount = 0;
		
		int pageTotalCountMod = viewTotalCount % viewCountPage; //
		if(pageTotalCountMod > 0) {
			pageTotalCount = viewTotalCount/viewCountPage+1;
		}else {
			pageTotalCount = viewTotalCount/viewCountPage;
		}
		int startNavi=((currentPage-1)/pageCountPerView)*pageCountPerView+1;
		int endNavi = startNavi + pageCountPerView - 1;
		
		//게시물이 있는 페이지까지만 보여줘야함
		if(endNavi >pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a href = '/story/list?currentPage="+(startNavi - 1)+"'>[이전]</a>");
		}
		for(int i = startNavi; i<=endNavi; i++) {
			if(i==currentPage) {
				sb.append(i);
			}else {
				sb.append("<a href='/story/list?currentPage=" + i + "'>" +" "+ i +" " + "</a>");
			}
		}
		if(needNext) {
			sb.append("<a href='/story/list?currentPage=" + (endNavi + 1) + "'>[다음]</a>");
		}
		return sb.toString();
	}

	private int totalCount(Connection conn) {
		int totalValue = 0;
		Statement stmt = null;
		ResultSet rset = null;
		String sql ="SELECT COUNT(*) AS TOTALCOUNT FROM STORY";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			if(rset.next()) {
				totalValue = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(stmt);
			JDBCTemplate.close(rset);
		}
		return totalValue;
	}
	//스토리 상세
	public Story selectOneStroy(Connection conn, int storyNo, String sessionId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql ="SELECT STORY_NO,(SELECT NVL(COUNT(USER_ID),0) FROM STORY_LIKE B WHERE B.STORY_NO = S.STORY_NO AND B.USER_ID=?)AS LIKE_CHECK, USER_ID,STORY_CONTENTS,FILE_NO,STORYFILE_NAME,STORY_TAG,STORY_VIEWCOUNT,STORY_ENROLLDATE,STORY_REPLYCOUNT,STORY_LIKE_COUNT FROM STORY S JOIN STORY_FILE F ON S.FILE_NO = F.STORYFILE_NO WHERE STORY_NO = ?";
		Story storyOne = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessionId);
			pstmt.setInt(2, storyNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				storyOne = new Story();
				storyOne.setStoryNo(rset.getInt("STORY_NO"));
				storyOne.setLikeCheck(rset.getInt("like_check"));
				storyOne.setUserId(rset.getString("USER_ID"));
				storyOne.setStoryContents(rset.getString("STORY_CONTENTS"));
				storyOne.setFileNo(rset.getString("File_No"));
				storyOne.setFileName(rset.getString("STORYFILE_NAME"));
				storyOne.setStoryTag(rset.getString("STORY_TAG"));
				storyOne.setStoryEnrollDate(rset.getDate("STORY_ENROLLDATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return storyOne;
	}
	//스토리 댓글 등록
	public int insertStoryReply(Connection conn, String userId, int storyNo, String replyContents,
			Timestamp uploadTime) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql="INSERT INTO STORY_REPLY VALUES(SEQ_STORY_REPLY.NEXTVAL,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storyNo);
			pstmt.setString(2, userId);
			pstmt.setString(3, replyContents);
			pstmt.setTimestamp(4, uploadTime);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//상세 댓글 리스트
	public List<StoryReply> selectAllStoryReply(Connection conn, int storyNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<StoryReply> List = null;
		StoryReply storyReply = null;
		String sql ="SELECT * FROM STORY_REPLY WHERE STORY_NO = ? ORDER BY 1 DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storyNo);
			rset = pstmt.executeQuery();
			List = new ArrayList();
			while(rset.next()) {
				storyReply = new StoryReply();
				storyReply.setReplyNo(rset.getInt("REPLY_NO"));
				storyReply.setStoryNo(rset.getInt("STORY_NO"));
				storyReply.setReplyUserId(rset.getString("REPLY_NICKNAME"));
				storyReply.setReplyContents(rset.getString("REPLY_CONTENTS"));
				storyReply.setReplyDate(rset.getTimestamp("REPLY_ENROLLDATE"));
				List.add(storyReply);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return List;
	}
	//스토리 댓글 삭제
	public int deleteStoryReplyOne(Connection conn, int replyNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql="DELETE FROM STORY_REPLY WHERE REPLY_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	//스토리 댓글 수정
	public int updateStoryReplyOne(Connection conn, int replyNo, String replyContents) {
		PreparedStatement pstmt = null;
		int result=0;
		String query="UPDATE STORY_REPLY SET REPLY_CONTENTS=? WHERE REPLY_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, replyContents);
			pstmt.setInt(2, replyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	// 스토리 삭제
	public int deleteStoryOne(Connection conn, int storyNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql="DELETE FROM STORY WHERE STORY_NO =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//좋아요 취소
	public int deleteStoryLike(Connection conn, int storyNo, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "DELETE FROM STORY_LIKE WHERE STORY_NO=? and user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storyNo);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//좋아요 등록
	public int insertRecipeLike(Connection conn, int storyNo, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into STORY_like values(?,?,SEQ_STORY_LIKE.NEXTVAL)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storyNo);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//스토리 조회수
	public int updateStoryViewCount(Connection conn, int storyNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update story set story_viewcount = story_viewcount+1 where story_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//스토리 수정
	public int updateStory(Connection conn, Story story) {
		PreparedStatement pstmt = null;
		int result = 0 ;
		String sql = "UPDATE STORY SET STORY_CONTENTS=?,STORY_TAG=? WHERE STORY_NO=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, story.getStoryContents());
			pstmt.setString(2, story.getStoryTag());
			pstmt.setInt(3, story.getStoryNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//스토리 파일 수정
	public int updateStoryFile(Connection conn, StoryFile storyFile) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql="UPDATE STORY_FILE SET STORYFILE_NAME=?,STORYFILE_PATH=?,STORYFILE_SIZE=? WHERE STORYFILE_NO=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storyFile.getFileName());
			pstmt.setString(2, storyFile.getFilePath());
			pstmt.setLong(3, storyFile.getFileSize());
			pstmt.setInt(4, storyFile.getFileNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
}
