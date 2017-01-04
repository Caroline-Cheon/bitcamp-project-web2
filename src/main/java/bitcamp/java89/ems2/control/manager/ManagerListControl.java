package bitcamp.java89.ems2.control.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.domain.Manager;

@Controller("/manager/list.do")
public class ManagerListControl implements PageController {
  @Autowired
  ManagerDao managerDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<Manager> list = managerDao.getList();
    request.setAttribute("managers", list);
    request.setAttribute("title", "매니저관리-목록");
    request.setAttribute("contentPage", "/manager/list.jsp");
    return "/main.jsp";
  }
}