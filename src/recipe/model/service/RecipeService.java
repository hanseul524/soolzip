package recipe.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import common.JDBCTemplate;
import recipe.model.dao.RecipeDAO;
import recipe.model.vo.PageData;
import recipe.model.vo.Recipe;
import recipe.model.vo.RecipeFile;
import recipe.model.vo.RecipeIngredient;
import recipe.model.vo.RecipeMakeProcess;
import recipe.model.vo.RecipeReply;

public class RecipeService {

	private JDBCTemplate jdbcTemplate;

	public RecipeService() {
		jdbcTemplate = JDBCTemplate.getConnection();
	}
	
	// 레시피 등록 서비스
	public int registerRecipe(Recipe recipe, List<RecipeIngredient> ingredList, List<RecipeMakeProcess> makeList) {
		int result = Integer.MIN_VALUE;
		Connection conn = null;
		RecipeDAO recipeDAO = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			
			// 레시피의 파일id의 (대표사진) 파일name 존재할시 file 인서트(대표사진)
			if (recipe.getRecipeFile().getFileName() != null && recipe.getRecipeFile().getFileName() != "") {
				recipe.setFileNo(String.valueOf(recipeDAO.inserRecipeFile(conn, recipe.getRecipeFile())));
			}
			// result는 등록된 레시피 번호 (시퀀스 번호)
			result = recipeDAO.insertRecipe(conn, recipe);

			if (result > Integer.MIN_VALUE) {

				for (RecipeMakeProcess tmp : makeList) {
					if (tmp.getRecipeFile().getFileName() != null && tmp.getRecipeFile().getFileName() != "") {
						tmp.setFileNo(String.valueOf(recipeDAO.inserRecipeFile(conn, tmp.getRecipeFile())));
					}
					if (recipeDAO.insertRecipeMakeProcess(conn, tmp, result) <= 0)
						throw new SQLException("error");
				}

				for (RecipeIngredient tmp : ingredList) {
					if (recipeDAO.insertRecipeIngred(conn, tmp, result) <= 0)
						throw new SQLException("error");
				}
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			JDBCTemplate.rollback(conn);
			// e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	// 레시피 selectAll by RecipeListServlet
	public PageData printAllRecipe(int currentPage) {
		PageData pd = new PageData();

		Connection conn = null;
		RecipeDAO rDao = new RecipeDAO();

		try {
			conn = jdbcTemplate.createConnection();
			pd.setRecipeList(rDao.selectAllRecipe(conn, currentPage));
			pd.setPageNavi(rDao.getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn); 
		}
		return pd;
	}

	//레시피 selectOne By RecipeDetailServlet
	public Recipe printOneRecipe(String sessionId, int recipeNo ) {
		Recipe recipeOne = null;
		Connection conn = null;
		List<RecipeReply> list = null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn= jdbcTemplate.createConnection();
			recipeOne= rDao.selectOneRecipe(conn,sessionId,recipeNo);
			//RecipeReply불러옴 // 댓글
			list = rDao.selectAllRecipeReply(conn,recipeNo);
			recipeOne.setReplies(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return recipeOne;
	}
	
	//레시피 재료 select By RecipeDetailServlet
	public List<RecipeIngredient> printOneRecipeIngr(int recipeNo) {
		List<RecipeIngredient> iList = null;
		Connection conn = null;
		RecipeDAO rDao = new RecipeDAO();

		try {
			conn = jdbcTemplate.createConnection();
			iList = rDao.selectOneRecipeIngr(conn, recipeNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return iList;
	}
	// 레시피 제조과정 select By RecipeDetailServlet
	public List<RecipeMakeProcess> printOneRecipeMkProcess(int recipeNo) {
		List<RecipeMakeProcess> mList = null;
		Connection conn = null;
		RecipeDAO rDao = new RecipeDAO();

		try {
			conn = jdbcTemplate.createConnection();
			mList = rDao.selectOneRecipeMkProcess(conn, recipeNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return mList;
	}

	//레시피 댓글 insert by RecipeReplyWriteServlet
	public int registerRecipeReply(String userId, int recipeNo, String replyContents, Timestamp uploadTime) {
		int result = 0;
		Connection conn =null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.insertRecipeReply(conn, userId, recipeNo, replyContents, uploadTime);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	//레시피 댓글 delete by RecipeReplyDeleteServlet
	public int removeRecipeReplyOne(int replyNo) {
		int result = 0;
		Connection conn =null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.deleteRecipeReplyOne(conn, replyNo);
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

	//레시피 댓글 update by RecipeReplyModifyServlet
	public int modifyRecipeReplyOne(int replyNo, String replyContents) {
		int result = 0;
		Connection conn = null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.updateRecipeReplyOne(conn, replyNo, replyContents);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	// 좋아요 취소
	public int removeRecipeLike(int recipeNo, String userId) {
		int result = 0;
		Connection conn =null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.deleteRecipeLike(conn, recipeNo, userId);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	//좋아요인서트
	public int insertRecipeLike(int recipeNo, String userId) {
		int result = 0;
		Connection conn =null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.insertRecipeLike(conn, recipeNo, userId);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	// 조회수 1업
	public int RecipeViewCount(int recipeNo) {
		int result = 0;
		Connection conn = null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.updateRecipeViewCount(conn, recipeNo);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	// 스크랩 취소
	public int removeRecipeScrap(int recipeNo, String userId) {
		int result = 0;
		Connection conn =null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.deleteRecipeScrap(conn, recipeNo, userId);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	//스크랩 인서트
	public int insertRecipeScrap(int recipeNo, String userId) {
		int result = 0;
		Connection conn =null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			result = rDao.insertRecipeScrap(conn, recipeNo, userId);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public int removeRecipeOne(int recipeNo) {
		int result =0;
		Connection conn =null;
		RecipeDAO rDao = new RecipeDAO();
		try {
			conn = jdbcTemplate.createConnection();
			System.out.println("step0");
			result = rDao.deleteRecipeOne(conn, recipeNo);
			System.out.println("step1");
			rDao.deleteRecipeMkProcess(conn,recipeNo);
			System.out.println("step2");
			rDao.deleteRecipeIngredient(conn, recipeNo);
			System.out.println("step3");
			rDao.deleteRecipeReplyOne(conn, recipeNo);
			JDBCTemplate.commit(conn);
		} catch (SQLException e) {
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int modifyRecipe(Recipe recipe, List<RecipeIngredient> ingredList, List<RecipeMakeProcess> makeList, String[] rmIngredientIds, String[] rmMakeIds) {
		int result = 0;
		Connection conn = null;
		RecipeDAO recipeDAO = new RecipeDAO();
		
		try {
			conn = jdbcTemplate.createConnection();
			//1. recipe 테이블에 데이터들을 받아와 수정한다.
			result = recipeDAO.updateRecipe(conn, recipe);
			if (result > 0) {
				//2. 레시피 재료 삭제
				for(String str : rmIngredientIds) {
					recipeDAO.deleteRecipeIngredientId(conn, str);
				}
				//레시피 재료 수정
				for (RecipeIngredient tmp : ingredList) {
					// 기존에 재료 번호가 존재하지 않으면 수정될 재료는 insert
					if(tmp.getIngredientNo()!=0) {
						if (recipeDAO.updateRecipeIngred(conn, tmp) <= 0)
							throw new SQLException("error");						
					}else {
					// 기존에 재료 번호가 존재하면 수정될 재료는 
						if (recipeDAO.insertRecipeIngred(conn, tmp, tmp.getRecipeNo()) <= 0)
							throw new SQLException("error");
					}
				}
				//기존에 있던 제조과정은 delete한다.
				for(RecipeMakeProcess tmp :makeList) {
					for(String str : rmMakeIds) {
						recipeDAO.deleteRecipeMkProcess(conn, str);
					}
					int fileNo = tmp.getRecipeFile().getFileNo();
					tmp.setRecipeNo(recipe.getRecipeNo());
					// 수정할 파일의 번호가 존재하면 update
					if(tmp.getRecipeFile().getFileNo()!=0 && !"".equals(tmp.getRecipeFile().getFilePath())) {
						fileNo= recipeDAO.updateRecipeFile(conn,tmp.getRecipeFile());
						if(fileNo<=0) throw new SQLException("error");
					//수정할 파일번호가 존재하지 않으면 insert
					}else if(tmp.getRecipeFile().getFileNo() ==0 && !"".equals(tmp.getRecipeFile().getFilePath())){
						fileNo = recipeDAO.inserRecipeFile(conn,tmp.getRecipeFile());	
						if(fileNo<=0) throw new SQLException("error");
					}
					
					//수정할 제조과정 번호가 존재하면 update
					if(tmp.getMakeNo() != 0) {
						if(recipeDAO.updateRecipeMakeProcess(conn, tmp, fileNo)<=0)
							throw new SQLException("error");
					//수정할 제조과정 번호가 존재하지 않으면 insert
					}else {
						if(recipeDAO.insertRecipeMakeProcess1(conn, tmp, fileNo)<=0)
							throw new SQLException("error");
					}
				}
				
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			JDBCTemplate.rollback(conn);
			// e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public PageData printKategorieRecipe(int currentPage, String recipeMainDrink) {
		PageData pd = new PageData();

		Connection conn = null;
		RecipeDAO rDao = new RecipeDAO();

		try {
			conn = jdbcTemplate.createConnection();
			pd.setRecipeList(rDao.selectKategorieRecipe(conn, currentPage, recipeMainDrink));
			pd.setPageNavi(rDao.getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn); 
		}
		return pd;
	}

	public PageData printSearchRecipe(int currentPage, String searchInput) {
		PageData pd = new PageData();

		Connection conn = null;
		RecipeDAO rDao = new RecipeDAO();

		try {
			conn = jdbcTemplate.createConnection();
			pd.setRecipeList(rDao.selectSearchRecipe(conn, currentPage, searchInput));
			pd.setPageNavi(rDao.getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn); 
		}
		return pd;
	}
	//명예의전당 레시피 리스트
	public PageData printAllLegendRecipe(int currentPage) {
		PageData pd = new PageData();

		Connection conn = null;
		RecipeDAO rDao = new RecipeDAO();

		try {
			conn = jdbcTemplate.createConnection();
			pd.setRecipeList(rDao.selectAllLegendRecipe(conn, currentPage));
			pd.setPageNavi(rDao.getLegendPageNavi(conn, currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn); 
		}
		return pd;
	}

}
