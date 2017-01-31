package bitcamp.java89.ems2.control.json;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.service.AuthService;

@RestController
public class AuthJsonControl {
  @Autowired AuthService authService;

  
  @RequestMapping("/auth/login")
  public AjaxResult login(String email, String password, String saveEmail, String userType, 
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
    //http://localhost:8080/bitcamp-project-web2/auth/login.json?email=user01@test.com&password=1111&userType=student&saveEmail=true
    Member member = authService.getmemberInfo(email, password, userType);
    
    if (member == null) { // 멤버정보가 없으면 로그인 실패이다.
      return new AjaxResult(AjaxResult.FAIL, "이메일 또는 암호가 틀리거나, 가입된 회원이 아닙니다.");
    }
      
    session.setAttribute("member", member); // 멤버 정보가 있으면 HttpSession 에 저장한다.
    return new AjaxResult(AjaxResult.SUCCESS, "로그인 성공!");
  }
  
  @RequestMapping("/auth/loginform")
  public String loginForm(Model model) throws Exception {
    model.addAttribute("title", "로그인");
    model.addAttribute("contentPage", "auth/loginform.jsp");
    return "main";
  }
  
  @RequestMapping("/auth/logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:loginform.do";
  }
  
  @RequestMapping("/auth/loginUser")
  public AjaxResult loginUser(HttpSession session) throws Exception {
    Member member = (Member)session.getAttribute("member");
    
    if (member == null) { // 로그인이 되지 않은 상태
      return new AjaxResult(AjaxResult.FAIL, "로그인을 하지 않았습니다.");
    }
    return new AjaxResult(AjaxResult.SUCCESS, member);
  }
}







