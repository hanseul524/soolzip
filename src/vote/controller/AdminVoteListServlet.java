package vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.model.vo.Recipe;
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
		request.setCharacterEncoding("UTF-8");
		List<Recipe> rList = new VoteService().printCandidateRecipe();
		List<RecipeCandidate> cList = new VoteService().printVoteRecipe();
		
		if(!rList.isEmpty()) {
			request.setAttribute("rList", rList);
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("/WEB-INF/html/admin/adminVote.jsp").forward(request,response);
		}else {
			System.out.println("에러");
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
