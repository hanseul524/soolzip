package myPage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import recipe.model.vo.Recipe;
import story.model.vo.Story;
import user.model.vo.User;

public class MyPageDAO {

	// 회원정보 수정
	public int updateUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE USERS SET USER_PWD = ?, USER_EMAIL =?, USER_PHONE=? WHERE USER_ID=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserPwd());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserPhone());
			pstmt.setString(4, user.getUserId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 마이페이지 입장시 유저vo에 유저 정보담기
	public User addUser(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		String query = "SELECT USER_NO, USER_ID, USER_PWD, USER_EMAIL, USER_PHONE FROM USERS WHERE USER_ID=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				user = new User();
				user.setUserNo(rset.getInt("USER_NO"));
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPwd(rset.getString("USER_PWD"));
				user.setUserEmail(rset.getString("USER_EMAIL"));
				user.setUserPhone(rset.getString("USER_PHONE"));
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return user;
	}

	// 마이페이지 내가쓴 공개 레시피 갯수
	public int countRecipe(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM RECIPE WHERE USER_ID=? AND RECIPE_SAVESTATE='1'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}

	// 마이페이지 내가쓴 스토리 갯수
	public int countStory(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String query = "SELECT COUNT(*) FROM STORY WHERE USER_NO = (SELECT USER_NO FROM USERS WHERE USER_ID = ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return count;
	}
	
	// 마이페이지 전체공개 레시피 리스트
	public List<Recipe> myPageSelectAllRecipe(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Recipe> rList = null;
		String query = "select recipe_no, user_id, recipe_title, F.file_name,recipe_enrollDate, recipe_replycount, recipe_LikeCount from recipe R,recipe_file F where  R.file_no = F.file_no and r.file_no is not null and USER_ID=? and recipe_savestate='1' order by recipe_no";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset=pstmt.executeQuery();
			rList = new ArrayList<Recipe>();
			while(rset.next()) {
				Recipe recipe= new Recipe();
				recipe.setRecipeNo(rset.getInt("RECIPE_NO"));
				recipe.setUserId(rset.getString("USER_ID"));
				recipe.setRecipeTitle(rset.getString("RECIPE_TITLE"));
				recipe.setFileName(rset.getString("FILE_NAME"));
				recipe.setRecipeReplyCount(rset.getInt("RECIPE_REPLYCOUNT"));
				recipe.setRecipeLikeCount(rset.getInt("RECIPE_LIKECOUNT"));
				recipe.setRecipeEnrollDate(rset.getDate("RECIPE_ENROLLDATE"));
				rList.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return rList;
	}
	//마이페이지 임시저장레시피 조회
	public List<Recipe> myCacheRecipe(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Recipe> cList = null;
		String query = "select recipe_title, F.file_name,recipe_enrollDate, recipe_replycount, recipe_LikeCount from recipe R,recipe_file F where  R.file_no = F.file_no and r.file_no is not null and USER_ID=? and recipe_savestate='0' order by recipe_no";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset=pstmt.executeQuery();
			cList = new ArrayList<Recipe>();
			while(rset.next()) {
				Recipe recipe= new Recipe();
				recipe.setRecipeTitle(rset.getString("RECIPE_TITLE"));
				recipe.setFileName(rset.getString("file_name"));
				recipe.setRecipeReplyCount(rset.getInt("recipe_replycount"));
				recipe.setRecipeLikeCount(rset.getInt("recipe_LikeCount"));
				recipe.setRecipeEnrollDate(rset.getDate("recipe_enrollDate"));
				cList.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cList;
	}
	//마이페이지 스토리 조회
	public List<Story> myStory(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Story> sList = null;
		String query = "SELECT STORY_ENROLLDATE, S.STORY_NO, STORY_CONTENTS, (SELECT COUNT(*) FROM STORY_REPLY WHERE STORY_NO = S.STORY_NO)as SCNT, F.STORYFILE_NO, F.STORYFILE_NAME FROM STORY S, STORY_FILE F WHERE S.STORY_NO = F.STORY_NO AND USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			sList = new ArrayList<>();
			while(rset.next()) {
				Story story = new Story();
				story.setFileNo(rset.getString("STORYFILE_NO"));
				story.setFileName(rset.getString("STORYFILE_NAME"));
				story.setStoryEnrollDate(rset.getDate("STORY_ENROLLDATE"));
				story.setStoryContents(rset.getString("STORY_CONTENTS"));
				story.setStoryReplyCount(rset.getInt("SCNT"));
				story.setStoryNo(rset.getInt("STORY_NO"));
				sList.add(story);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return sList;
	}
	//스크랩 리스트
	public List<Recipe> myScrap(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Recipe> scList = null;
		String query = "select R.recipe_no, recipe_title, F.file_name ,recipe_enrollDate, recipe_replycount, recipe_LikeCount from recipe R,recipe_file F,RECIPE_SCRAP S where(R.file_no = F.file_no and r.file_no is not null) and (R.USER_ID=?) AND (S.RECIPE_NO = R.RECIPE_NO)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			scList = new ArrayList<>();
			while(rset.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeNo(rset.getInt("RECIPE_NO"));
				recipe.setRecipeTitle(rset.getString("RECIPE_TITLE"));
				recipe.setFileName(rset.getString("FILE_NAME"));
				recipe.setRecipeEnrollDate(rset.getDate("RECIPE_ENROLLDATE"));
				recipe.setRecipeReplyCount(rset.getInt("RECIPE_REPLYCOUNT"));
				recipe.setRecipeLikeCount(rset.getInt("RECIPE_LIKECOUNT"));
				scList.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return scList;
	}
	
}