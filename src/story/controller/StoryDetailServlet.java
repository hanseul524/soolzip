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
		String userId = (String)session.getAttribute("userId");
		
		
		int storyNo = Integer.parseInt(request.getParameter("storyNo"));
		//스토리 정보
		Story storyOne = new StoryService().pintOneStroy(storyNo);
			
		if(storyOne !=null) {
			request.setAttribute("storyOne", storyOne);
			request.getRequestDispatcher("/html/story/storyDetail.jsp").forward(request,response);
		}else {
			request.getRequestDispatcher("/html/story/storyError.html").forward(request,response);
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
