package topqna.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import topqna.model.dao.TopQnaDAO;
import topqna.model.vo.TopQna;

public class TopQnaService {
	private JDBCTemplate jdbcTemplate;

	public TopQnaService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}

	public List<TopQna> selectTopQna() {
		List<TopQna> tqList = null;
		Connection conn = null;
		TopQnaDAO tqDao = new TopQnaDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			tqList = tqDao.selectTopQna(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		
		return tqList;
	}

	public int updateTopQna(TopQna topQna) {
		int result = 0;
		Connection conn = null;	
		try {
			conn = jdbcTemplate.createConnection();
			result = new TopQnaDAO().updateTopQna(conn,topQna);
			if(result > 0) {
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

	
		
	
	
	

}
