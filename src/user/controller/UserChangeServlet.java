package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;

/**
 * Servlet implementation class UserChangeServlet
 */
@WebServlet("/user/change")
public class UserChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/user/changeList").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] userArr = request.getParameterValues("change"); //체크한 값 배열로 받기
		String users = "";
		for(int i=0; i<userArr.length; i++) {
			users += userArr[i]+ ",";
		}
		int result = new UserService().changeUser(users);
		if(result > 0) {
			System.out.println("권한변경 성공");
			response.sendRedirect("/admin/list");
		}else {
			System.out.println("변경실패");
		}
	}
}
