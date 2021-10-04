package recipe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.model.service.RecipeService;
import recipe.model.vo.PageData;
import recipe.model.vo.Recipe;

/**
 * Servlet implementation class RecipeListServlet
 */
@WebServlet("/recipe/list")
public class RecipeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 0;
		String getCurrentPage = request.getParameter("currentPage");
		
		if(getCurrentPage == null) {
			currentPage = 1;
		}else {
			currentPage =  Integer.parseInt(getCurrentPage);
		} 
		
		PageData pageData = new RecipeService().printAllRecipe(currentPage);
		List<Recipe> rList = pageData.getRecipeList();
		
		for(Recipe recipe : rList) {
			System.out.println(recipe.toString());
		}
		
		if(!rList.isEmpty()) {
			request.setAttribute("rList", rList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeList.jsp").forward(request, response);	
		}else {
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeError.html").forward(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
