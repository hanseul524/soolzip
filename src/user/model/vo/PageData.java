package user.model.vo;

import java.util.List;

public class PageData {
	private List<User> userList;
	private List<User> adminList;
	private String pageNavi;
	
	public PageData() {}
	
	
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
		return "PageData [userList=" + userList + ", adminList=" + adminList + ", pageNavi=" + pageNavi + "]";
	}
	
}
