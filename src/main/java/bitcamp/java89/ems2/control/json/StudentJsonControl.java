package bitcamp.java89.ems2.control.json;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.service.StudentService;
import bitcamp.java89.ems2.util.MultipartUtil;

//@Controller
@RestController
public class StudentJsonControl {  // 페이지 컨트롤러!!
  // data를 직접 사용하지 않고 service를 경유한다.
  @Autowired ServletContext sc;
  @Autowired StudentService studentService;
  
  @RequestMapping("/student/form")
  public String form(Model model) {
    model.addAttribute("title", "학생 입력폼");
    model.addAttribute("contentPage", "student/form.jsp");
    return "main";
  }
  
  @RequestMapping("/student/list")
  public AjaxResult list() throws Exception {
    List<Student> list = studentService.getList();
    return new AjaxResult(AjaxResult.SUCCESS, list);
  }
  
  @RequestMapping("/student/detail")
  public AjaxResult detail(int memberNo) throws Exception { // jsp로 보내지 않는다. model 필요없다
    Student student = studentService.getDetail(memberNo);
    
    if (student == null) {
      return new AjaxResult(AjaxResult.FAIL,"해당 학생이 없습니다.");
    }
    return new AjaxResult(AjaxResult.SUCCESS, student);
  }
  
  @RequestMapping("/student/add")
  public AjaxResult add(Student student, MultipartFile photo) throws Exception {
    // 페이지 컨트롤러는 입력 파라미터 값을 가공하여 모델 객체에게 전달하는 일을 한다.
    if (photo != null && photo.getSize() > 0) { 
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename); // 새이름으로 지정하고 설정
    }
    
    studentService.add(student);
    
    return new AjaxResult(AjaxResult.SUCCESS, "등록 성공입니다.");
  }
  
  @RequestMapping("/student/delete")
  public AjaxResult delete(int memberNo, HttpServletRequest request) throws Exception {
    int count = studentService.delete(memberNo);
    if (count == 0) {
      return new AjaxResult(AjaxResult.FAIL, "해당 번호의 학생이 없습니다.");
    }
    return new AjaxResult(AjaxResult.SUCCESS, "삭제 성공입니다.");
  }
  
  @RequestMapping("/student/update")
  public AjaxResult update(Student student, MultipartFile photo) throws Exception {
    
    if (photo != null && photo.getSize() > 0) { // 파일이 업로드 되었다면,
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename);
    }
    int count = studentService.update(student);
    
    if (count == 0) {
      return new AjaxResult(AjaxResult.FAIL, "해당 번호의 학생이 없습니다.");
    }
    
    return new AjaxResult(AjaxResult.SUCCESS, "변경 성공입니다.");
  }
  
}