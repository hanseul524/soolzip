package vote.model.vo;

import java.sql.Date;

public class RecipeCandidate {
	private int candidateNo;
	private int recipeNo;
	private Date fixdDate;
	private int voteCount;
	private String fileName;
	private String recipeTitle;
	private String recipeUserId;
	
	public RecipeCandidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getRecipeTitle() {
		return recipeTitle;
	}

	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}




	public String getRecipeUserId() {
		return recipeUserId;
	}




	public void setRecipeUserId(String recipeUserId) {
		this.recipeUserId = recipeUserId;
	}




	public int getCandidateNo() {
		return candidateNo;
	}
	public void setCandidateNo(int candidateNo) {
		this.candidateNo = candidateNo;
	}
	public int getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}
	public Date getFixdDate() {
		return fixdDate;
	}
	public void setFixdDate(Date fixdDate) {
		this.fixdDate = fixdDate;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}




	@Override
	public String toString() {
		return "RecipeCandidate [candidateNo=" + candidateNo + ", recipeNo=" + recipeNo + ", fixdDate=" + fixdDate
				+ ", voteCount=" + voteCount + ", fileName=" + fileName + ", recipeTitle=" + recipeTitle
				+ ", recipeUserId=" + recipeUserId + "]";
	}


	
	
	
}
