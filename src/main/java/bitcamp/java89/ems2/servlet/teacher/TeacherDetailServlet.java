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

@WebServlet("/teacher/detail")
public class TeacherDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    
    try {
      int memberNo = Integer.parseInt(request.getParameter("memberNo"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>강사관리-상세정보</title>");
      out.println("</head>");
      out.println("<body>");
      
   // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response); 
      
      out.println("<h1>강사 정보</h1>");
      out.println("<form action='update' method='POST'>");
      
      TeacherDao teacherDao = (TeacherDao)this.getServletContext().getAttribute("teacherDao");
      Teacher teacher = teacherDao.getOne(memberNo);
      
      if (teacher == null) {
        throw new Exception("해당 강사가 없습니다.");
      }
      
      out.println("<table border='1'>");
      out.printf("<tr><th>이메일</th><td><input name='email' type='text' value='%s'></td></tr>\n",
          teacher.getEmail());
      out.printf("<tr><th>암호</th><td><input name='password' type='password'></td></tr>\n");
      out.printf("<tr><th>이름</th><td><input name='name' type='text' value='%s'></td></tr>\n",
          teacher.getName());
      out.printf("<tr><th>전화</th><td><input name='tel' type='text' value='%s'></td></tr>\n",
          teacher.getTel());
      out.printf("<tr><th>홈페이지</th><td><input name='homepage' type='text' value='%s'></td></tr>\n",
          teacher.getHomepage());
      out.printf("<tr><th>페이스북</th><td><input name='facebook' type='text' value='%s'></td></tr>\n",
          teacher.getTwitter());
      out.printf("<tr><th>트위터</th><td><input name='twitter' type='text' value='%s'></td></tr>\n",
          teacher.getTwitter());
      out.println("</table>");
      
      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?memberNo=%s'>삭제</a>\n", teacher.getMemberNo());
      out.printf("<input type='hidden' name='memberNo' value='%d'>\n", teacher.getMemberNo());
      
      out.println(" <a href='list'>목록</a>");
      out.println("</form>");
      
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