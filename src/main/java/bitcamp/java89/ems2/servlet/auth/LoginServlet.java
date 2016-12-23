package bitcamp.java89.ems2.servlet.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.MemberDao;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    String email = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("email")) {
          email = cookie.getValue();
          break;
        }
      }
    }
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    
    // HeaderServlet에게 머리말 HTML 생성을 요청한다.
    RequestDispatcher rd = request.getRequestDispatcher("/header");
    rd.include(request, response); 
    
    out.println("<h1>로그인</h1>");
    out.println("<form action='login' method='POST'>");
    out.println("<table border='1'>");
    out.printf("<tr><th>이메일</th><td>"
        + "<input name='email' type='text' placeholder='예)hong@test.com'"
        + " value='%s'></td></tr>\n", email);
    out.println("<tr><th>암호</th><td><input name='password' type='password'></td></tr>");
    out.println("<tr><th></th><td><input name='saveEmail' type='checkbox'> 이메일 저장 </td></tr>");
    out.println("</table>");
    out.println("<button type='submit'>로그인</button>");
    out.println("</form>");
    
    // HeaderServlet에게 꼬리말 HTML 생성을 요청한다.
    rd = request.getRequestDispatcher("/footer");
    rd.include(request, response); 
    
    out.println("</body>");
    out.println("</html>");
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String saveEmail = request.getParameter("saveEmail");
      
      if (saveEmail != null) {
        // 쿠키를 웹 브라우저에게 보낸다.
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 15);
        response.addCookie(cookie);
        
      } else {
        // 기존에 보낸 쿠키를 제거하라고 웹 브라우저에게 응답한다.
        Cookie cookie = new Cookie("email", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
      }
      
      MemberDao memberDao = (MemberDao)this.getServletContext().getAttribute("memberDao");
      
      if (memberDao.exist(email, password)) {
        response.sendRedirect("../student/list");
        return;
      }
      
      response.setHeader("Refresh", "2;url=login");
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>로그인</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>로그인 실패</h1>");
      
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
