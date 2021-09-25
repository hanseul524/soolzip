package recipe.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
		request.setCharacterEncoding("UTF-8");

		
		
		// 제조과정 파일 
		// 1. upload 폴더에 실제 파일을 저장하는 작업
		// MultpartRequest 객체 생성을 하면 파일 저장됨 -> 편리함
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileLimit = 5*1024*1024;//5MB , M -> 10^6
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileLimit, encType, new DefaultFileRenamePolicy());
		//upload 폴더에 저장한 파일에 대한 정보
		String mFileName = multi.getFilesystemName("mainFile");
		File mUploadFile = multi.getFile("mainFile");
		String mfilePath = mUploadFile.getPath();
		long mfileSize = mUploadFile.length();
		
		//레시피 기본 정보 담기
		// 세션유저아이디 필요함 같이 넘겨줘야함 등록을 하려면 유저 아이디 필요
//		HttpSession session = request.getSession();
//		String userId = (String)session.getAttribute("userId");
		String recipeTitle = multi.getParameter("recipe-title");
		String recipeContents = multi.getParameter("recipe-contents");
		String recipeMainDrink = multi.getParameter("recipe-mainDrink");
		int recipeAlcohol= Integer.parseInt(multi.getParameter("recipe-alcohol"));
		String recipeTag=multi.getParameter("recipe-tag");
		Recipe recipe = new Recipe(recipeContents,recipeTitle,recipeMainDrink,recipeAlcohol,recipeTag);
		
		//재료 항목 리스트
		String[] ingredientNames = multi.getParameter("ingredientName").split(",");
		String[] ingredientGrams = multi.getParameter("ingredientGram").split(",");
		List<RecipeIngredient> ingredList = new ArrayList();
		
		for(int i = 0; i < ingredientNames.length; i++) {
			RecipeIngredient ingred = new RecipeIngredient();
			ingred.setIngredientName(ingredientNames[i]);
			ingred.setIngredientGram(ingredientGrams[i]);
			ingredList.add(ingred);
		}
		
		// 제조과정 리스트
		List<RecipeFile> rFileList = new ArrayList();
		
		
		RecipeFile recipeFile= new RecipeFile();
		recipeFile.setFileName(mFileName);
		recipeFile.setFilePath(mfilePath);
		recipeFile.setFileSize(mfileSize);
		recipeFile.setFileOrder(1);
		
		System.out.println(mFileName);
		System.out.println(mfilePath);
		
		rFileList.add(recipeFile);
		
		
//		다중파일 업로드 다시 한번확인
		Enumeration files = multi.getFileNames();
		int i = 2;
		while(files.hasMoreElements()){
			recipeFile = new RecipeFile();
		    String file = (String) files.nextElement();
		    String file_name = multi.getFilesystemName(file);
		    String file_path = multi.getFile(file).getPath();
		    long file_size = multi.getFile(file).length();
		    recipeFile.setFileName(file_name);
		    recipeFile.setFilePath(file_path);
		    recipeFile.setFileSize(file_size);
		    recipeFile.setFileOrder(i++);
		    rFileList.add(recipeFile);
		    System.out.println("@@@@@@@@@@@@@: "+ file_name); 
		    System.out.println("@@@@@@@@@@@@@: "+ file_path); 
		}
		
		
		
		int result = new RecipeService().registerRecipe(recipe,ingredList,rFileList);
		
		if(result > Integer.MIN_VALUE) {
			System.out.println("등록성공");
		}else {
			response.sendRedirect("/html/recipe/recipeError.html");
		}
	}

}
