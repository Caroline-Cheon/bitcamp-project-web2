package bitcamp.java89.ems2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    System.out.println("CharacterEncodingFilter.doFilter()");
    // 다음 필터나 서블릿을 실행하기 전에 요청 데이터의 문자 집합을 지정하자!
    // => 이렇게 하면 각각의 서블릿에서 다음 코드를 실행할 필요가 없어짐
    request.setCharacterEncoding("UTF-8");
    
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }

}
