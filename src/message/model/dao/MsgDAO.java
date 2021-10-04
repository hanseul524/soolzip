package message.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import message.model.vo.Message;

public class MsgDAO {

	public List<Message> myMessageSendList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query ="select * from message where msg_send_user = ? and sent_del='N' order by MSG_SEND_DATE DESC";
		List<Message> msList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			msList= new ArrayList<>();
			
			while(rset.next()) {
				Message msg = new Message();
				msg.setMsgNo(rset.getInt("MSG_NO"));
				msg.setMsgGetUser(rset.getString("MSG_GET_USER"));
				msg.setMsgSendUser(rset.getString("MSG_SEND_USER"));
				msg.setMsgName(rset.getString("MSG_NAME"));
				msg.setMsgContents(rset.getString("MSG_CONTENTS"));
				msg.setMsgSendDate(rset.getTimestamp("MSG_SEND_DATE"));
				msList.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return msList;
	}

	public List<Message> myMessageGetList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query ="select MSG_NO,MSG_GET_USER,MSG_SEND_USER,MSG_NAME, MSG_CONTENTS, MSG_SEND_DATE,(SELECT USER_NO FROM USERS u WHERE m.msg_send_user = u.user_id ) as user_no from message m where msg_get_user = ? and recv_del='N' order by MSG_SEND_DATE DESC";
		List<Message> mgList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			mgList= new ArrayList<>();
			
			while(rset.next()) {
				Message msg = new Message();
				msg.setMsgNo(rset.getInt("MSG_NO"));
				msg.setUserNo(rset.getInt("USER_NO"));
				msg.setMsgGetUser(rset.getString("MSG_GET_USER"));
				msg.setMsgSendUser(rset.getString("MSG_SEND_USER"));
				msg.setMsgName(rset.getString("MSG_NAME"));
				msg.setMsgContents(rset.getString("MSG_CONTENTS"));
				msg.setMsgSendDate(rset.getTimestamp("MSG_SEND_DATE"));
				mgList.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mgList;
	}
	//받은메세지 상세페이지
	public List<Message> myGetMessageDetail(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Message> mdList = null;
		String query = "select * from message where msg_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			rset = pstmt.executeQuery();
			mdList = new ArrayList<>();
			
			while(rset.next()) {
				Message msg = new Message();
				msg.setMsgNo(rset.getInt("MSG_NO"));
				msg.setMsgGetUser(rset.getString("MSG_GET_USER"));
				msg.setMsgSendUser(rset.getString("MSG_SEND_USER"));
				msg.setMsgName(rset.getString("MSG_NAME"));
				msg.setMsgContents(rset.getString("MSG_CONTENTS"));
				msg.setMsgSendDate(rset.getTimestamp("MSG_SEND_DATE"));
				mdList.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return mdList;
	}
	//받은 메세지 삭제
	public int deleteMsg(Connection conn, int msgNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update message set recv_del='Y' where msg_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//받은메세지 답장
	public int replyMessage(Connection conn, Message msg) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MESSAGE VALUES(MSG_NO_SEQ.NEXTVAL,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, msg.getMsgGetUser());
			pstmt.setString(2, msg.getMsgSendUser());
			pstmt.setString(3, msg.getMsgName());
			pstmt.setString(4, msg.getMsgContents());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	//보낸메세지 상세
	public List<Message> mySendMessageDetail(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Message> sdList = null;
		String query = "select * from message where msg_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			rset = pstmt.executeQuery();
			sdList = new ArrayList<>();
			
			while(rset.next()) {
				Message msg = new Message();
				msg.setMsgNo(rset.getInt("MSG_NO"));
				msg.setMsgGetUser(rset.getString("MSG_GET_USER"));
				msg.setMsgSendUser(rset.getString("MSG_SEND_USER"));
				msg.setMsgName(rset.getString("MSG_NAME"));
				msg.setMsgContents(rset.getString("MSG_CONTENTS"));
				msg.setMsgSendDate(rset.getTimestamp("MSG_SEND_DATE"));
				sdList.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return sdList;
	}
	//보낸메세지 삭제
	public int deleteSendMsg(Connection conn, int msgNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update message set sent_del='Y' where msg_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
