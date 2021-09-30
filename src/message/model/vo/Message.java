package message.model.vo;

import java.sql.Timestamp;

public class Message {
	private int msgNo;
	private String msgGetUser;
	private String msgSendUser;
	private String msgName;
	private String msgContents;
	private Timestamp msgSendDate;
	
	public Message() {}

	public Message(int msgNo, String msgGetUser, String msgSendUser, String msgName, String msgContents,
			Timestamp msgSendDate) {
		super();
		this.msgNo = msgNo;
		this.msgGetUser = msgGetUser;
		this.msgSendUser = msgSendUser;
		this.msgName = msgName;
		this.msgContents = msgContents;
		this.msgSendDate = msgSendDate;
	}

	public int getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}

	public String getMsgGetUser() {
		return msgGetUser;
	}

	public void setMsgGetUser(String msgGetUser) {
		this.msgGetUser = msgGetUser;
	}

	public String getMsgSendUser() {
		return msgSendUser;
	}

	public void setMsgSendUser(String msgSendUser) {
		this.msgSendUser = msgSendUser;
	}

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getMsgContents() {
		return msgContents;
	}

	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}

	public Timestamp getMsgSendDate() {
		return msgSendDate;
	}

	public void setMsgSendDate(Timestamp msgSendDate) {
		this.msgSendDate = msgSendDate;
	}

	@Override
	public String toString() {
		return "Message [msgNo=" + msgNo + ", msgGetUser=" + msgGetUser + ", msgSendUser=" + msgSendUser + ", msgName="
				+ msgName + ", msgContents=" + msgContents + ", msgSendDate=" + msgSendDate + "]";
	}
	
}
