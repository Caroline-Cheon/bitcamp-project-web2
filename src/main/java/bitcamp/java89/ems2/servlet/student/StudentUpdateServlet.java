package bitcamp.java89.ems2.servlet.student;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      Map<String,String> dataMap = MultipartUtil.parse(request);
      
      Student student = new Student();
      student.setMemberNo(Integer.parseInt(dataMap.get("memberNo")));
      student.setEmail(dataMap.get("email"));
      student.setPassword(dataMap.get("password"));
      student.setName(dataMap.get("name"));
      student.setTel(dataMap.get("tel"));
      student.setWorking(Boolean.parseBoolean(dataMap.get("working")));
      student.setGrade(dataMap.get("grade"));
      student.setSchoolName(dataMap.get("schoolName"));
      student.setPhotoPath(dataMap.get("photoPath"));
      
      StudentDao studentDao = (StudentDao)ContextLoaderListener.applicationContext.getBean("studentDao");
      
      if (!studentDao.exist(student.getMemberNo())) {
        throw new Exception("학생을 찾지 못했습니다.");
      }
      
      MemberDao memberDao = (MemberDao)ContextLoaderListener.applicationContext.getBean("memberDao");
      memberDao.update(student);
      studentDao.update(student);
      
      response.sendRedirect("list");
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      
      RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
      rd.forward(request, response); 
      return;
    }
  }
}
