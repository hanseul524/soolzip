package myPage.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.vo.User;


/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/myPage/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		///////////////////////////////////////////////////
		String userId = ((User)session.getAttribute("userId")).getUserId();
		
		/////////////////////////////////////////////////////
		request.getRequestDispatcher("/html/myPage/myPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
