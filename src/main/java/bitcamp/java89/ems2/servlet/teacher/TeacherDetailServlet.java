package bitcamp.java89.ems2.servlet.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/teacher/detail")
public class TeacherDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    try {
      int memberNo = Integer.parseInt(request.getParameter("memberNo"));
      
      TeacherDao teacherDao = (TeacherDao)ContextLoaderListener.applicationContext.getBean("teacherDao");
      Teacher teacher = teacherDao.getOne(memberNo);
      
      if (teacher == null) {
        throw new Exception("해당 강사가 없습니다.");
      }
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
      request.setAttribute("teacher", teacher);
      rd.include(request, response); 
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response); 
      return;
    }
    
    
  }
}