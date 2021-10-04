package recipe.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class RecipeKategorieServlet
 */
@WebServlet("/recipe/kategorie")
public class RecipeKategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeKategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String recipeMainDrink = request.getParameter("recipeMainDrink");
		int currentPage = 0;
		String getCurrentPage = request.getParameter("currentPage");
		
		if(getCurrentPage == null) {
			currentPage = 1;
		}else {
			currentPage =  Integer.parseInt(getCurrentPage);
		} 
		
		PageData pageData = new RecipeService().printKategorieRecipe(currentPage,recipeMainDrink );
		List<Recipe> rList = pageData.getRecipeList();
		
		if(!rList.isEmpty()) {
			request.setAttribute("rList", rList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeList.jsp").forward(request, response);	
		}else {
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeList.jsp").forward(request,response);
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
