package bitcamp.java89.ems2.control;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.service.StudentService;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller
public class StudentControl {  // 페이지 컨트롤러!!
  // data를 직접 사용하지 않고 service를 경유한다.
  @Autowired ServletContext sc;
  @Autowired StudentService studentService;
  
  @RequestMapping("/student/add")
  public String add(Student student, MultipartFile photo) throws Exception {
    // 페이지 컨트롤러는 입력 파라미터 값을 가공하여 모델 객체에게 전달하는 일을 한다.
    if (photo.getSize() > 0) { // 파일이 업로드 되었다면,
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename); // 새이름으로 지정하고 설정
    }
    studentService.add(student); // 실제로 업무를 하는 service에게 보낸다
    
    return "redirect:list.do";
  }
  
  @RequestMapping("/student/list")
  public String list(Model model) throws Exception {
    List<Student> list = studentService.getList();
    model.addAttribute("students", list);
    model.addAttribute("title", "학생관리-목록");
    model.addAttribute("contentPage", "/student/list.jsp");
    return "main";
  }
  
  @RequestMapping("/student/detail")
  public String detail(int memberNo, Model model) throws Exception {
    
    Student student = studentService.getDetail(memberNo);
    
    if (student == null) {
      throw new Exception("해당 학생이 없습니다.");
    }
    // 페이지 컨트롤러는 모델 객체가 리턴한 값을 JSP가 사용할 수 있도록 가공하는 일을 한다.
    model.addAttribute("student", student);
    model.addAttribute("title", "학생관리-상세정보");
    model.addAttribute("contentPage", "/student/detail.jsp");
    return "main";
  }
  
  @RequestMapping("/student/update")
  public String update(Student student, MultipartFile photo) throws Exception {
    
    if (photo.getSize() > 0) { // 파일이 업로드 되었다면,
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename);
    }
    studentService.update(student);
    
    return "redirect:list.do";
  }
  
  @RequestMapping("/student/delete")
  public String delete(int memberNo) throws Exception {
    studentService.delete(memberNo);
    return "redirect:list.do";
  }
}