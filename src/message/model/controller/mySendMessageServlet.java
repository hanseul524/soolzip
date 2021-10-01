package message.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.model.service.MsgService;
import message.model.vo.Message;

/**
 * Servlet implementation class mySendMessageServlet
 */
@WebServlet("/message/msDetail")
public class mySendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public mySendMessageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int msgNo = 0;
		msgNo = Integer.parseInt(request.getParameter("msgNo"));
		List<Message> sdList = new MsgService().mySendMessageDetail(msgNo);
		if (sdList != null) {
			request.setAttribute("sdList", sdList);
			request.getRequestDispatcher("/html/myPage/msgSendMsgDetail.jsp").forward(request, response);
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
