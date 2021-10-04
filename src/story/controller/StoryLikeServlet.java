package story.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import story.model.service.StoryService;
import user.model.vo.User;

/**
 * Servlet implementation class StoryLikeServlet
 */
@WebServlet("/story/like")
public class StoryLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		User user = new User();
		if(session.getAttribute("user")!=null)
			user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		int storyNo = Integer.parseInt(request.getParameter("storyNo"));
		int likeCheck = Integer.parseInt(request.getParameter("likeCheck"));
		int result = 0;
		if(likeCheck == 1) {
			result=new StoryService().removeStoryLike(storyNo,userId);
			if(result>0) {
				System.out.println("취소");
				response.sendRedirect("/story/detail?storyNo ="+storyNo);
			}else {
				request.getRequestDispatcher("/WEB-INF/html/story/storyError.html").forward(request,response);
			}
		}else {
			result = new StoryService().insertStoryLike(storyNo,userId);
			if(result>0) {
				System.out.println("좋아요");
				response.sendRedirect("/story/detail?storyNo="+storyNo);
			}else {
				request.getRequestDispatcher("/WEB-INF/html/story/storyError.html").forward(request, response);
			}
		}
	}

}
