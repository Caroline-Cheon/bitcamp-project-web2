package bitcamp.java89.ems2.servlet.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.impl.MemberMysqlDao;
import bitcamp.java89.ems2.dao.impl.StudentMysqlDao;
import bitcamp.java89.ems2.domain.Student;

@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      
      request.setCharacterEncoding("UTF-8");
      
      Student student = new Student();
      student.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
      student.setEmail(request.getParameter("email"));
      student.setPassword(request.getParameter("password"));
      student.setName(request.getParameter("name"));
      student.setTel(request.getParameter("tel"));
      student.setWorking(Boolean.parseBoolean(request.getParameter("working")));
      student.setGrade(request.getParameter("grade"));
      student.setSchoolName(request.getParameter("schoolName"));
      student.setPhotoPath(request.getParameter("photoPath"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>학생관리-변경</title>");
      out.println("</head>");
      out.println("<body>");
      
   // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response); 
      
      out.println("<h1>학생 결과</h1>");
    
      StudentDao studentDao = (StudentDao)this.getServletContext().getAttribute("studentDao");
      
      if (!studentDao.exist(student.getMemberNo())) {
        throw new Exception("사용자를 찾지 못했습니다.");
      }
      
      MemberDao memberDao = (MemberDao)this.getServletContext().getAttribute("memberDao");
      memberDao.update(student);
      studentDao.update(student);
      out.println("<p>변경 하였습니다.</p>");
      
      // HeaderServlet에게 꼬리말 HTML 생성을 요청한다.
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response); 
      
      out.println("</body>");
      out.println("</html>");
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response); 
      return;
    }
    
  }
}
