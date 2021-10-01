package myPage.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserPageServlet
 */
@WebServlet("/user/page")
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();// 로그인 안했을때 회원페이지 들어가서 쪽지누르기 방지용
		String userId = request.getParameter("userId");
		
		request.getRequestDispatcher("/WEB-INF/html/myPage/userPage.jsp").forward(request, response);
	}
}
