package recipe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.model.service.RecipeService;

/**
 * Servlet implementation class RecipeReplyModifyServlet
 */
@WebServlet("/recipeReply/modify")
public class RecipeReplyModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeReplyModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		String replyContents = request.getParameter("replyContents");
		int result = new RecipeService().modifyRecipeReplyOne(replyNo,replyContents);
		if(result>0) {
			response.sendRedirect("/notice/detail?recipeNo="+recipeNo);
		}else {
			request.getRequestDispatcher("/html/recipe/recipeError.html").forward(request, response);
		}
	}

}
