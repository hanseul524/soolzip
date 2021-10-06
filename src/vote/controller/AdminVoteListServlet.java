package vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.model.vo.Recipe;
import user.model.vo.User;
import vote.model.service.VoteService;
import vote.model.vo.RecipeCandidate;

/**
 * Servlet implementation class AdminVoteListServlet
 */
@WebServlet("/adminVote/list")
public class AdminVoteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminVoteListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		String userId = user.getUserId();
		request.setCharacterEncoding("UTF-8");
		List<Recipe> rList = new VoteService().printCandidateRecipe();
		List<RecipeCandidate> cList = new VoteService().printVoteRecipe(userId);
		
			request.setAttribute("rList", rList);
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("/WEB-INF/html/admin/adminVote.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
