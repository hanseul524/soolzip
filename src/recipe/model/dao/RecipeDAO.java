package recipe.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import recipe.model.vo.Recipe;
import recipe.model.vo.RecipeFile;
import recipe.model.vo.RecipeIngredient;
import recipe.model.vo.RecipeMakeProcess;
import recipe.model.vo.RecipeReply;

public class RecipeDAO {

	public int insertRecipe(Connection conn, Recipe recipe) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String querySeq = "SELECT SEQ_RECIPE.NEXTVAL AS SEQ_RECIPE FROM DUAL";
		String query = "INSERT INTO RECIPE VALUES(?,?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		try {
			pstmt = conn.prepareStatement(querySeq);
			rset = pstmt.executeQuery();
			int seqRecipe = Integer.MIN_VALUE;

			while (rset.next()) {
				seqRecipe = rset.getInt("SEQ_RECIPE");
			}

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, seqRecipe);
			pstmt.setString(2, recipe.getUserId());
			pstmt.setString(3, recipe.getRecipeTitle());
			pstmt.setString(4, recipe.getFileNo());
			pstmt.setString(5, recipe.getRecipeContents());
			pstmt.setString(6, recipe.getRecipeMainDrink());
			pstmt.setInt(7, recipe.getRecipeAlcohol());
			pstmt.setString(8, recipe.getRecipeTag());
			pstmt.setInt(9, recipe.getRecipeSaveState());
			pstmt.executeUpdate();
			result = seqRecipe;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int insertRecipeIngred(Connection conn, RecipeIngredient tmp, int result) {
		PreparedStatement pstmt = null;
		int result1 = 0;
		String query = "INSERT INTO recipe_ingredient VALUES(SEQ_INGREDIENT.NEXTVAL,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, result);
			pstmt.setString(2, tmp.getIngredientName());
			pstmt.setString(3, tmp.getIngredientGram());
			result1 = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result1;
	}

	public int insertRecipeMakeProcess(Connection conn, RecipeMakeProcess tmp, int result) {
		PreparedStatement pstmt = null;
		int result1 = 0;
		String query = "INSERT INTO recipe_make_process VALUES(SEQ_make.NEXTVAL,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, result);
			pstmt.setString(2, tmp.getFileNo());
			pstmt.setString(3, tmp.getMakeContents());
			result1 = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result1;
	}

	public int inserRecipeFile(Connection conn, RecipeFile tmp) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result1 = 0;
		String querySeq = "select seq_file.NEXTVAL as file_no from dual";
		String query = "INSERT INTO recipe_file VALUES(?,?,?,?, sysdate, ?)";
		try {
			pstmt = conn.prepareStatement(querySeq);
			rset = pstmt.executeQuery();
			int fileNo = Integer.MIN_VALUE;
			while (rset.next()) {
				fileNo = rset.getInt("file_no");
			}
			result1 = fileNo;

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fileNo);
			pstmt.setString(2, tmp.getFilePath());
			pstmt.setString(3, tmp.getFileName());
			pstmt.setLong(4, tmp.getFileSize());
			pstmt.setString(5, tmp.getRegName());
			// pstmt.setString(5, 유저아이디넣어야함);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result1;
	}

	public List<Recipe> selectAllRecipe(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Recipe> rList = null;
		String query = "select * from(SELECT ROW_NUMBER() OVER(ORDER BY recipe_NO DESC)AS NUM, recipe_no, user_id,(select Count(*) from recipe_like l where l.recipe_no=r.recipe_no) as like_cnt,(select Count(*) from recipe_reply rr where rr.recipe_no=r.recipe_no) as reply_cnt, recipe_title, file_name, recipe_contents ,recipe_viewCount,recipe_savestate FROM recipe r join recipe_file f using(file_no) where RECIPE_savestate = 1) where NUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			int viewCountPerPage = 12;// 한페이지당 보여줄게시글 갯수
			int start = currentPage * viewCountPerPage - (viewCountPerPage - 1);
			int end = currentPage * viewCountPerPage;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			rList = new ArrayList<Recipe>();
			while (rset.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeSaveState(rset.getInt("recipe_savestate"));
				recipe.setRecipeNo(rset.getInt("recipe_no"));
				recipe.setUserId(rset.getString("USER_ID"));
				recipe.setRecipeTitle(rset.getString("RECIPE_TITLE"));
				recipe.setFileName(rset.getString("file_name"));
				recipe.setRecipeContents(rset.getString("RECIPE_CONTENTS"));
				recipe.setRecipeReplyCount(rset.getInt("reply_cnt"));
				recipe.setRecipeLikeCount(rset.getInt("like_cnt"));
				recipe.setRecipeViewCount(rset.getInt("recipe_viewCount"));
				rList.add(recipe);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return rList;
	}

	public String getPageNavi(Connection conn, int currentPage) {
		// 1페이지당 10개씩 보여주는데 총 125개의 개시물이있으면 13개의 페이지가 있어야함
		int pageCountPerView = 5;// [1,2,3,4,5] , [6,7,8,9,10] 페이지번호는 5개 씩 짤라서 보여줌
		int viewTotalCount = totalCount(conn); // 총 게시물 수
		int viewCountPerPage = 12; // 한 페이지에 들어가는 게시물수
		int pageTotalCount = 0; // 페이지 총 개수

		int pageTotalCountMod = viewTotalCount % viewCountPerPage; // 총게시물 갯수를 페이지카운트로 나눈 나머지

		if (pageTotalCountMod > 0) {
			pageTotalCount = viewTotalCount / viewCountPerPage + 1; // 나머지가 0보다 크면 페이지를 하나 추가한다
		} else {
			pageTotalCount = viewTotalCount / viewCountPerPage;
		}

		int startNavi = ((currentPage - 1) / pageCountPerView) * pageCountPerView + 1;
		int endNavi = startNavi + pageCountPerView - 1;

		// 7페이지까지 게시물 존재하는데 8,9,10 페이지까지 보여지는것을 방지
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();

		if (needPrev) {
			sb.append("<a href='/recipe/list?currentPage=" + (startNavi - 1) + "'>[이전]</a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(i);
			} else {
				sb.append("<a href='/recipe/list?currentPage=" + i + "'>" + i + "</a>");
			}
		}
		if (needNext) {
			sb.append("<a href='/recipe/list?currentPage=" + (endNavi + 1) + "'>[다음]</a>");
		}

		return sb.toString();
	}

	// 페이지 토탈 갯수 가져오는 메소드
	public int totalCount(Connection conn) {
		int totalValue = 0;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM recipe where recipe_savestate = 1";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				totalValue = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
			JDBCTemplate.close(rset);
		}
		return totalValue;
	}

	// 레시피 리스트
	public Recipe selectOneRecipe(Connection conn,String sessionId, int recipeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select recipe_no,(select nvl(count(user_id),0) from recipe_like b where b.recipe_no = r.recipe_no and b.user_id = ?) as like_check, (select nvl(count(s.user_id),0) from recipe_scrap s where s.recipe_no = r.recipe_no and s.user_id = ?) as scrap_check, user_id,recipe_title, file_no, file_name, recipe_contents, recipe_maindrink,recipe_alcohol, recipe_Tag, recipe_savestate,recipe_viewcount,recipe_enrolldate, recipe_replycount, recipe_likecount,recipe_LegendState from recipe r join recipe_file f using(file_no) where recipe_no = ?";
		Recipe recipeOne = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sessionId);
			pstmt.setString(2, sessionId);
			pstmt.setInt(3, recipeNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				recipeOne = new Recipe();
				recipeOne.setRecipeNo(recipeNo);
				recipeOne.setLikeCheck(rset.getInt("like_check"));
				recipeOne.setScrapCheck(rset.getInt("scrap_check"));
				recipeOne.setUserId(rset.getString("user_id"));
				recipeOne.setRecipeTitle(rset.getString("recipe_title"));
				recipeOne.setRecipeContents(rset.getString("recipe_contents"));
				recipeOne.setRecipeMainDrink(rset.getString("recipe_maindrink"));
				recipeOne.setRecipeAlcohol(rset.getInt("recipe_alcohol"));
				recipeOne.setRecipeTag(rset.getString("recipe_Tag"));
				recipeOne.setRecipeSaveState(rset.getInt("recipe_savestate"));
				recipeOne.setFileName(rset.getString("file_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recipeOne;
	}

	// 재료 리스트
	public List<RecipeIngredient> selectOneRecipeIngr(Connection conn, int recipeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecipeIngredient> iList = null;
		RecipeIngredient recipeIngr = null;
		String query = "select * from recipe_ingredient where recipe_no = ? order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			rset = pstmt.executeQuery();
			iList = new ArrayList();
			while (rset.next()) {
				recipeIngr = new RecipeIngredient();
				recipeIngr.setIngredientNo(rset.getInt("ingredient_no"));
				recipeIngr.setIngredientName(rset.getString("ingredient_name"));
				recipeIngr.setIngredientGram(rset.getString("ingredient_gram"));
				iList.add(recipeIngr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return iList;
	}

	// 제조과정 리스트
	public List<RecipeMakeProcess> selectOneRecipeMkProcess(Connection conn, int recipeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecipeMakeProcess> mList = null;
		RecipeMakeProcess recipeMkProcess = null;
		String query = "select make_no, recipe_no, File_no, Make_contents, file_name from recipe_make_process join recipe_file using(file_no) where recipe_no = ? order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			rset = pstmt.executeQuery();
			mList = new ArrayList();
			while (rset.next()) {
				recipeMkProcess = new RecipeMakeProcess();
				recipeMkProcess.setMakeNo(rset.getInt("make_no"));
				recipeMkProcess.setRecipeNo(rset.getInt("recipe_no"));
				recipeMkProcess.setMakeContents(rset.getString("Make_contents"));
				recipeMkProcess.setFileName(rset.getString("file_name"));
				mList.add(recipeMkProcess);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mList;
	}


	//레시피 댓글 등록
	public int insertRecipeReply(Connection conn, String userId, int recipeNo, String replyContents,Timestamp uploadTime) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO RECIPE_REPLY VALUES(SEQ_RECIPE_REPLY.NEXTVAL,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2,userId);
			pstmt.setString(3, replyContents);
			pstmt.setTimestamp(4, uploadTime);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//레시피 댓글 리스트
	public List<RecipeReply> selectAllRecipeReply(Connection conn, int recipeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RecipeReply> List = null;
		RecipeReply recipeReply = null;
		String query = "select * from recipe_reply where recipe_no = ? order by 1 desc";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			rset = pstmt.executeQuery();
			List = new ArrayList();
			while (rset.next()) {
				recipeReply = new RecipeReply();
				recipeReply.setReplyNo(rset.getInt("reply_no"));
				recipeReply.setRecipeNo(rset.getInt("recipe_no"));
				recipeReply.setReplyContents(rset.getString("CONTENTS"));
				recipeReply.setReplyUserId(rset.getString("REPLY_NAME"));
				recipeReply.setReplyDate(rset.getTimestamp("ENROLLDATE"));
				List.add(recipeReply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return List;
	}
	
	//레시피 댓글 삭제
	public int deleteRecipeReplyOne(Connection conn, int replyNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM RECIPE_REPLY WHERE REPLY_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//레시피 댓글 수정
	public int updateRecipeReplyOne(Connection conn, int replyNo, String replyContents) {
		
		PreparedStatement pstmt = null;
		int result=0;
		String query="UPDATE recipe_reply SET CONTENTS=? WHERE REPLY_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, replyContents);
			pstmt.setInt(2, replyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//레시피 좋아요 취소
	public int deleteRecipeLike(Connection conn, int recipeNo, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM recipe_like WHERE recipe_NO=? and user_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//레시피 좋아요
	public int insertRecipeLike(Connection conn, int recipeNo, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into recipe_like values(SEQ_RECIPE_LIKE.NEXTVAL,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	// 레시피 조회수
	public int updateRecipeViewCount(Connection conn, int recipeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update recipe set recipe_viewcount = recipe_viewcount+1 where recipe_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteRecipeScrap(Connection conn, int recipeNo, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM recipe_scrap WHERE recipe_NO=? and user_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertRecipeScrap(Connection conn, int recipeNo, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into recipe_scrap values(seq_recipe_scrap.NEXTVAL,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
