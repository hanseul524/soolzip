package vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vote.model.service.VoteService;

/**
 * Servlet implementation class CandiateRecipeRegisterServlet
 */
@WebServlet("/candiate/register")
public class CandiateRecipeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandiateRecipeRegisterServlet() {
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
		String [] recipeNos = request.getParameterValues("chk");
		int result = new VoteService().registerCandidate(recipeNos);
		if(result > Integer.MIN_VALUE) {
			System.out.println("등록성공");
			response.sendRedirect("/adminVote/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/html/recipe/recipeError.html").forward(request,response);
		}
	}

}
