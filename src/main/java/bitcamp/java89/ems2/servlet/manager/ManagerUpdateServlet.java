package bitcamp.java89.ems2.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/manager/update")
public class ManagerUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    try {
      Map<String,String> dataMap = MultipartUtil.parse(request);
      
      Manager manager = new Manager();
      manager.setMemberNo(Integer.parseInt(dataMap.get("memberNo")));
      manager.setEmail(dataMap.get("email"));
      manager.setPassword(dataMap.get("password"));
      manager.setName(dataMap.get("name"));
      manager.setTel(dataMap.get("tel"));
      manager.setPosition(dataMap.get("posi"));
      manager.setFax(dataMap.get("fax"));
      manager.setPhotoPath(dataMap.get("photoPath"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>매니저관리-변경</title>");
      out.println("</head>");
      out.println("<body>");
      
   // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response); 
      
      out.println("<h1>매니저 결과</h1>");
    
      ManagerDao managerDao = (ManagerDao)this.getServletContext().getAttribute("managerDao");
    
      if (!managerDao.exist(manager.getMemberNo())) {
        throw new Exception("사용자를 찾지 못했습니다.");
      }
      MemberDao memberDao = (MemberDao)this.getServletContext().getAttribute("memberDao");
      memberDao.update(manager);
      managerDao.update(manager);
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
