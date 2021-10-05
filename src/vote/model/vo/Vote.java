package vote.model.vo;

import java.sql.Date;

public class Vote {
	private int voteNo; // 투표번호
	private String userId; // 회원 아이디
	private int candidateNo; // 후보 번호
	private Date voteDate; // 투표 날짜
	private String voteState; // 투표 상태
	
	
	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Vote(int voteNo, String userId, int candidateNo, Date voteDate, String voteState) {
		super();
		this.voteNo = voteNo;
		this.userId = userId;
		this.candidateNo = candidateNo;
		this.voteDate = voteDate;
		this.voteState = voteState;
	}
	
	
	public int getVoteNo() {
		return voteNo;
	}
	public void setVoteNo(int voteNo) {
		this.voteNo = voteNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserNo(String userId) {
		this.userId = userId;
	}
	public int getCandidateNo() {
		return candidateNo;
	}
	public void setCandidateNo(int candidateNo) {
		this.candidateNo = candidateNo;
	}
	public Date getVoteDate() {
		return voteDate;
	}
	public void setVoteDate(Date voteDate) {
		this.voteDate = voteDate;
	}
	public String getVoteState() {
		return voteState;
	}
	public void setVoteState(String voteState) {
		this.voteState = voteState;
	}

	@Override
	public String toString() {
		return "Vote [voteNo=" + voteNo + ", userNo=" + userId + ", candidateNo=" + candidateNo + ", voteDate="
				+ voteDate + ", voteState=" + voteState + "]";
	}
	
	
}
