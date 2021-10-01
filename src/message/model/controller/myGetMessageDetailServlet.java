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

@WebServlet("/message/detail")
public class myGetMessageDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public myGetMessageDetailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		int msgNo = 0;
		msgNo = Integer.parseInt(request.getParameter("msgNo"));
		List<Message> mdList = new MsgService().myGetMessageDetail(msgNo);
		if (mdList != null) {
			request.setAttribute("mdList", mdList);
			

			
			request.getRequestDispatcher("/WEB-INF/html/myPage/msgGetDetail.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
