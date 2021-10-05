package story.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import story.model.service.StoryService;
import story.model.vo.Story;
import user.model.vo.User;

/**
 * Servlet implementation class StoryDetailServlet
 */
@WebServlet("/story/detail")
public class StoryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 번역
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		User user = new User();
		if(session.getAttribute("user")!=null)
			user = (User)session.getAttribute("user");
		String userId = user.getUserId();
	
		int storyNo = Integer.parseInt(request.getParameter("storyNo"));
		//스토리 정보
		Story storyOne = new StoryService().pintOneStroy(storyNo,userId);
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@"+storyOne.toString());
		
		if(storyOne !=null) {
			request.setAttribute("storyOne", storyOne);
			request.getRequestDispatcher("/WEB-INF/html/story/storyDetail.jsp").forward(request,response);
			int viewCount = new StoryService().StoryViewCount(storyNo);
			if(viewCount>0) {
				System.out.println("조회수 1업");
			}else {
				System.out.println("조회수 x");
			}
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
