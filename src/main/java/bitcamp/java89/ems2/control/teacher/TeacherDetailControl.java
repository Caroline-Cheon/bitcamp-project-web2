package bitcamp.java89.ems2.control.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Teacher;

@Controller("/teacher/detail.do")
public class TeacherDetailControl implements PageController {
  @Autowired TeacherDao teacherDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    
    Teacher teacher = teacherDao.getOne(memberNo);
    
    if (teacher == null) {
      throw new Exception("해당 강사가 없습니다.");
    }
    
    request.setAttribute("teacher", teacher);
    return "/teacher/detail.jsp";
  }
}