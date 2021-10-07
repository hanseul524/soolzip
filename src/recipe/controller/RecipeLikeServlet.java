package recipe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.model.service.RecipeService;
import user.model.vo.User;

/**
 * Servlet implementation class RecipeLikeServlet
 */
@WebServlet("/recipe/like")
public class RecipeLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		int likeCheck = Integer.parseInt(request.getParameter("likeCheck"));
		int result = 0;
		if(likeCheck==1) {
			result = new RecipeService().removeRecipeLike(recipeNo,userId);
			if(result>0) {
				response.sendRedirect("/recipe/detail?recipeNo="+recipeNo);
			}else {
				request.getRequestDispatcher("/WEB-INF/html/recipe/recipeError.html").forward(request, response);
			}
		}else {
			result = new RecipeService().insertRecipeLike(recipeNo,userId);
			if(result>0) {
				response.sendRedirect("/recipe/detail?recipeNo="+recipeNo);
			}else {
				request.getRequestDispatcher("/WEB-INF/html/recipe/recipeError.html").forward(request, response);
			}
		}
		
	}

}
