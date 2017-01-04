package bitcamp.java89.ems2.control.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;

@Controller("/auth/loginform.do")
public class LoginFormControl implements PageController  {

  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired TeacherDao teacherDao;
  @Autowired ManagerDao managerDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("title", "로그인");
    request.setAttribute("contentPage", "/auth/loginform.jsp");
    return "/main.jsp";
  }
}
