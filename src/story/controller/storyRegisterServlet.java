package story.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class storyRegisterServlet
 */
@WebServlet("/story/register")
public class storyRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public storyRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/story/storyRegister.html").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//로그인 완성시 삭제 start
		HttpSession session = request.getSession();
		User user = new User();
		user.setUserId("user01");
		session.setAttribute("user", user);
		//로그인 완성시 삭제 end
		String userId = ((User)session.getAttribute("user")).getUserId();
		
		//session ID 값이 null이면 로그인 페이지로 넘어간다.
		
		if(userId == null) {
			response.sendRedirect("/user/login");
		}
		
		//첨부 사진 저장
		String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileLimit = 5*1024*1024; //5MB , M -> 10^6
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileLimit, encType, new DefaultFileRenamePolicy());
		
		//스토리 내용/태그
		String storyContents = multi.getParameter("story-Contents");
		String storyTag =multi.getParameter("story-Tag");
		
		//스토리 썸네일
		StoryFile storyFile = new StoryFile();
		if(multi.getFile("storyFile")!=null) {
			storyFile.setFileName(multi.getFile("storyFile").getName());
			storyFile.setFilePath(multi.getFile("storyFile").getPath());
			storyFile.setFileSize(multi.getFile("storyFile").length());
			storyFile.setFileName(userId);
		}
		Story story = new Story(storyContents, storyTag);
		story.setStoryFile(storyFile);
		story.setUserId(userId);
		
		int result = new StoryService().registerStory(story);
		if(result>Integer.MIN_VALUE) {
			System.out.println("등록성공");
			response.sendRedirect("/story/list");
		}else {
			response.sendRedirect("/html/story/storyError.html");
		}
	}
}
