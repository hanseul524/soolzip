package recipe.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.model.service.RecipeService;
import user.model.vo.User;

/**
 * Servlet implementation class RecipeReplyWriteServlet
 */
@WebServlet("/recipeReply/write")
public class RecipeReplyWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeReplyWriteServlet() {
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
		HttpSession session = request.getSession();
		User user = new User();
		user = (User)session.getAttribute("user"); 
		String userId = user.getUserId();
		
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		String replyContents = request.getParameter("replyContents");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		int result = new RecipeService().registerRecipeReply(userId,recipeNo,replyContents,uploadTime);
		if(result>0) {
			response.sendRedirect("/recipe/detail?recipeNo="+recipeNo);
		}else {
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeError.html").forward(request,response);
		}
		
		
	}

}
