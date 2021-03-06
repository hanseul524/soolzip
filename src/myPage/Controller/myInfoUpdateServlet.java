package myPage.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myPage.service.MyPageService;
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
	    User user = new User();
	    if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
	    String userId = user.getUserId();
		String userPwd = request.getParameter("userPwd");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");	
		user = new User(userId, userPwd, userEmail, userPhone);
		
		
		int result = new MyPageService().modifyUser(user);
		
		if(result>0) {
			session.invalidate();
			response.sendRedirect("/html/myPage/succes.html");
			//세션파괴
		}else {
			System.out.println("다시해");
		}
	}

}
