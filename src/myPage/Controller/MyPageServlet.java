package myPage.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import message.model.service.MsgService;
import message.model.vo.Message;
import myPage.service.MyPageService;
import recipe.model.vo.Recipe;
import recipe.model.vo.RecipeReply;
import story.model.vo.Story;
import story.model.vo.StoryReply;
import user.model.vo.User;


@WebServlet("/myPage/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
	      User user = new User();
	      if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
	      String userId = user.getUserId();
		//유저 정보 저장
		user = new MyPageService().addUser(userId);
		//공개된 레시피 갯수 카운트
		int recipeCount = new MyPageService().recipeCount(userId);
		//내가쓴 스토리 갯수 카운트
		int storyCount = new MyPageService().storyCount(userId);
		//전체공개리스트
		List<Recipe> rList = new MyPageService().myPagePrintAllRecipe(userId);
		//임시공개 리스트
		List<Recipe> cList = new MyPageService().myCacheRecipe(userId);
		//스토리 리스트
		List<Story> sList = new MyPageService().myStory(userId);
		//스크랩 리스트
		List<Recipe> scList = new MyPageService().myScrap(userId);
		//레시피 내가쓴 댓글 리스트
		List<RecipeReply> reList = new MyPageService().myRecipeReply(userId);
		//스토리 내가쓴 댓글 리스트
		List<StoryReply> srList = new MyPageService().myStoryReply(userId);
		//보낸쪽지
		List<Message> msList = new MsgService().myMessageSendList(userId);
		//받은쪽지
		List<Message> mgList = new MsgService().myMessageGetList(userId);
		
		for(Story a : sList) {
			System.out.println(a.toString());
		}
		
		if(user !=null) {
			request.setAttribute("srList", srList);
			request.setAttribute("mgList", mgList);
			request.setAttribute("msList", msList);
			request.setAttribute("reList", reList);
			request.setAttribute("scList", scList);
			request.setAttribute("sList", sList);
			request.setAttribute("cList", cList);
			request.setAttribute("rList", rList);
			request.setAttribute("storyCount", storyCount);
			request.setAttribute("recipeCount", recipeCount);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/html/myPage/myPage.jsp").forward(request, response);
		}else {
			PrintWriter writer = response.getWriter(); 
			writer.print("<script>");
			writer.print("alert('로그인 해주세요!');");
			writer.print("location.href = '/index.jsp'");
			writer.print("</script>");
			writer.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
