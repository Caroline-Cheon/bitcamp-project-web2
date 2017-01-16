package bitcamp.java89.ems2.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bitcamp.java89.ems2.domain.Member;

//interceptor 이용하기 위해서 주석으로 막음
//@WebFilter("*.do")
public class LoginCheckFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }
  // 로그인 여부를 검사해서 로그인 되지 않으면 어떤 control(**관리)로 들어갈 수 없다.
  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    
    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)resp;
    
    String servletPath = request.getServletPath();
    
    // 로그인, 로그아웃을 요청한 경우 로그인 여부를 검사하지 않는다.
    if (!servletPath.startsWith("/auth/")) {
      // HttpSession꺼내기 - member로 저장되어있음
      HttpSession session = request.getSession();
      Member member = (Member)session.getAttribute("member");
      
      if (member == null) { // contextpath는 웹어플리케이션 경로(web2)
        response.sendRedirect(request.getContextPath() + "/auth/loginform.do"); 
        return;
      } 
    }
    
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {}
  
}
