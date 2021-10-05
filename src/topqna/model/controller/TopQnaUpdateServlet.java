package topqna.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import topqna.model.dao.TopQnaDAO;
import topqna.model.service.TopQnaService;
import topqna.model.vo.TopQna;
import user.model.vo.User;

/**
 * Servlet implementation class TopQnaUpdateServlet
 */
@WebServlet("/top/qna/update")
public class TopQnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopQnaUpdateServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//HttpSession session = request.getSession();
		//String userId = request.getSession().getAttribute("userId").toString();
		HttpSession session = request.getSession();
	      User user = new User();
	      if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
	      String userId = user.getUserId();
		int topNO = Integer.parseInt(request.getParameter("topQnaNo"));
		String topTitle = request.getParameter("topQnaTitle");
		String topContent = request.getParameter("topQnaContents");

		TopQna topQna = new TopQna();
		topQna.setTopQnaNo(topNO);
		topQna.setTopQnaTitle(topTitle);
		topQna.setTopQnaContent(topContent);
		
		int result = new TopQnaService().updateTopQna(topQna);
		System.out.println(topTitle);
		System.out.println(topContent);

		if(result>0) {

			PrintWriter writer = response.getWriter(); 
			writer.print("<script>");
			writer.print("alert('수정완료');");
			writer.print("window.location = '/service/center';");
			writer.print("self.close();");
			writer.print("</script>");
			writer.close();
			
			
			//response.sendRedirect("/service/center");
			
		}else {
			request.getRequestDispatcher("/WEB-INF/html/qna/qnaError.html").forward(request, response);

		}
		
		
		
		
	}

}
