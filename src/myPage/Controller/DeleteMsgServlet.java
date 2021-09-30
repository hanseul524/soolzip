package myPage.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myPage.service.MyPageService;

/**
 * Servlet implementation class DeleteMsgServlet
 */
@WebServlet("/delete/msg")
public class DeleteMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int msgNo = Integer.parseInt(request.getParameter("msgNo"));
		
		int result =0;
		result = new MyPageService().deleteMsg(msgNo);
		if(result>0) {
			PrintWriter writer = response.getWriter(); 
			writer.print("<script>");
			writer.print("alert('삭제 완료');");
			writer.print("self.close();");
			writer.print("</script>");
			writer.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
