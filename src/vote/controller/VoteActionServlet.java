package vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.model.service.RecipeService;
import user.model.vo.User;
import vote.model.service.VoteService;

/**
 * Servlet implementation class VoteActionServlet
 */
@WebServlet("/vote/action")
public class VoteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		int candidateNo = Integer.parseInt(request.getParameter("candidateNo"));
		int voteAt = Integer.parseInt(request.getParameter("voteAt"));
		int result = 0;
		
		if(voteAt==0) {
			result = new VoteService().insertVoteAction(candidateNo,userId);				
			if(result>0) {
				System.out.println("투표하기");
				response.sendRedirect("/vote/list");
			}else{
				System.out.println("실패");				
			}
		}else {
			result = new VoteService().removeVoteAction(candidateNo,userId);							
			if(result>0) {
				System.out.println("투표취소");
				response.sendRedirect("/vote/list");
			}else {
				System.out.println("실패");
			}
		}
		
		
		
	}

}
