package topqna.model.vo;

import java.sql.Date;

public class TopQna {
	private int topQnaNo;
	private String topQnaTitle;
	private String topQnaContent;
	private Date topQnaWriteDate;

	public TopQna() {}

	public int getTopQnaNo() {
		return topQnaNo;
	}

	public void setTopQnaNo(int topQnaNo) {
		this.topQnaNo = topQnaNo;
	}

	public String getTopQnaTitle() {
		return topQnaTitle;
	}

	public void setTopQnaTitle(String topQnaTitle) {
		this.topQnaTitle = topQnaTitle;
	}

	public String getTopQnaContent() {
		return topQnaContent;
	}

	public void setTopQnaContent(String topQnaContent) {
		this.topQnaContent = topQnaContent;
	}

	public Date getTopQnaWriteDate() {
		return topQnaWriteDate;
	}

	public void setTopQnaWriteDate(Date topQnaWriteDate) {
		this.topQnaWriteDate = topQnaWriteDate;
	}

	public TopQna(int topQnaNo, String topQnaTitle, String topQnaContent, Date topQnaWriteDate) {
		super();
		this.topQnaNo = topQnaNo;
		this.topQnaTitle = topQnaTitle;
		this.topQnaContent = topQnaContent;
		this.topQnaWriteDate = topQnaWriteDate;
	}

	public TopQna(String topQnaTitle, String topQnaContent) {
		super();
		this.topQnaTitle = topQnaTitle;
		this.topQnaContent = topQnaContent;
	}

	@Override
	public String toString() {
		return "TopQna [topQnaNo=" + topQnaNo + ", topQnaTitle=" + topQnaTitle + ", topQnaContent=" + topQnaContent
				+ ", topQnaWriteDate=" + topQnaWriteDate + "]";
	}
	
	
	
}
