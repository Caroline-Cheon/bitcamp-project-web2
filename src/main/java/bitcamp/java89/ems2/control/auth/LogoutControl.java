package bitcamp.java89.ems2.control.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;

@Controller("/auth/logout.do")
public class LogoutControl implements PageController {

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.getSession().invalidate();
    return "redirect:loginform.do";
  }
}
