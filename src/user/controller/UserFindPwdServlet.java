package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserFindPwdServlet
 */
@WebServlet("/user/findpwd")
public class UserFindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFindPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user-id");
		String userEmail = request.getParameter("user-email");
		User userOne = new UserService().findUserPwd(userId, userEmail);
		
		//입력한 id와email 값이 있으면 인증메일 보내기
		if(userOne != null) {
			String host = "smtp.gmail.com"; //서버명
			final String user = "hanseul56524@gmail.com";
			final String password = "baekhyun0506!";
			
			//인증 메일을 보낼 메일 주소 -> 회원이 입력한 메일값 가져오기
			String toEmail = userOne.getUserEmail();
			
			//서버 정보 설정
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", 587);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable","true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			
			//인증번호 랜덤 생성
			StringBuffer temp = new StringBuffer();
			Random rand = new Random();
			for(int i=0; i<16; i++) {
				int rIndex = rand.nextInt(3);
				switch(rIndex) {
				case 0:
					temp.append((char) ((int) (rand.nextInt(26))+97));
					break;
				case 1:
					temp.append((char) ((int) (rand.nextInt(26))+65));
					break;
				case 2:
					temp.append((rand.nextInt(10)));
					break;
				}
			}
			String AuthenticationKey = temp.toString();
			System.out.println(AuthenticationKey);
			
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				} 
			});
			
			//email 전송하기
			MimeMessage msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress(user, "soolzip"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
				
				msg.setSubject("안녕하세요 SOOLZIP 인증 메일입니다.");
				msg.setText("회원님의 임시 비밀번호는  '" + temp + "' 입니다.");
				
				Transport.send(msg);
				System.out.println("이메일 전송");
				
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			HttpSession saveKey = request.getSession();
			saveKey.setAttribute("AuthenticationKey", AuthenticationKey); //세션에 랜덤키 저장해주기
			int result = new UserService().changePwd(userId, AuthenticationKey);
			if (result > 0) {
				System.out.println("비밀번호 변경 성공");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 변경되었습니다. 수정해주세요.')");
				out.println("</script>");
				response.sendRedirect("/index.jsp");
			}else {
				System.out.println("비밀번호 변경 실패");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('입력하신 정보가 일치하지 않습니다.')");
				out.println("history.go(-1);");
				out.println("</script>");
			}
			
		//정보가 일치하지 않으면 실패 메세지 출력 후 다시 입력창으로
		}else {
			System.out.println("실패");
			response.sendRedirect("/WEB-INF/html/userinfo/findinfo.jsp");
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
