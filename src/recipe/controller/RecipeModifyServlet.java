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
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		
		Recipe recipeOne = new RecipeService().printOneRecipe(userId,recipeNo);
		// 레시피1에 대한 재료 여러개
		List<RecipeIngredient> iList = new RecipeService().printOneRecipeIngr(recipeNo);
		// 레시피2에 대한 제조과정 여러개 
		List<RecipeMakeProcess> mList = new RecipeService().printOneRecipeMkProcess(recipeNo); 
		for(RecipeMakeProcess s : mList) {
			System.out.println(s.toString());
		}
		
		if(recipeOne !=null) {	
			request.setAttribute("iList", iList);
			request.setAttribute("mList", mList);
			request.setAttribute("recipeOne", recipeOne);
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeModify.jsp").forward(request,response);
		}else {
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeError.html").forward(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 Id 받아오기
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		String userId = user.getUserId();
		
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
		
		//재료삭제항목
		String[] rmIngredientIds = multi.getParameter("rmIngredientId").split(",");
		
		List<RecipeIngredient> ingredList = new ArrayList();
		
		System.out.println("legnth : " + ingredientNames.length);
		
		for(int i = 0; i < ingredientNames.length; i++) {
			RecipeIngredient ingred = new RecipeIngredient();
			System.out.println("@@@@@@@@@@@@@@ : "+ multi.getParameter("ingrNo"+(i+1)));
			if(!"".equals(multi.getParameter("ingrNo"+(i+1)))){
				ingred.setIngredientNo(Integer.parseInt(multi.getParameter("ingrNo"+(i+1))));
			}
			ingred.setRecipeNo(recipeNo);
			ingred.setIngredientName(ingredientNames[i]);				
			ingred.setIngredientGram(ingredientGrams[i]);
			ingredList.add(ingred);
		}
		
		//제조과정 리스트 저장
		String [] fileContents = multi.getParameterValues("fileContents");
	
		//제조과정 삭제 항목
		String [] rmMakeIds = multi.getParameter("rmMakeId").split(",");
		
		List<RecipeMakeProcess> makeList = new ArrayList();
		for(int i = 0; i< fileContents.length; i++) {
			RecipeMakeProcess makeProcess = new RecipeMakeProcess();
			RecipeFile file = new RecipeFile();
			//파일NO 세팅부분
			if(!"".equals(multi.getParameter("fileNo"+(i+1)))){
				file.setFileNo(Integer.parseInt(multi.getParameter("fileNo"+(i+1))));					
			}
			//새롭게 파일등록 시 경로/명/SIZE세팅
			if(multi.getFile("processFile" + (i+1)) != null) {
				file.setFileName(multi.getFile("processFile" + (i+1)).getName());
				file.setFilePath(multi.getFile("processFile" + (i+1)).getPath());
				file.setFileSize(multi.getFile("processFile" + (i+1)).length());
				file.setRegName(userId);
			}
			if(!"".equals(multi.getParameter("makeNo"+(i+1)))) {
				makeProcess.setMakeNo(Integer.parseInt(multi.getParameter("makeNo"+(i+1))));
			}
			makeProcess.setRecipeFile(file);
			makeProcess.setMakeContents(fileContents[i]);
			makeList.add(makeProcess);
 		}

		int result = new RecipeService().modifyRecipe(recipe,ingredList,makeList, rmIngredientIds, rmMakeIds);
		
		if(result>0) {
			response.sendRedirect("/recipe/detail?recipeNo="+recipeNo);
		}else {
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeError.html").forward(request,response);
		}
		
	}

}
