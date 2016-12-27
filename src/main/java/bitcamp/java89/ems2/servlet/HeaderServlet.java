package bitcamp.java89.ems2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
    PrintWriter out = response.getWriter();
    
    out.println("<div id='header' style='background-color:gray; height:40px;"
        + "position:relative;'>");
    
    out.println("<div style='width: 300px; height:38px;"
        + "position:absolute; left:0px; top:0px;'>");
    out.println("<img src='../image/hoho.jpg' "
        + " height='30' style='float:left; margin-top:6px; margin-left:7px;'>"); 
    out.println("<div style='color:white; font-weight:bold; "
        + " margin-left:60px; padding-top:3px; font-family: 고딕체, sans-serif; "
        + " font-size:x-large;'>교육센터관리시스템</div>");
    out.println("</div>");
    
    // 로그인 사용자 정보를 가져온다.
    out.println("<div style='width:200px; height:30px; margin-top:5px; float:right;'>");
    Member member = (Member)request.getSession().getAttribute("member");
    if (member == null) {
      out.println("<a href='../auth/login'>로그인</a>");
    } else {
      // 업데이트부분-사진넣기
      out.printf("<img src='../upload/%s' height='30' style='vertical-align:middle;'>", this.getPhotoPath(member));
      out.printf("<span >%s</span>\n", member.getName());
      out.println("<a href='../auth/logout'>로그아웃</a>");
    }
    out.println("</div>");
    out.println("</div>");
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
