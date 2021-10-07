package vote.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import recipe.model.vo.Recipe;
import vote.model.dao.VoteDAO;
import vote.model.vo.RecipeCandidate;

public class VoteService {

	private JDBCTemplate jdbcTemplate;

	public VoteService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	
	// 투표 현황 페이지에 뿌려줄 데이터
	public List<RecipeCandidate> printVoteRecipe(String userId) {
		Connection conn = null;
		List<RecipeCandidate> cList = null;
		try {
			conn = jdbcTemplate.createConnection();
			cList = new VoteDAO().selectVoteRecipe(conn,userId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return cList;
	}


	public List<Recipe> printCandidateRecipe() {
		Connection conn = null;
		List<Recipe> rList = null;
		try {
			conn = jdbcTemplate.createConnection();
			rList = new VoteDAO().selectCandidateRecipe(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return rList;
	}

	public int registerCandidate(String[] recipeNos) {
		int result = 0;
		Connection conn = null;
		VoteDAO vDao = new VoteDAO();
		try {
			conn = jdbcTemplate.createConnection();
			for(String tmp : recipeNos) {
				if(vDao.insertCandidate(conn,tmp)<0) {
					throw new SQLException("error");
				}
			}
			result = 1;
			JDBCTemplate.commit(conn);
		} catch (SQLException e) {
			JDBCTemplate.rollback(conn);
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public int voteStateModify(String votingState) {
		int result = 0;
		Connection conn = null;
		VoteDAO vDao = new VoteDAO();
		try {
			conn = jdbcTemplate.createConnection();
			//투표 진행 테이블에 투표진행 상태값 변경
			result = vDao.updateVoteState(conn ,votingState);
			if(result>0) {
				//투표 테이블에 투표 상태 값을 Y로 변경
				vDao.updateUserVoteState(conn);
				
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

	public int removeVoteCandidate() {
		int result = 0;
		Connection conn = null;
		VoteDAO vDao = new VoteDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = vDao.updateLegendRecipeState(conn);
			System.out.println("step1");
			if(result>0) {
				if(vDao.deleteVoteCandidate(conn)<0) {
					throw new SQLException("error");
				}
			}
			System.out.println("step2");
			JDBCTemplate.commit(conn);
		} catch (SQLException e) {
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}	
		return result;
	}

	public String printVotingState() {
		Connection conn = null;
		VoteDAO vDao = new VoteDAO();
		String voteState="";
		try {
			conn = jdbcTemplate.createConnection();
			voteState = vDao.selectVotingState(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}	
		return voteState;
	}

	public int insertVoteAction(int candidateNo, String userId) {
		int result = 0;
		Connection conn = null;
		VoteDAO vDao = new VoteDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = vDao.insertVoteAction(conn,candidateNo,userId);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}	
		return result;
	}

	public int removeVoteAction(int candidateNo, String userId) {
		int result = 0;
		Connection conn = null;
		VoteDAO vDao = new VoteDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = vDao.deleteVoteAction(conn,candidateNo,userId);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}	
		return result;
	}

}
