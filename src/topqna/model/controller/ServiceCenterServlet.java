package topqna.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qna.model.service.QnaService;
import qna.model.vo.Qna;
import topqna.model.service.TopQnaService;
import topqna.model.vo.TopQna;
import user.model.vo.User;

/**
 * Servlet implementation class TopQnaListServlet
 */
@WebServlet("/service/center")
public class ServiceCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceCenterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		//Qna qnaOne = new QnaService().selectQnaDetail(qnaNo);

		
		
		List<TopQna> tqList = new TopQnaService().selectTopQna();
		//HttpSession session = request.getSession();
		//String userId = request.getSession().getAttribute("userId").toString();
		//String userId = "임진영";
		//String userId = (String)session.getAttribute("userId");
		HttpSession session = request.getSession();
	      User user = new User();
	      if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
	      String userId = user.getUserId();
		List<Qna> qList = new QnaService().selectQna(userId);
		for(Qna a : qList) {
			System.out.println("@@@@@@@@@@@@@@@@@: "+a.toString());
		}
		//System.out.println("tqList===> " + tqList.size());
		if (!tqList.isEmpty()) {
			request.setAttribute("tqList", tqList);
			request.setAttribute("qList", qList);
			request.getRequestDispatcher("/WEB-INF/html/qna/serviceCenter.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/html/qna/qnaError.html").forward(request, response);
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
