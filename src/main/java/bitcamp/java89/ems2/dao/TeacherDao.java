package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Teacher;

public interface TeacherDao {
  ArrayList<Teacher> getList() throws Exception;
  boolean exist(String email) throws Exception;
  boolean exist(int memberNo) throws Exception;
  void insert(Teacher teacher) throws Exception;
  Teacher getOne(int memberNo) throws Exception;
  void update(Teacher teacher) throws Exception;
  void delete(int memberNo) throws Exception;
}
