package bitcamp.java89.ems2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>애플리케이션 오류</title>");
    out.println("</head>");
    out.println("<body>");
    
    // HeaderServlet에게 머리말 HTML 생성을 요청한다.
    RequestDispatcher rd = request.getRequestDispatcher("/header");
    rd.include(request, response); 
    
    out.println("<h1>오류 내용</h1>");

    // 상세한 오류 내용을 출력한다.
    // ServletRequest 보관소에 "error"라는 이름으로 저장된 오류 정보를 꺼낸다.
    Exception exception = (Exception)request.getAttribute("error");
    
    // 오류 정보가 있다면 출력한다.
    if (exception != null) {
      out.printf("<pre>%s</pre>\n", exception.getMessage());
      out.println("<pre>");
      exception.printStackTrace(out);
      out.println("<pre>");
    }
 // HeaderServlet에게 꼬리말 HTML 생성을 요청한다.
    rd = request.getRequestDispatcher("/footer");
    rd.include(request, response); 
    
    out.println("</body>");
    out.println("</html>");
    
  }
}
