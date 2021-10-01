package story.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import story.model.service.StoryService;
import story.model.vo.PageData;
import story.model.vo.Story;

/**
 * Servlet implementation class StoryListServlet
 */
@WebServlet("/story/list")
public class StoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 0;
		String getCurrentPage = request.getParameter("currentPage");
		
		if(getCurrentPage ==null) {
			currentPage = 1;
			
		}else {
			currentPage = Integer.parseInt(getCurrentPage);
		}
		
		PageData pageData = new StoryService().printAllStory(currentPage);
		List<Story> storyList = pageData.getStoryList();
		
//		for(Story story : storyList) {
//			System.out.println(story.toString());
//		}
		if(!storyList.isEmpty()) {
			request.setAttribute("storyList", storyList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			request.getRequestDispatcher("/html/story/storyList.jsp").forward(request,response);
		}
		else {
			request.getRequestDispatcher("/html/story/storyError.html").forward(request, response);
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
