package bitcamp.java89.ems2.servlet.student;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    try {
      Map<String,String> dataMap = MultipartUtil.parse(request);
      
      Student student = new Student();
      student.setEmail(dataMap.get("email"));
      student.setPassword(dataMap.get("password"));
      student.setName(dataMap.get("name"));
      student.setTel(dataMap.get("tel"));
      student.setWorking(Boolean.parseBoolean(dataMap.get("working")));
      student.setGrade(dataMap.get("grade"));
      student.setSchoolName(dataMap.get("schoolName"));
      student.setPhotoPath(dataMap.get("photoPath"));
      
      StudentDao studentDao = (StudentDao)ContextLoaderListener.applicationContext.getBean("studentDao");
    
      if (studentDao.exist(student.getEmail())) {
        throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
      }
      
      MemberDao memberDao = (MemberDao)ContextLoaderListener.applicationContext.getBean("memberDao");
      
      if (!memberDao.exist(student.getEmail())) { // 강사나 매니저로 등록되지 않았다면,
        memberDao.insert(student);
        
      } else { // 강사나 매니저로 이미 등록된 사용자라면 기존의 회원번호를 사용한다.
        Member member = memberDao.getOne(student.getEmail());
        student.setMemberNo(member.getMemberNo());
      }
      
      studentDao.insert(student);
      
      response.sendRedirect("list");
      
    } catch (Exception e) {
      // 오류 정보를 ServletRequest에 담는다.
      request.setAttribute("error", e);
      
      RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
      rd.forward(request, response); 
      return;
    }
    
  }
}