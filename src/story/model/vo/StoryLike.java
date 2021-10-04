package story.model.vo;

public class StoryLike {
	private int likeNo;
	private int storyNo;
	private String userId;
	
	public StoryLike() {
		super();
	}

	public StoryLike(int likeNo, int storyNo, String userId) {
		super();
		this.likeNo = likeNo;
		this.storyNo = storyNo;
		this.userId = userId;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public int getStoryNo() {
		return storyNo;
	}

	public void setStoryNo(int storyNo) {
		this.storyNo = storyNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "StoryLike [likeNo=" + likeNo + ", storyNo=" + storyNo + ", userId=" + userId + "]";
	}
	
	
}
