package myPage.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.model.service.RecipeService;
import recipe.model.vo.PageData;
import recipe.model.vo.Recipe;
import user.model.service.UserService;
import user.model.vo.User;


/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/myPage/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		//유저 정보 저장
		User user = new UserService().addUser(userId);
		
		//공개된 레시피 갯수 카운트
		int recipeCount = new UserService().recipeCount(userId);
		
		//내가쓴 스토리 갯수 카운트
		int storyCount = new UserService().storyCount(userId);
		
		//임시저장 페이징
		int currentPage = 0;
		String getCurrentPage = request.getParameter("currentPage");
		if(getCurrentPage == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(getCurrentPage);
		}
		PageData pageData = new RecipeService().myPagePrintAllRecipe(currentPage,userId);
		List<Recipe> rList = pageData.getRecipeList();
		if(user !=null) {
			request.setAttribute("pageNavi", pageData.getPageNavi());
			request.setAttribute("rList", rList);
			request.setAttribute("storyCount", storyCount);
			request.setAttribute("recipeCount", recipeCount);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/html/myPage/myPage.jsp").forward(request, response);
		}else {
			System.out.println("retry");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
