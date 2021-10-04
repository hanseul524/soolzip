package story.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import story.model.service.StoryService;

/**
 * Servlet implementation class StoryReplyDeleteServlet
 */
@WebServlet("/storyReply/delete")
public class StoryReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int storyNo = Integer.parseInt(request.getParameter("storyNo"));
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		int result = new StoryService().removeStoryReplyOne(replyNo);
		
		if(result > 0) {
			response.sendRedirect("/story/detail?storyNo="+storyNo);
			System.out.println("삭제성공");
		}else {
			request.getRequestDispatcher("/WEB-INF/html/story/storyError.html").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
