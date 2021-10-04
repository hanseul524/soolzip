package qna.model.vo;

import java.util.List;

public class PageData {
	private List<Qna> qnaList;
	private String pageNavi;
	
	public PageData() {}

	public List<Qna> getQnaList() {
		return qnaList;
	}

	public void setQnaList(List<Qna> qnaList) {
		this.qnaList = qnaList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	@Override
	public String toString() {
		return "PageData [qnaList=" + qnaList + ", pageNavi=" + pageNavi + "]";
	}


}
