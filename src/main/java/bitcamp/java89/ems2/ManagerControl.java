package bitcamp.java89.ems2;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.annotation.RequestMapping;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller
public class ManagerControl {
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;
  
  @RequestMapping("/manager/add.do")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String,String> dataMap = MultipartUtil.parse(request);
    
    Manager manager = new Manager();
    manager.setEmail(dataMap.get("email"));
    manager.setPassword(dataMap.get("password"));
    manager.setName(dataMap.get("name"));
    manager.setTel(dataMap.get("tel"));
    manager.setPosition(dataMap.get("posi"));
    manager.setFax(dataMap.get("fax"));
    manager.setPhotoPath(dataMap.get("photoPath") == null ? "default.png" : dataMap.get("photoPath"));
    
    if (managerDao.exist(manager.getEmail())) {
      throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
    }
    if (!memberDao.exist(manager.getEmail())) { // 강사나 매니저로 등록되지 않았다면,
      memberDao.insert(manager);
      
    } else { // 강사나 매니저로 이미 등록된 사용자라면 기존의 회원번호를 사용한다.
      Member member = memberDao.getOne(manager.getEmail());
      manager.setMemberNo(member.getMemberNo());
    }
    managerDao.insert(manager);
    response.sendRedirect("list");
    return "redirect:list.do";
  }
  
  @RequestMapping("/manager/list.do")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<Manager> list = managerDao.getList();
    request.setAttribute("managers", list);
    request.setAttribute("title", "매니저관리-목록");
    request.setAttribute("contentPage", "/manager/list.jsp");
    return "/main.jsp";
  }
  
  @RequestMapping("/manager/detail.do")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    
    Manager manager = managerDao.getOne(memberNo);
    
    if (manager == null) {
      throw new Exception("해당 매니저가 없습니다.");
    }
    request.setAttribute("manager", manager);
    request.setAttribute("title", "매니저관리-상세정보");
    request.setAttribute("contentPage", "/manager/detail.jsp");
    return "/main.jsp";
  }
  
  @RequestMapping("/manager/update.do")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String,String> dataMap = MultipartUtil.parse(request);
    
    Manager manager = new Manager();
    manager.setMemberNo(Integer.parseInt(dataMap.get("memberNo")));
    manager.setEmail(dataMap.get("email"));
    manager.setPassword(dataMap.get("password"));
    manager.setName(dataMap.get("name"));
    manager.setTel(dataMap.get("tel"));
    manager.setPosition(dataMap.get("posi"));
    manager.setFax(dataMap.get("fax"));
    manager.setPhotoPath(dataMap.get("photoPath") == null ? "default.png" : dataMap.get("photoPath"));
  
    if (!managerDao.exist(manager.getMemberNo())) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }
    memberDao.update(manager);
    managerDao.update(manager);
    return "redirect:list.do";
  }
  
  @RequestMapping("/manager/delete.do")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    
    if (!managerDao.exist(memberNo)) {
      throw new Exception("학생을 찾지 못했습니다.");
    }
    managerDao.delete(memberNo);
    
    if (!studentDao.exist(memberNo) && !teacherDao.exist(memberNo)) {
      memberDao.delete(memberNo);
    }
    return "redirect:list.do";
  }
}