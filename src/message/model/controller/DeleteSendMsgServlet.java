package message.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.model.service.MsgService;

@WebServlet("/delete/sendMsg")
public class DeleteSendMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteSendMsgServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int msgNo = Integer.parseInt(request.getParameter("msgNo"));
		
		int result =0;
		result = new MsgService().deleteSendMsg(msgNo);
		if(result>0) {
			PrintWriter writer = response.getWriter(); 
			writer.print("<script>");
			writer.print("alert('삭제 완료');");
			writer.print("self.close();");
			writer.print("</script>");
			writer.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
