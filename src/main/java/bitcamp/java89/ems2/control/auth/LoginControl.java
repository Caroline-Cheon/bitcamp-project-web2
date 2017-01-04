package bitcamp.java89.ems2.control.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;

@Controller("/auth/login.do")
public class LoginControl implements PageController  {

  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired TeacherDao teacherDao;
  @Autowired ManagerDao managerDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String saveEmail = request.getParameter("saveEmail");
    
    if (saveEmail != null) {
      // 쿠키를 웹 브라우저에게 보낸다.
      Cookie cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 15);
      response.addCookie(cookie);
      
    } else {
      // 기존에 보낸 쿠키를 제거하라고 웹 브라우저에게 응답한다.
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
    
    Member member = memberDao.getOne(email, password);
    
    if (member != null) {
      String userType = request.getParameter("userType");
      Member detailMember = this.getMemberInfo(userType, member.getMemberNo());
      
      if (detailMember != null) { // 로그인 성공인 경우
        request.getSession().setAttribute("member", detailMember); // HttpSession 에 저장한다.
        return "redirect:../student/list.do";
      }
    }
    response.setHeader("Refresh", "2;url=loginform.do");
    
    return "/auth/loginfail.jsp";
  }
  
  private Member getMemberInfo(String userType, int memberNo) throws Exception {
    if (userType.equals(Member.STUDENT)) {
      return studentDao.getOne(memberNo);
      
    } else if (userType.equals(Member.TEACHER)) {
      return teacherDao.getOne(memberNo);
      
    } else /*if (userType.equals(Member.MANAGER))*/{
      return managerDao.getOne(memberNo);
    }
  }
}
