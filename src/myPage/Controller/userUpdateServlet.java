package myPage.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/update")
public class userUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public userUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	String userId = request.getParameter("userId");
	String userEmail = request.getParameter("userEmail");
	String userPhone = request.getParameter("userPhone");
	
	request.setAttribute("userId", userId);
	request.setAttribute("userEmail", userEmail);
	request.setAttribute("userPhone", userPhone);
	request.getRequestDispatcher("/WEB-INF/html/myPage/myInfoUpdate.jsp").forward(request, response);
	}

}
