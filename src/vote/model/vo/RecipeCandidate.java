package vote.model.vo;

import java.sql.Date;

public class RecipeCandidate {
	private int candidateNo;
	private int recipeNo;
	private Date fixdDate;
	private int voteCount;
	private String endState;
	
	
	public RecipeCandidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeCandidate(int candidateNo, int recipeNo, Date fixdDate, int voteCount, String endState) {
		super();
		this.candidateNo = candidateNo;
		this.recipeNo = recipeNo;
		this.fixdDate = fixdDate;
		this.voteCount = voteCount;
		this.endState = endState;
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
	public String getEndState() {
		return endState;
	}
	public void setEndState(String endState) {
		this.endState = endState;
	}

	@Override
	public String toString() {
		return "RecipeCandidate [candidateNo=" + candidateNo + ", recipeNo=" + recipeNo + ", fixdDate=" + fixdDate
				+ ", voteCount=" + voteCount + ", endState=" + endState + "]";
	}
	
}
