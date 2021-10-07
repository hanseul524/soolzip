package recipe.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import user.model.vo.User;

/**
 * Servlet implementation class RecipeRegisterServlet
 */
@WebServlet("/recipe/register")
public class RecipeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeRegisterServlet() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		String userId = user.getUserId();
    	if(userId == null) {
    		response.setContentType("text/html; charset=utf-8");
    		PrintWriter out = response.getWriter();
    		out.println("<script>");
    		out.println("alert('레시피 등록은 로그인이 필요합니다.!!!');");
    		out.println("history.back(-1);");
    		out.println("</script>");
    	}else {
    		request.getRequestDispatcher("/WEB-INF/html/recipe/recipeReg.html").forward(request, response);    		
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		request.setCharacterEncoding("UTF-8");
		
		// 1. upload 폴더에 실제 파일을 저장하는 작업
		// MultpartRequest 객체 생성을 하면 파일 저장됨 -> 편리함
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileLimit = 5*1024*1024;//5MB , M -> 10^6
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileLimit, encType, new DefaultFileRenamePolicy());
		//upload 폴더에 저장한 파일에 대한 정보
		
		//레시피 기본 정보 담기
		int recipeSaveState = Integer.parseInt(multi.getParameter("recipeSaveState"));
		String recipeTitle = multi.getParameter("recipe-title");
		String recipeContents = multi.getParameter("recipe-contents");
		String recipeMainDrink = multi.getParameter("recipe-mainDrink");
		int recipeAlcohol= Integer.parseInt(multi.getParameter("recipe-alcohol"));
		String recipeTag=multi.getParameter("recipe-tag");
		
		// 레시피 대표 사진 파일 정보 넣기
		RecipeFile mainFile = new RecipeFile();
		if(multi.getFile("mainFile") != null) {
			mainFile.setFileName(multi.getFile("mainFile").getName());
			mainFile.setFilePath(multi.getFile("mainFile").getPath());
			mainFile.setFileSize(multi.getFile("mainFile").length());
			mainFile.setRegName(userId);
		}
		
		//레시피 정보 넣기
		Recipe recipe = new Recipe(recipeContents,recipeTitle,recipeMainDrink,recipeAlcohol,recipeTag);
		recipe.setRecipeFile(mainFile);
		recipe.setUserId(userId);
		recipe.setRecipeSaveState(recipeSaveState);
		
		
		//재료항목 리스트 저장
		String[] ingredientNames = multi.getParameter("ingredientName").split(",");
		String[] ingredientGrams = multi.getParameter("ingredientGram").split(",");
		List<RecipeIngredient> ingredList = new ArrayList();
		
		for(int i = 0; i < ingredientNames.length; i++) {
			RecipeIngredient ingred = new RecipeIngredient();
			ingred.setIngredientName(ingredientNames[i]);
			ingred.setIngredientGram(ingredientGrams[i]);
			ingredList.add(ingred);
		}
		
		//제조과정 리스트 저장
		String [] fileContents = multi.getParameterValues("fileContents");
		List<RecipeMakeProcess> makeList = new ArrayList();
		
		for(int i = 0; i< fileContents.length; i++) {
			RecipeMakeProcess makeProcess = new RecipeMakeProcess();
			RecipeFile file = new RecipeFile();
			if(multi.getFile("processFile" + (i+1)) != null) {
				file.setFileName(multi.getFile("processFile" + (i+1)).getName());
				file.setFilePath(multi.getFile("processFile" + (i+1)).getPath());
				file.setFileSize(multi.getFile("processFile" + (i+1)).length());
				file.setRegName(userId);
			};
			makeProcess.setRecipeFile(file);
			makeProcess.setMakeContents(fileContents[i]);
			makeList.add(makeProcess);
 		}
		
		int result = new RecipeService().registerRecipe(recipe,ingredList,makeList);
		
		if(result > Integer.MIN_VALUE) {
			response.sendRedirect("/recipe/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeError.html").forward(request,response);
		}

	}

}
