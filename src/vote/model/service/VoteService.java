package vote.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import vote.model.dao.VoteDAO;
import vote.model.vo.RecipeCandidate;

public class VoteService {

	private JDBCTemplate jdbcTemplate;

	public VoteService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	
	
	public List<RecipeCandidate> printVoteRecipe() {
		Connection conn = null;
		List<RecipeCandidate> cList = null;
		try {
			conn = jdbcTemplate.createConnection();
			cList = new VoteDAO().selectVoteRecipe(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return cList;
	}

}
