package bitcamp.java89.ems2.control.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller("/manager/update.do")
public class ManagerUpdateControl implements PageController {
  
  @Autowired ManagerDao managerDao;
  @Autowired MemberDao memberDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
}
