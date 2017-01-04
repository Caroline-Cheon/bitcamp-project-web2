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
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller("/teacher/update.do")
public class TeacherUpdateControl implements PageController {
 
  @Autowired TeacherDao teacherDao;
  @Autowired MemberDao memberDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String,String> dataMap = MultipartUtil.parse(request);
    
    Teacher teacher = new Teacher();
    teacher.setMemberNo(Integer.parseInt(dataMap.get("memberNo")));
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
    
    teacher.setPhotoList(photoList); 
  
    if (!teacherDao.exist(teacher.getMemberNo())) { // 강사나 매니저로 등록되지 않았다면,
      throw new Exception("사용자를 찾지 못했습니다.");
    } 
    memberDao.update(teacher);
    teacherDao.update(teacher);

    return "redirect:list.do";
  }
}