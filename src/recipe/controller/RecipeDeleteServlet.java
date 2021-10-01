package recipe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.model.service.RecipeService;

/**
 * Servlet implementation class RecipeDeleteServlet
 */
@WebServlet("/recipe/remove")
public class RecipeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeDeleteServlet() {
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
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		
		int result = new RecipeService().removeRecipeOne(recipeNo);
		if(result>0) {
			System.out.println("레시피 삭제 성공");
		}else {
			System.out.println("레시피 삭제 실패 ");
		}
	}

}
