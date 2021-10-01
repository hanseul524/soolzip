package message.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sendReply
 */
@WebServlet("/send/reply")
public class sendReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public sendReply() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String msgSendUser = request.getParameter("msgSendUser");
		request.setAttribute("msgSendUser", msgSendUser);
		request.getRequestDispatcher("/WEB-INF/html/myPage/replyMsg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
