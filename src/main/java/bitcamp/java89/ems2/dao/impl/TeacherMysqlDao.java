package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.util.DataSource;

public class TeacherMysqlDao implements StudentDao {
  DataSource ds;
  
  //Singleton 패턴 - start
  private TeacherMysqlDao() {
    ds = DataSource.getInstance();
  }
 
  static TeacherMysqlDao instance;
 
  public static TeacherMysqlDao getInstance() {
    if (instance == null) {
      instance = new TeacherMysqlDao();
    }
    return instance;
  }
  // end - Singleton 패턴
  
  public ArrayList<Teacher> getList() throws Exception {
    ArrayList<Teacher> list = new ArrayList<>();
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select mno, name, tel, email, hmpg"
            + " from tcher left outer join memb on tcher.tno=memb.mno");
       
        ResultSet rs = stmt.executeQuery(); ){
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Teacher teacher = new Teacher();
        teacher.setMemberNo(rs.getInt("mno"));
        teacher.setName(rs.getString("name"));
        teacher.setTel(rs.getString("tel"));
        teacher.setEmail(rs.getString("email"));
        teacher.setHomepage(rs.getString("hmpg"));
        
        list.add(teacher);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  
  public boolean exist(String email) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select count(*)"
          + " from tcher left outer join memb on tcher.tno=memb.mno"
          + " where email=?"); ) {
      
      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();
      
      rs.next();
      int count = rs.getInt(1);
      rs.close();
      
      if (count > 0) {
        return true;
      } else {
        return false;
      }
    } finally {
      ds.returnConnection(con);
    }
  }
  
  public boolean exist(int memberNo) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select count(*)"
          + " from tcher left outer join memb on tcher.tno=memb.mno"
          + " where tno=?"); ) {
      
      stmt.setInt(1, memberNo);
      ResultSet rs = stmt.executeQuery();
      
      rs.next();
      int count = rs.getInt(1);
      rs.close();
      
      if (count > 0) {
        return true;
      } else {
        return false;
      }
    } finally {
      ds.returnConnection(con);
    }
  }
  
  public void insert(Teacher teacher) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into tcher(tno,hmpg,fcbk,twit) values(?,?,?,?)"); ) {
        
        stmt.setInt(1, teacher.getMemberNo());
        stmt.setString(2, teacher.getHomepage());
        stmt.setString(3, teacher.getFacebook());
        stmt.setString(4, teacher.getTwitter());
        stmt.executeUpdate();
        
      } finally {
        ds.returnConnection(con);
      }
  }
  
  public Teacher getOne(int memberNo) throws Exception {
    Connection con = ds.getConnection();
    Teacher teacher = null;
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select email, name, tel, hmpg, fcbk, twit" 
        + " from tcher left outer join memb on tcher.tno=memb.mno"
        + " where tno=?"); ) {
      
      stmt.setInt(1, memberNo);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        teacher = new Teacher();
        teacher.setMemberNo(memberNo);
        teacher.setEmail(rs.getString("email"));
        teacher.setName(rs.getString("name"));
        teacher.setTel(rs.getString("tel"));
        teacher.setHomepage(rs.getString("hmpg"));
        teacher.setFacebook(rs.getString("fcbk"));
        teacher.setTwitter(rs.getString("twit"));
      }      
      rs.close();
      
    } finally {
      ds.returnConnection(con);
    }
    return teacher;
  }
  
  public void update(Teacher teacher) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "update tcher set"
            +" hmpg=?, fcbk=?, twit=?"
            +" where tno=?"); ) {
      
        stmt.setString(1, teacher.getHomepage());
        stmt.setString(2, teacher.getFacebook());
        stmt.setString(3, teacher.getTwitter());
        stmt.setInt(4, teacher.getMemberNo());
        
        stmt.executeUpdate();
       
    } finally {
      ds.returnConnection(con);
    }
  }

  public void delete(int memberNo) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "delete from tcher where tno=?"); ) {
      
      stmt.setInt(1, memberNo);
      
      stmt.executeUpdate();
      
    } finally {
      ds.returnConnection(con);
    }
  }
}

