package recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import recipe.model.service.RecipeService;
import recipe.model.vo.Recipe;
import recipe.model.vo.RecipeFile;
import recipe.model.vo.RecipeIngredient;
import recipe.model.vo.RecipeMakeProcess;

/**
 * Servlet implementation class RecipeModifyServlet
 */
@WebServlet("/recipe/modify")
public class RecipeModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		
		Recipe recipeOne = new RecipeService().printOneRecipe(userId,recipeNo);
		// 레시피1에 대한 재료 여러개
		List<RecipeIngredient> iList = new RecipeService().printOneRecipeIngr(recipeNo);
		// 레시피2에 대한 제조과정 여러개 
		List<RecipeMakeProcess> mList = new RecipeService().printOneRecipeMkProcess(recipeNo); 
		
		
		if(recipeOne !=null) {	
			request.setAttribute("iList", iList);
			request.setAttribute("mList", mList);
			request.setAttribute("recipeOne", recipeOne);
			request.getRequestDispatcher("/html/recipe/recipeModify.jsp").forward(request,response);
		}else {
			request.getRequestDispatcher("/html/recipe/recipeError.html").forward(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 Id 받아오기
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		request.setCharacterEncoding("UTF-8");
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileLimit = 5*1024*1024;//5MB , M -> 10^6
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileLimit, encType, new DefaultFileRenamePolicy());
	
		//어떤 레시피 인지 확인하기위한 변수 [recipeNo]
		int recipeNo = Integer.parseInt(multi.getParameter("recipeNo"));
		
		// 레시피 테이블에 수정사항 업데이트 하기위한 변수
		int recipeSaveState = Integer.parseInt(multi.getParameter("recipeSaveState"));
		String recipeTitle = multi.getParameter("recipe-title");
		String recipeContents = multi.getParameter("recipe-contents");
		String recipeMainDrink = multi.getParameter("recipe-mainDrink");
		int recipeAlcohol= Integer.parseInt(multi.getParameter("recipe-alcohol"));
		String recipeTag=multi.getParameter("recipe-tag");
		
		//레시피 정보 넣기
		Recipe recipe = new Recipe(recipeContents,recipeTitle,recipeMainDrink,recipeAlcohol,recipeTag);
		recipe.setRecipeNo(recipeNo);
		recipe.setUserId(userId);
		recipe.setRecipeSaveState(recipeSaveState);
		//재료항목 리스트 저장
		String[] ingredientNames = multi.getParameter("ingredientName").split(",");
		String[] ingredientGrams = multi.getParameter("ingredientGram").split(",");
		String[] ingredientNo = multi.getParameterValues("ingredientNo");
		List<RecipeIngredient> ingredList = new ArrayList();
		
		for(int i = 0; i < ingredientNames.length; i++) {
			RecipeIngredient ingred = new RecipeIngredient();
			ingred.setIngredientNo(Integer.parseInt(ingredientNo[i]));
			ingred.setIngredientName(ingredientNames[i]);
			ingred.setIngredientGram(ingredientGrams[i]);
			ingredList.add(ingred);
		}
		for(RecipeIngredient r : ingredList) {
			System.out.println(r.toString());
		}
		
		int result = new RecipeService().modifyRecipe(recipe,ingredList);
		
		if(result>0) {
			response.sendRedirect("/recipe/detail?recipeNo="+recipeNo);
		}else {
			System.out.println("실패");
		}
		
	}

}
