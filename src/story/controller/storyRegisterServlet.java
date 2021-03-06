package story.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user") != null) user= (User)session.getAttribute("user");
		String userId = user.getUserId();
    	if(userId == null) {
    		response.setContentType("text/html; charset=utf-8");
    		PrintWriter out = response.getWriter();
    		out.println("<script>");
    		out.println("alert('로그인을 하셔야 합니다.');");
    		out.println("history.back(-1);");
    		out.println("</script>");
    	}else {
    		request.getRequestDispatcher("/WEB-INF/html/story/storyRegister.html").forward(request, response);    		
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = new User();
		if(session.getAttribute("user")!=null)
			user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		
		//첨부 사진 저장
		String uploadFilePath = request.getServletContext().getRealPath("story-upload");
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
		}
		Story story = new Story(storyContents, storyTag);
		story.setStoryFile(storyFile);
		story.setUserId(userId);
		
		
		int result = new StoryService().registerStory(story, storyFile);
		if(result>Integer.MIN_VALUE) {
			response.sendRedirect("/story/list");
		}else {
			response.sendRedirect("/WEB-INF/html/story/storyError.html");
		}
	}
}
