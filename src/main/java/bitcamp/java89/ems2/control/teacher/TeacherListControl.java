package bitcamp.java89.ems2.control.teacher;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Teacher;

@Controller("/teacher/list.do")
public class TeacherListControl implements PageController {
  @Autowired
  TeacherDao teacherDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<Teacher> list = teacherDao.getList();
    request.setAttribute("teachers", list);
    request.setAttribute("title", "강사관리-목록");
    request.setAttribute("contentPage", "/teacher/list.jsp");
    return "/main.jsp";
  }
}