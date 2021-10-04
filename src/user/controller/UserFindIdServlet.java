package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserFindIdServlet
 */
@WebServlet("/user/findid")
public class UserFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user-name");
		String userEmail = request.getParameter("user-email");
		User userOne = new UserService().findUserId(userName, userEmail);
		
		if(userOne != null) {
			request.setAttribute("userOne", userOne);
			System.out.println( "아이디 찾기 성공");
			
			
			request.getRequestDispatcher("/WEB-INF/html/userinfo/findinfo.jsp").forward(request, response);
//			response.sendRedirect("/html/userinfo/findinfo.jsp");
		}else {
			System.out.println("아이디찾기 실패");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('입력하신 정보가 일치하지 않습니다.')");
			out.println("history.go(-1);");
			out.println("</script>");
		}
	}	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
