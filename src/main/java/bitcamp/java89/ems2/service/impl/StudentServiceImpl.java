package bitcamp.java89.ems2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;
  
  public List<Student> getList() throws Exception {
    return studentDao.getList();
  }
  
  public Student getDetail(int no) throws Exception {
    return studentDao.getOne(no);
  }
  
  // 업무에 관련된 로직을 관리한다.
  public int add(Student student) throws Exception {
    if (studentDao.count(student.getEmail()) > 0) {
      throw new Exception("같은 학생의 이메일이 존재합니다. 등록을 취소합니다.");
    }
    if (memberDao.count(student.getEmail()) == 0) { // 강사나 매니저로 등록되지 않았다면,
      memberDao.insert(student);
      
    } else { // 강사나 매니저로 이미 등록된 사용자라면 기존의 회원번호를 사용한다.
      Member member = memberDao.getOne(student.getEmail());
      student.setMemberNo(member.getMemberNo());
    }
    return studentDao.insert(student);
  }
  
  public int delete(int no) throws Exception {
    if (studentDao.countByNo(no) == 0) {
      throw new Exception("학생을 찾지 못했습니다.");
    }
    int count = studentDao.delete(no);

    if (managerDao.countByNo(no) == 0 && (teacherDao.countByNo(no) == 0)) { // 학생이면서 매니저나 강사인 경우 멤버정보는 보관한다.
      memberDao.delete(no);
    }
    return count;
  }
  
  public int update(Student student) throws Exception {
    if (studentDao.countByNo(student.getMemberNo()) == 0) {
      throw new Exception("학생을 찾지 못했습니다.");
    }
    memberDao.update(student);
    return studentDao.update(student);
  }
  
  
  
  
}
