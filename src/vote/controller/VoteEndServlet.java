package vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vote.model.service.VoteService;

/**
 * Servlet implementation class VoteEndServlet
 */
@WebServlet("/vote/end")
public class VoteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteEndServlet() {
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
		
		//기존에 있던 레시피 후보 리스트 삭제
		int delete = new VoteService().removeVoteCandidate();
		String votingState = request.getParameter("voting-state");
		//투표 상태 마감
		int result = new VoteService().voteStateModify(votingState);
		if(result>0) {
			response.sendRedirect("/adminVote/list");
		}else {
			System.out.println("갱신에러");
		}
	}

}
