package bitcamp.java89.ems2.servlet.student;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.StudentMysqlDao;
import bitcamp.java89.ems2.domain.Student;

@WebServlet("/student/detail")
public class StudentDetailServlet extends HttpServlet {
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
      out.println("<title>학생관리-상세정보</title>");
      out.println("</head>");
      out.println("<body>");
      
   // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response); 
      
      out.println("<h1>학생 정보</h1>");
      out.println("<form action='update' method='POST'>");
    
      StudentMysqlDao studentDao = StudentMysqlDao.getInstance();
      Student student = studentDao.getOne(memberNo);
      
      if (student == null) {
        throw new Exception("해당 학생이 없습니다.");
      }
      
      out.println("<table border='1'>");
      out.printf("<tr><th>이메일</th><td><input name='email' type='text' value='%s'></td></tr>\n",
          student.getEmail());
      out.printf("<tr><th>암호</th><td><input name='password' type='password'></td></tr>\n");
      out.printf("<tr><th>이름</th><td><input name='name' type='text' value='%s'></td></tr>\n",
          student.getName());
      out.printf("<tr><th>전화</th><td><input name='tel' type='text' value='%s'></td></tr>\n",
          student.getTel());
      out.printf("<tr><th>재직여부</th><td>"
          + "<input type='radio' name='working' value='true' %s>재직중 "
          + "<input type='radio' name='working' value='false' %s>실업/미취업</td></tr>\n",
          (student.isWorking() ? "checked" : ""),
          (student.isWorking() ? "" : "checked"));
      out.println("<tr><th>최종학력</th><td>");
      out.println("<select name='grade'>");
      out.printf("<option value='고졸' %s>고졸</option>\n", "고졸".equals(student.getGrade()) ? "selected" : "");
      out.printf("<option value='전문학사' %s>전문학사</option>\n", "전문학사".equals(student.getGrade()) ? "selected" : "");
      out.printf("<option value='학사' %s>학사</option>\n", "학사".equals(student.getGrade()) ? "selected" : "");
      out.printf("<option value='석사' %s>석사</option>\n", "석사".equals(student.getGrade()) ? "selected" : "");
      out.printf("<option value='박사' %s>박사</option>\n", "박사".equals(student.getGrade()) ? "selected" : "");
      out.println("</select>");
      out.printf("<tr><th>최종학교</th><td><input name='schoolName' type='text' value='%s'></td></tr>\n",
          student.getSchoolName());
      out.printf("<tr><th>사진</th><td><input name='photoPath' type='file'></td></tr>");
      out.println("</table>");
      
      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?memberNo=%s'>삭제</a>\n", student.getMemberNo());
      out.printf("<input type='hidden' name='memberNo' value='%d'>\n", student.getMemberNo());
      
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