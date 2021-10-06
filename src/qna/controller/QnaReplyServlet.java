package qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qna.model.service.QnaService;
import user.model.vo.User;

/**
 * Servlet implementation class QnaReplyServlet
 */
@WebServlet("/qna/reply")
public class QnaReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyServlet() {
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
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user")!= null)
			user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		String replyContent = request.getParameter("replyContent");
		int result = new QnaService().registerReply(qnaNo, replyContent, userId);
		// 문의사항 등록 -> userId / 댓글작성 -> userId 똑같은 컬럼이라 오류남
		if(result > 0) {
			response.sendRedirect("/admin/qnalist");
		}else {
			
		}
	}

}
