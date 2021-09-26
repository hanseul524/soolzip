package myPage.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;
import user.model.vo.User;


/**
 * Servlet implementation class myInfoUpdateServlet
 */
@WebServlet("/user/modify")
public class myInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public myInfoUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int result = 0;
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		
		User user = new User(userId, userPwd, userEmail, userPhone);
		result = new UserService().modifyUser(user);
		if(result>0) {
			response.sendRedirect("/html/myPage/succes.html");
			session.invalidate();
			response.sendRedirect("/index.jsp");
			//세션파괴
		}else {
			System.out.println("다시해");
		}
	}

}
