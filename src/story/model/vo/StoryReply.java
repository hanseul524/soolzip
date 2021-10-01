package story.model.vo;

import java.sql.Timestamp;

public class StoryReply {
	private int replyNo;
	private int storyNo;
	private String replyContents;
	private String replyUserId;
	private Timestamp replyDate; //Timestamp 상세 날짜
	private String StoryContents;
	
	public StoryReply() {
		super();
	}
	
	public StoryReply(int replyNo, int storyNo, String replyContents, String replyUserId, Timestamp replyDate) {
		super();
		this.replyNo = replyNo;
		this.storyNo = storyNo;
		this.replyContents = replyContents;
		this.replyUserId = replyUserId;
		this.replyDate = replyDate;
	}

	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getStoryNo() {
		return storyNo;
	}
	public void setStoryNo(int storyNo) {
		this.storyNo = storyNo;
	}
	public String getReplyContents() {
		return replyContents;
	}
	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}
	public String getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	public Timestamp getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
	public String getStoryContents() {
		return StoryContents;
	}
	public void setStoryContents(String storyContents) {
		StoryContents = storyContents;
	}
	
	
}

