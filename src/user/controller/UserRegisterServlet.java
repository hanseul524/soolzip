package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/user/register")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("user-id");
		String userPwd = request.getParameter("user-pwd");
		String userEmail = request.getParameter("user-email");
		String userName = request.getParameter("user-name");
		String [] phoneStr = request.getParameterValues("user-phone");
		
		String userPhone = "";
		for(int i=0; i<phoneStr.length; i++){
			userPhone += phoneStr[i];
		} //핸드폰번호 배열에 저장
		User user = new User(userId, userPwd, userName, userEmail, userPhone);
		int result = new UserService().registerUser(user);
		
		if(result > 0) {
			response.sendRedirect("/index.jsp");
		}else {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
