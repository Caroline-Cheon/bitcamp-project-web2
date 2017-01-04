package bitcamp.java89.ems2.control.teacher;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller("/teacher/add.do")
public class TeacherAddControl implements PageController {
  
  @Autowired MemberDao memberDao;
  @Autowired TeacherDao teacherDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String,String> dataMap = MultipartUtil.parse(request);
    
    Teacher teacher = new Teacher();
    teacher.setEmail(dataMap.get("email"));
    teacher.setPassword(dataMap.get("password"));
    teacher.setName(dataMap.get("name"));
    teacher.setTel(dataMap.get("tel"));
    teacher.setHomepage(dataMap.get("hmpg"));
    teacher.setFacebook(dataMap.get("fcbk"));
    teacher.setTwitter(dataMap.get("twit"));
    
    ArrayList<Photo> photoList = new ArrayList<>(); 
    photoList.add(new Photo(dataMap.get("photoPath1")));
    photoList.add(new Photo(dataMap.get("photoPath2")));
    photoList.add(new Photo(dataMap.get("photoPath3")));
    
    teacher.setPhotoList(photoList);  // 업데이트된 부분
    
    if (teacherDao.exist(teacher.getEmail())) {
      throw new Exception("이메일이 존재합니다. 등록을 취소합니다.");
    }
    
    if (!memberDao.exist(teacher.getEmail())) { // 학생이나 매니저로 등록되지 않았다면,
      memberDao.insert(teacher);
      
    } else { // 학생이나 매니저로 이미 등록된 사용자라면 기존의 회원번호를 사용한다.
      Member member = memberDao.getOne(teacher.getEmail());
      teacher.setMemberNo(member.getMemberNo());
    }
    
    teacherDao.insert(teacher);
    
    return "redirect:list.do";
  }
}