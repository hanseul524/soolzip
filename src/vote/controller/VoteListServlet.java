package vote.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.vo.User;
import vote.model.service.VoteService;
import vote.model.vo.RecipeCandidate;

/**
 * Servlet implementation class VoteListServlet
 */
@WebServlet("/vote/list")
public class VoteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteListServlet() {
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
		
		String votingState= new VoteService().printVotingState();
		
		List<RecipeCandidate> cList = new VoteService().printVoteRecipe(userId);
		
		
		if(!cList.isEmpty()) {
			request.setAttribute("votingState",votingState);
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("/WEB-INF/html/vote/vote2.jsp").forward(request,response);
		}else {
			request.getRequestDispatcher("/WEB-INF/html/vote/vote.jsp").forward(request,response);
			System.out.println("에러");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
