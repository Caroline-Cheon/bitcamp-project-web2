package bitcamp.java89.ems2.control.student;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller("/student/update.do")
public class StudentUpdateControl implements PageController  {

  @Autowired StudentDao studentDao;
  @Autowired MemberDao memberDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String,String> dataMap = MultipartUtil.parse(request);
    
    Student student = new Student();
    student.setMemberNo(Integer.parseInt(dataMap.get("memberNo")));
    student.setEmail(dataMap.get("email"));
    student.setPassword(dataMap.get("password"));
    student.setName(dataMap.get("name"));
    student.setTel(dataMap.get("tel"));
    student.setWorking(Boolean.parseBoolean(dataMap.get("working")));
    student.setGrade(dataMap.get("grade"));
    student.setSchoolName(dataMap.get("schoolName"));
    student.setPhotoPath(dataMap.get("photoPath"));
    
    if (!studentDao.exist(student.getMemberNo())) {
      throw new Exception("학생을 찾지 못했습니다.");
    }
    
    memberDao.update(student);
    studentDao.update(student);
    
    return "redirect:list.do";
  }
}
