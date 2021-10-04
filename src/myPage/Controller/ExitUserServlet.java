package myPage.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myPage.service.MyPageService;
import user.model.vo.User;

/**
 * Servlet implementation class ExitUserServlet
 */
@WebServlet("/exit/user")
public class ExitUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ExitUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
        User user = new User();
        if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
        String userId = user.getUserId();
        int result = 0;
        result = new MyPageService().exitUser(userId);
        if(result>0) {
        	PrintWriter writer = response.getWriter();
        	session.invalidate();
			writer.print("<script>");
			writer.print("alert('회원탈퇴 완료되었습니다. 이용해주셔서 감사합니다');");
			writer.print("document.location.href='/main.jsp'");
			writer.print("</script>");
			writer.close();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
