package story.model.vo;

import java.util.Date;
import java.util.List;

import recipe.model.vo.RecipeReply;

public class Story {
	private String userId;
	private int storyNo;
	private String storyContents;
	private String storyTag;
	private int storyReplyCount;
	private int storyLikeCount;
	private int storyViewCount;
	private Date storyEnrollDate;
	private String fileNo; //대표 사진 id
	private String fileName;
	private StoryFile storyFile;
	
	private List<StoryReply> replies; //댓글 리스트
	public Story() {
		super();
	}
	
	
	public Story(String storyContents, String storyTag) {
		super();
		this.storyContents = storyContents;
		this.storyTag = storyTag;
	}
	
	

	public Story(String userId, int storyNo, String storyContents, String storyTag, int storyReplyCount,
			int storyLikeCount, int storyViewCount, Date storyEnrollDate, String fileNo, String fileName,
			StoryFile storyFile) {
		super();
		this.userId = userId;
		this.storyNo = storyNo;
		this.storyContents = storyContents;
		this.storyTag = storyTag;
		this.storyReplyCount = storyReplyCount;
		this.storyLikeCount = storyLikeCount;
		this.storyViewCount = storyViewCount;
		this.storyEnrollDate = storyEnrollDate;
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.storyFile = storyFile;
	}


	

	public List<StoryReply> getReplies() {
		return replies;
	}


	public void setReplies(List<StoryReply> replies) {
		this.replies = replies;
	}


	public String getFileNo() {
		return fileNo;
	}


	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public StoryFile getStoryFile() {
		return storyFile;
	}


	public void setStoryFile(StoryFile storyFile) {
		this.storyFile = storyFile;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getStoryNo() {
		return storyNo;
	}
	public void setStoryNo(int storyNo) {
		this.storyNo = storyNo;
	}
	public String getStoryContents() {
		return storyContents;
	}
	public void setStoryContents(String storyContents) {
		this.storyContents = storyContents;
	}
	public String getStoryTag() {
		return storyTag;
	}
	public void setStoryTag(String storyTag) {
		this.storyTag = storyTag;
	}
	public int getStoryReplyCount() {
		return storyReplyCount;
	}
	public void setStoryReplyCount(int storyReplyCount) {
		this.storyReplyCount = storyReplyCount;
	}
	public int getStoryLikeCount() {
		return storyLikeCount;
	}
	public void setStoryLikeCount(int storyLikeCount) {
		this.storyLikeCount = storyLikeCount;
	}
	public Date getStoryEnrollDate() {
		return storyEnrollDate;
	}
	public void setStoryEnrollDate(Date storyEnrollDate) {
		this.storyEnrollDate = storyEnrollDate;
	}
	

	public int getStoryViewCount() {
		return storyViewCount;
	}


	public void setStoryViewCount(int storyViewCount) {
		this.storyViewCount = storyViewCount;
	}


	@Override
	public String toString() {
		return "Story [userId=" + userId + ", storyNo=" + storyNo + ", storyContents=" + storyContents + ", storyTag="
				+ storyTag + ", storyReplyCount=" + storyReplyCount + ", storyLikeCount=" + storyLikeCount
				+ ", storyViewCount=" + storyViewCount + ", storyEnrollDate=" + storyEnrollDate + ", fileNo=" + fileNo
				+ ", fileName=" + fileName + ", storyFile=" + storyFile + ", replies=" + replies + "]";
	}


	


	



	
	
	
}

