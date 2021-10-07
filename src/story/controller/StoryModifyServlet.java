package story.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import story.model.service.StoryService;
import story.model.vo.Story;
import story.model.vo.StoryFile;
import user.model.vo.User;

/**
 * Servlet implementation class StoryModifyServlet
 */
@WebServlet("/story/modify")
public class StoryModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		int storyNo = Integer.parseInt(request.getParameter("storyNo"));
		
		Story storyOne = new StoryService().pintOneStroy(storyNo,userId);
		storyOne.setStoryNo(storyNo);
		if(storyOne != null) {
			request.setAttribute("storyOne", storyOne);
			request.getRequestDispatcher("/WEB-INF/html/story/storyModify.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession session = request.getSession();
				User user = new User();
				if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
				String userId = user.getUserId();
				
				request.setCharacterEncoding("UTF-8");
				String uploadFilePath = request.getServletContext().getRealPath("story-upload");
				int uploadFileLimit = 5*1024*1024;//5MB , M -> 10^6
				String encType = "UTF-8";
				MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileLimit, encType, new DefaultFileRenamePolicy());
				
				//스토리 확인하는 변수
				int storyNo = Integer.parseInt(multi.getParameter("storyNo"));
				
				
				String storyContents = multi.getParameter("story-contents");
				String storyTag = multi.getParameter("story-tag");
				
				//스토리 정보 넣기
				Story story = new Story(storyContents,storyTag);
				story.setStoryNo(storyNo);
				story.setUserId(userId);
				
				int fileNo = Integer.parseInt(multi.getParameter("fileNo"));
				StoryFile storyFile = new StoryFile();
				if(multi.getFile("storyFile")!=null) {
					storyFile.setFileNo(fileNo);
					storyFile.setFileName(multi.getFile("storyFile").getName());
					storyFile.setFilePath(multi.getFile("storyFile").getPath());
					storyFile.setFileSize(multi.getFile("storyFile").length());
				}
				story.setStoryFile(storyFile);
				
				System.out.println(storyFile.getFileName());
				
				int result = new StoryService().modifyStory(story,storyFile);
				
				if(result>0) {
					response.sendRedirect("/story/detail?storyNo="+storyNo);
				}else {
					request.getRequestDispatcher("/WEB-INF/html/story/storyError.html").forward(request, response);
				}
	}

}
