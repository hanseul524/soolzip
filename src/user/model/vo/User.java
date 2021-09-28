package user.model.vo;

public class User {

	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userEmail;
	private String userPhone;
	
	public User() {}
	

	// 회원가입 
	public User(String userId, String userPwd, String userName, String userEmail, String userPhone) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
	}
		
		//회원정보 수정
	public User(String userId, String userPwd, String userEmail, String userPhone) {
			super();
			this.userId = userId;
			this.userPwd = userPwd;
			this.userEmail = userEmail;
			this.userPhone = userPhone;
		}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPwd() {
		return userPwd;
	}


	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", userPhone=" + userPhone + "]";
	}
	
}
