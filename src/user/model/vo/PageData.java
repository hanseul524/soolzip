package user.model.vo;

import java.util.List;

import qna.model.vo.Qna;

public class PageData {
	private List<User> userList;
	private List<User> adminList;
	private String pageNavi;
	private String apageNavi;
	
	public PageData() {}

	
	public String getApageNavi() {
		return apageNavi;
	}


	public void setApageNavi(String apageNavi) {
		this.apageNavi = apageNavi;
	}


	public List<User> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<User> adminList) {
		this.adminList = adminList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}


	@Override
	public String toString() {
		return "PageData [userList=" + userList + ", adminList=" + adminList + ", pageNavi=" + pageNavi + ", apageNavi="
				+ apageNavi + "]";
	}

}
