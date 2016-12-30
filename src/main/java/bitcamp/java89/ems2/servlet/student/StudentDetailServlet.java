package bitcamp.java89.ems2.servlet.student;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/student/detail")
public class StudentDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    try {
      int memberNo = Integer.parseInt(request.getParameter("memberNo"));
      
      StudentDao studentDao = (StudentDao)ContextLoaderListener.applicationContext.getBean("studentDao");
      Student student = studentDao.getOne(memberNo);
      
      if (student == null) {
        throw new Exception("해당 학생이 없습니다.");
      }
      
      response.setContentType("text/html;charset=UTF-8");
      
      RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
      request.setAttribute("student", student);
      rd.include(request, response); 
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response); 
      return;
    }
  }
}