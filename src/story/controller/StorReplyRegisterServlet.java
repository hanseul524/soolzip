package story.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import story.model.service.StoryService;
import user.model.vo.User;

/**
 * Servlet implementation class StorReplyRegisterServlet
 */
@WebServlet("/storReply/wirte")
public class StorReplyRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StorReplyRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글번역
		request.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user")!=null)
			user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		int storyNo = Integer.parseInt(request.getParameter("storyNo"));
		String replyContents = request.getParameter("replyContents");
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp uploadTime = Timestamp.valueOf(formats.format(Calendar.getInstance().getTimeInMillis()));
		int result = new StoryService().registerStoryReply(userId,storyNo,replyContents,uploadTime);
				
		if(result > 0) {
			response.sendRedirect("/story/detail?storyNo="+storyNo);
		}else {
			request.getRequestDispatcher("/WEB-INF/html/story/storyError.html");
		}
	}

}
