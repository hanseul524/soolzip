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

import myPage.service.MyPageService;
import recipe.model.vo.Recipe;
import story.model.vo.Story;
import user.model.vo.User;

/**
 * Servlet implementation class UserPageServlet
 */
@WebServlet("/user/page")
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		//넘어온 유저아이디
		String userId = request.getParameter("userId");
		//넘어온 유저아이디 회원 탈퇴체크
		boolean exitCk = true;
		String ck = new MyPageService().exitCk(userId);
		if(ck == "") {
			exitCk = false;
		};
		
		//유저페이지 레시피리스트
		List<Recipe> rList = new MyPageService().myPagePrintAllRecipe(userId);
		//유저가 쓴 스토리 리스트
		List<Story> sList = new MyPageService().myStory(userId);
		//유저가 쓴 레시피 카운트
		int recipeCount = new MyPageService().recipeCount(userId);
		//유저가 쓴 스토리 카운트
		int storyCount = new MyPageService().storyCount(userId);
		
		if(userId.equals(user.getUserId())){
			PrintWriter writer = response.getWriter();
			writer.print("<script>");
			writer.print("alert('마이페이지를 이용해주세요');");
			writer.print("history.go(-1);");
			writer.print("</script>");
			writer.close();
		}else if(exitCk && !(userId.equals(user.getUserId()))) {
			request.setAttribute("storyCount", storyCount);
			request.setAttribute("recipeCount", recipeCount);
			request.setAttribute("userId",userId);
			request.setAttribute("user",user);
			request.setAttribute("rList", rList);
			request.setAttribute("sList", sList);
			request.getRequestDispatcher("/WEB-INF/html/myPage/userPage.jsp").forward(request, response);
		}else {
			PrintWriter writer = response.getWriter();
			writer.print("<script>");
			writer.print("alert('없는 회원입니다!');");
			writer.print("history.go(-1);");
			writer.print("</script>");
			writer.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
