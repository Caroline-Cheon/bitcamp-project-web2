package bitcamp.java89.ems2.control;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;

@Controller
public class AuthControl {
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired TeacherDao teacherDao;
  @Autowired ManagerDao managerDao;

  @RequestMapping("/auth/login")
  public String login(String email, String password, String saveEmail, String userType, 
      HttpServletResponse response, HttpSession session, Model model) throws Exception {
    
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
    HashMap<String,String> paramMap = new HashMap<>();
    paramMap.put("email", email);
    paramMap.put("password", password);
    
    Member member = memberDao.getOneByEmailPassword(paramMap);
    
    if (member != null) {
      Member detailMember = this.getMemberInfo(userType, member.getMemberNo());
      
      if (detailMember != null) { // 로그인 성공인 경우
        session.setAttribute("member", detailMember); // HttpSession 에 저장한다.
        return "redirect:../student/list.do";
      }
    }
    response.setHeader("Refresh", "2;url=loginform.do");
    model.addAttribute("title", "로그인");
    model.addAttribute("contentPage", "/auth/loginfail.jsp");
    return "main";
  }
  
  private Member getMemberInfo(String userType, int memberNo) throws Exception {
    if (userType.equals(Member.STUDENT)) {
      return studentDao.getOne(memberNo);
      
    } else if (userType.equals(Member.TEACHER)) {
      return teacherDao.getOneWithPhoto(memberNo);
      
    } else /*if (userType.equals(Member.MANAGER))*/{
      return managerDao.getOne(memberNo);
    }
  }
  
  @RequestMapping("/auth/loginform")
  public String loginForm(Model model) throws Exception {
    model.addAttribute("title", "로그인");
    model.addAttribute("contentPage", "/auth/loginform.jsp");
    return "main";
  }
  
  @RequestMapping("/auth/logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:loginform.do";
  }
}
