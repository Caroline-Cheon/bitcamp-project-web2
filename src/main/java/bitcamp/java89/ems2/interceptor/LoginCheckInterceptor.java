package bitcamp.java89.ems2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import bitcamp.java89.ems2.domain.Member;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  
    HttpSession session = request.getSession();
    Member member = (Member)session.getAttribute("member");
    
    if (member == null) { // contextpath는 웹어플리케이션 경로(web2)
      response.sendRedirect(request.getContextPath() + "/auth/loginform.do"); 
        return false;
      } 
    // 로그인 되어있으면 true 리턴하고 아무것도 하지 않는다.
    return true;
  }
}
