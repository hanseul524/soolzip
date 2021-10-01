package message.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import message.model.dao.MsgDAO;
import message.model.vo.Message;

public class MsgService {

	private JDBCTemplate jdbcTemplate;

	public MsgService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	
	//내가쓴 쪽지 리스트
		public List<Message> myMessageSendList(String userId) {
			Connection conn = null;
			List<Message> msList = null;
			MsgDAO mDao = new MsgDAO();
			
			try {
				conn = jdbcTemplate.createConnection();
				msList = mDao.myMessageSendList(conn,userId);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return msList;
		}
		//받은쪽지 리스트
		public List<Message> myMessageGetList(String userId) {
			Connection conn = null;
			List<Message> mgList = null;
			MsgDAO mDao = new MsgDAO();
			
			try {
				conn = jdbcTemplate.createConnection();
				mgList = mDao.myMessageGetList(conn,userId);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return mgList;
		
		}
		//받은쪽지 상세
		public List<Message> myGetMessageDetail(int msgNo) {
			Connection conn = null;
			List<Message> mdList = null;
			MsgDAO mDao = new MsgDAO();
			
			try {
				conn= jdbcTemplate.createConnection();
				mdList = mDao.myGetMessageDetail(conn,msgNo);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(conn);
			}
			return mdList;
		}

		//메세지 삭제
		public int deleteMsg(int msgNo) {
			Connection conn = null;
			int result = 0;
			MsgDAO mDao = new MsgDAO();
			try {
				conn = jdbcTemplate.createConnection();
				result = mDao.deleteMsg(conn,msgNo);
				
				if(result>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return result;
		}
		
		//마이페이지 받은쪽지 답장
		public int replyMessage(Message msg) {
			int result = 0;
			Connection conn = null;
			MsgDAO mDao = new MsgDAO();
			
			try {
				conn = jdbcTemplate.createConnection();
				result = mDao.replyMessage(conn,msg);
				
				if(result>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return result;
		}
		//보낸메세지 상세
		public List<Message> mySendMessageDetail(int msgNo) {
			Connection conn = null;
			List<Message> sdList = null;
			MsgDAO mDao = new MsgDAO();
			
			try {
				conn= jdbcTemplate.createConnection();
				sdList = mDao.mySendMessageDetail(conn,msgNo);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(conn);
			}
			return sdList;
		}
		//보낸메세지 삭제
		public int deleteSendMsg(int msgNo) {
			Connection conn = null;
			int result = 0;
			MsgDAO mDao = new MsgDAO();
			try {
				conn = jdbcTemplate.createConnection();
				result = mDao.deleteSendMsg(conn, msgNo);
				if(result>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return result;
		}
}
