package recipe.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.model.service.RecipeService;
import recipe.model.vo.Recipe;
import recipe.model.vo.RecipeIngredient;
import recipe.model.vo.RecipeMakeProcess;
import user.model.vo.User;

/**
 * Servlet implementation class RecipeDetailServlet
 */
@WebServlet("/recipe/detail")
public class RecipeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
			
		String userId = user.getUserId();
		
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		//레시피정보
		Recipe recipeOne = new RecipeService().printOneRecipe(userId,recipeNo);
		
		// 레시피1에 대한 재료 여러개
		List<RecipeIngredient> iList = new RecipeService().printOneRecipeIngr(recipeNo);
		// 레시피2에 대한 제조과정 여러개 
		List<RecipeMakeProcess> mList = new RecipeService().printOneRecipeMkProcess(recipeNo); 
		
		
		
		if(recipeOne !=null) {	
			request.setAttribute("iList", iList);
			request.setAttribute("mList", mList);
			request.setAttribute("recipeOne", recipeOne);
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeDetail.jsp").forward(request,response);
			int viewCount = new RecipeService().RecipeViewCount(recipeNo);
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
