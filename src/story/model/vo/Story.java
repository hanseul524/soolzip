package story.model.vo;

import java.util.Date;

public class Story {
	private String userId;
	private int storyNo;
	private String storyContents;
	private String storyTag;
	private int storyReplyCount;
	private int storyLikeCount;
	private Date storyEnrollDate;
	private String fileNo; //대표 사진 id
	private String fileName;
	private StoryFile storyFile;
	public Story() {
		super();
	}
	
	
	public Story(String storyContents, String storyTag) {
		super();
		this.storyContents = storyContents;
		this.storyTag = storyTag;
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
	@Override
	public String toString() {
		return "Story [userId=" + userId + ", storyNo=" + storyNo + ", storyContents=" + storyContents + ", storyTag="
				+ storyTag + ", storyReplyCount=" + storyReplyCount + ", storyLikeCount=" + storyLikeCount
				+ ", storyEnrollDate=" + storyEnrollDate + "]";
	}
	
	
}

