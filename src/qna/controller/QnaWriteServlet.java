package qna.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qna.model.service.QnaService;
import qna.model.vo.Qna;
import user.model.vo.User;

/**
 * Servlet implementation class QnaWriteServlet
 */
@WebServlet("/qna/write")
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/qna/qnaWrite.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String title = request.getParameter("qnaTitle");
		String content = request.getParameter("qnaContent");
		
		
		HttpSession session = request.getSession();
	      User user = new User();
	      if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
	      String userId = user.getUserId();
		Qna qna = new Qna();
		qna.setQnaTitle(title);
		qna.setQnaContent(content);
		qna.setUserId(userId);

	
		int result = new QnaService().insertQna(qna);
		if(result>0) {
			PrintWriter writer = response.getWriter(); 
			writer.print("<script>");
			writer.print("alert('문의등록 완료');");
			writer.print("window.location = '/service/center';");
			writer.print("self.close();");
			writer.print("</script>");
			writer.close();
			
			
		}else {
			PrintWriter writer = response.getWriter(); 
			writer.print("<script>");
			writer.print("alert('로그인 후 이용해주세요.');");
			writer.print("window.location = '/index.jsp';");
			writer.print("self.close();");
			writer.print("</script>");
			writer.close();
			
		}

	}

}
