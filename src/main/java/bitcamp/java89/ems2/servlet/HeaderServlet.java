package bitcamp.java89.ems2.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.domain.Teacher;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    Member member = (Member)request.getSession().getAttribute("member");
    
    RequestDispatcher rd = request.getRequestDispatcher("/header.jsp");
    if (member != null) {
      request.setAttribute("photoPath", this.getPhotoPath(member));
    }
    rd.include(request, response);
  }
  
  private String getPhotoPath(Member member) {
    if (member instanceof Student) {
      return ((Student)member).getPhotoPath();
      
    } else if (member instanceof Manager) {
      return ((Manager)member).getPhotoPath();
      
    } else /*if (member instanceof Teacher)*/ {
      List<Photo> photoList = ((Teacher)member).getPhotoList();
      if (photoList.size() > 0) {
        return photoList.get(0).getFilePath();
      } else {
        return null;
      }
    }
  }
  
  
  
}
