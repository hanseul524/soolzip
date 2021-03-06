package qna.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import qna.model.dao.QnaDAO;
import qna.model.vo.PageData;
import qna.model.vo.Qna;

public class QnaService {
	
	private JDBCTemplate jdbcTemplate;

	public QnaService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}

	public List<Qna> selectQna(String userId) {
		List<Qna> qList = null;
		Connection conn = null;
		QnaDAO qDao = new QnaDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			qList = qDao.selectQna(userId , conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		
		return qList;
	}

	public int insertQna(Qna qna) {
		int result =0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new QnaDAO().insertQna(conn,qna);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public Qna selectQnaDetail(int qnaNo) {
		Qna qnaOne = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			qnaOne = new QnaDAO().selectQnaDetail(conn, qnaNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return qnaOne;
	}
	//관리자 페이지 문의사항 전체조회
	public PageData printAllQna(int currentPage) {
		PageData pd = new PageData();
		Connection conn = null;
		QnaDAO qDAO = new QnaDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			List<Qna> qList = qDAO.selectAllQna(conn, currentPage);
			String pageNavi = qDAO.getPageNavi(conn, currentPage);
			pd.setQnaList(qList);
			pd.setPageNavi(pageNavi);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}

	public int registerReply(int qnaNO, String replyContent, String userId) {
	
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.createConnection();
			result = new QnaDAO().updateReply(conn, qnaNO, replyContent, userId);
			if(result > 0) {
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
