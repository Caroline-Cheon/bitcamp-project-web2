package bitcamp.java89.ems2.servlet.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.MemberMysqlDao;
import bitcamp.java89.ems2.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems2.domain.Teacher;

@WebServlet("/teacher/update")
public class TeacherUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    
    try {
      request.setCharacterEncoding("UTF-8");
      
      Teacher teacher = new Teacher();
      teacher.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
      teacher.setEmail(request.getParameter("email"));
      teacher.setPassword(request.getParameter("password"));
      teacher.setName(request.getParameter("name"));
      teacher.setTel(request.getParameter("tel"));
      teacher.setHomepage(request.getParameter("homepage"));
      teacher.setFacebook(request.getParameter("facebook"));
      teacher.setTwitter(request.getParameter("twitter"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>강사관리-등록</title>");
      out.println("</head>");
      out.println("<body>");
      
   // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response); 
      
      out.println("<h1>등록 결과</h1>");
      
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
    
      if (!teacherDao.exist(teacher.getMemberNo())) { // 강사나 매니저로 등록되지 않았다면,
        throw new Exception("사용자를 찾지 못했습니다.");
        
      } 
      MemberMysqlDao memberDao = MemberMysqlDao.getInstance();
      memberDao.update(teacher);
      teacherDao.update(teacher);
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