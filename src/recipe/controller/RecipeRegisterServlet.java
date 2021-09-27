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
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/html/recipe/recipeReg.html").forward(request, response);
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 완성 시 삭제 start
		HttpSession session = request.getSession();
		User user = new User();
		user.setUserId("user01");
		session.setAttribute("user", user);
		//로그인 완성 시 삭제 end
		String userId = ((User)session.getAttribute("user")).getUserId();
		
		//session ID값이 null이면 로그인페이지로 넘어간다.
		
		if(userId == null) {
			response.sendRedirect("/user/login");
		}
		
		request.setCharacterEncoding("UTF-8");
		
		// 1. upload 폴더에 실제 파일을 저장하는 작업
		// MultpartRequest 객체 생성을 하면 파일 저장됨 -> 편리함
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileLimit = 5*1024*1024;//5MB , M -> 10^6
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileLimit, encType, new DefaultFileRenamePolicy());
		//upload 폴더에 저장한 파일에 대한 정보
		
		//레시피 기본 정보 담기
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
		Recipe recipe = new Recipe(recipeContents,recipeTitle,recipeMainDrink,recipeAlcohol,recipeTag);
		recipe.setRecipeFile(mainFile);
		recipe.setUserId(userId);
		
		
		
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
		// fileContents[0] 1 2 ~
		
	
		
		
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
			System.out.println("등록성공");
		}else {
			response.sendRedirect("/html/recipe/recipeError.html");
		}

	}

}
