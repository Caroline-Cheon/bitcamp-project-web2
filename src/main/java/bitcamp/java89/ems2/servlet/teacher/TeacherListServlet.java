package bitcamp.java89.ems2.servlet.teacher;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/teacher/list")
public class TeacherListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    try {
      
      TeacherDao teacherDao = (TeacherDao)ContextLoaderListener.applicationContext.getBean("teacherDao");
      ArrayList<Teacher> list = teacherDao.getList();
      request.setAttribute("teachers", list);
      
      response.setContentType("text/html;charset=UTF-8");
      RequestDispatcher rd = request.getRequestDispatcher("/teacher/list.jsp");
      rd.include(request, response); 

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response); 
      return;
    }
  }
}