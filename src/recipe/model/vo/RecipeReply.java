package recipe.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class RecipeReply {
	private int replyNo;
	private int recipeNo;
	private String replyContents;
	private String replyUserId;
	private Timestamp replyDate;
	
	
	
	public RecipeReply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecipeReply(int replyNo, int recipeNo, String replyContents, String replyUserId, Timestamp replyDate) {
		super();
		this.replyNo = replyNo;
		this.recipeNo = recipeNo;
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
	public int getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
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
	@Override
	public String toString() {
		return "RecipeReply [replyNo=" + replyNo + ", recipeNo=" + recipeNo + ", replyContents=" + replyContents
				+ ", replyUserId=" + replyUserId + ", replyDate=" + replyDate + "]";
	}
	
	
	
}
