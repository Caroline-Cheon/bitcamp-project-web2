package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Student;

public interface StudentDao {
  ArrayList<Student> getList() throws Exception;
  int count(String email) throws Exception;
  int countByNo(int memberNo) throws Exception;
  int insert(Student student) throws Exception;
  Student getOne(int memberNo) throws Exception;
  int update(Student student) throws Exception;
  int delete(int memberNo) throws Exception;
}
