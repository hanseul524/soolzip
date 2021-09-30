package story.model.vo;

import java.util.List;

public class PageData {
	private List<Story> storyList;
	private String pageNavi;
	
	public PageData() {
		super();
	}
	
	public List<Story> getStoryList() {
		return storyList;
	}
	public void setStoryList(List<Story> storyList) {
		this.storyList = storyList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	@Override
	public String toString() {
		return "PageData [storyList=" + storyList + ", pageNavi=" + pageNavi + "]";
	}
	
	
}
