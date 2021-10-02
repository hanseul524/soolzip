package message.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import message.model.service.MsgService;
import message.model.vo.Message;
import user.model.vo.User;

/**
 * Servlet implementation class ReplyMessageServlet
 */
@WebServlet("/msg/replyMsg")
public class ReplyMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyMessageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int result = 0;
		HttpSession session = request.getSession();
		User user = new User();
		if (session.getAttribute("user") != null)
			user = (User) session.getAttribute("user");
		String userId = user.getUserId();
		String msgGetUser = (String) request.getParameter("msgGetUser");
		String msgName = (String) request.getParameter("msgName");
		String contents = (String) request.getParameter("contents");

		Message msg = new Message();
		msg.setMsgSendUser(userId);
		msg.setMsgGetUser(msgGetUser);
		msg.setMsgName(msgName);
		msg.setMsgContents(contents);

		result = new MsgService().replyMessage(msg);

		if (result > 0) {
			PrintWriter writer = response.getWriter();
			writer.print("<script>");
			writer.print("alert('답장 완료');");
			writer.print("history.go(-2);");
			writer.print("</script>");
			writer.close();
		}
	}

}
