package bitcamp.java89.ems2.dao;

import bitcamp.java89.ems2.domain.Member;

public interface MemberDao {
  boolean exist(String email) throws Exception;
  boolean exist(String email, String password) throws Exception;
  void insert(Member member) throws Exception;
  void update(Member member) throws Exception;
  void delete(int memberNo) throws Exception;
  Member getOne(String email) throws Exception;
}
