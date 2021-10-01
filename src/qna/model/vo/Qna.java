package qna.model.vo;

import java.sql.Date;

public class Qna {
	private int qnaNo;
	private String userId;
	private String qnaTitle;
	private String qnaContent;
	private String qnaStatus;
	private Date qnaWriteDate;
	private Date replyWriteDate;
	private String replyTitle;
	private String replyContent;
	private String replyUserName;
	
	public Qna() {}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	
	public String getQnaStatus() {
		return qnaStatus;
	}

	public void setQnaStatus(String qnaStatus) {
		this.qnaStatus = qnaStatus;
	}

	public Date getQnaWriteDate() {
		return qnaWriteDate;
	}

	public void setQnaWriteDate(Date qnaWriteDate) {
		this.qnaWriteDate = qnaWriteDate;
	}

	public Date getReplyWriteDate() {
		return replyWriteDate;
	}

	public void setReplyWriteDate(Date replyWriteDate) {
		this.replyWriteDate = replyWriteDate;
	}

	public String getReplyTitle() {
		return replyTitle;
	}

	public void setReplyTitle(String replyTitle) {
		this.replyTitle = replyTitle;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public Qna(int qnaNo, Date replyWriteDate, String replyTitle, String replyContent, String replyUserName) {
		super();
		this.qnaNo = qnaNo;
		this.replyWriteDate = replyWriteDate;
		this.replyTitle = replyTitle;
		this.replyContent = replyContent;
		this.replyUserName = replyUserName;
	}

	public Qna(int qnaNo, String userId, String qnaTitle, String qnaContent, String qnaStatus, Date qnaWriteDate) {
		super();
		this.qnaNo = qnaNo;
		this.userId = userId;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaStatus = qnaStatus;
		this.qnaWriteDate = qnaWriteDate;
	}
	

	@Override
	public String toString() {
		return "Qna [qnaNo=" + qnaNo + ", userId=" + userId + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent
				+ ", qnaStatus=" + qnaStatus + ", qnaWriteDate=" + qnaWriteDate + ", replyWriteDate=" + replyWriteDate
				+ ", replyTitle=" + replyTitle + ", replyContent=" + replyContent + ", replyUserName=" + replyUserName
				+ "]";
	}
	
	
	
}
