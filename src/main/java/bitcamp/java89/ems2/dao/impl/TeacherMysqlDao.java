package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.util.DataSource;

public class TeacherMysqlDao implements TeacherDao {
  DataSource ds;
  
  public void setDataSource(DataSource ds) {
    this.ds = ds;
  }
  
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
    Connection con = ds.getConnection(); 
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
    Connection con = ds.getConnection();
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
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into tcher(tno,hmpg,fcbk,twit) values(?,?,?,?)"); ) {
        
        stmt.setInt(1, teacher.getMemberNo());
        stmt.setString(2, teacher.getHomepage());
        stmt.setString(3, teacher.getFacebook());
        stmt.setString(4, teacher.getTwitter());
        stmt.executeUpdate();

        this.insertPhotoList(teacher);        //업데이트
        
      } finally {
        ds.returnConnection(con);
      }
  }
  // 업데이트 된 부분
  public void insertPhotoList(Teacher teacher) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into tch_phot(tno,path) values(?,?)"); ) {
      
      List<Photo> photoList = teacher.getPhotoList();
      for (Photo photo : photoList) {
        if (photo.getFilePath() == null) {
          continue;
        }
        stmt.setInt(1, teacher.getMemberNo());
        stmt.setString(2, photo.getFilePath());
        stmt.executeUpdate();
        
      }
      
    } finally {
      ds.returnConnection(con);
    }
  }
  //업데이트 된 부분
  /*   <테스트용 코드>
  select name, tel, email, hmpg, fcbk, twit, tpno, path
  from tcher
  left outer join memb on tcher.tno=memb.mno
  left outer join tch_phot on tcher.tno=tch_phot.tno
  where tcher.tno=41
  */
  public Teacher getOne(int memberNo) throws Exception {
    Connection con = ds.getConnection();
    Teacher teacher = null;
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select name, tel, email, hmpg, fcbk, twit, tpno, path" 
        + " from tcher "
        + " left outer join memb on tcher.tno=memb.mno"
        + " left outer join tch_phot on tcher.tno=tch_phot.tno"
        + " where tcher.tno=?"); ) {
      
      stmt.setInt(1, memberNo);
      ResultSet rs = stmt.executeQuery();
      
      teacher = null;
      ArrayList<Photo> photoList = new ArrayList<>();
      
      while (rs.next()) {
        if (teacher == null) {  // 처음이면 담는다.
          teacher = new Teacher(); 
          teacher.setMemberNo(memberNo);
          teacher.setEmail(rs.getString("email"));
          teacher.setName(rs.getString("name"));
          teacher.setTel(rs.getString("tel"));
          teacher.setHomepage(rs.getString("hmpg"));
          teacher.setFacebook(rs.getString("fcbk"));
          teacher.setTwitter(rs.getString("twit"));
        }
        if (rs.getString("path") != null) {
          photoList.add(new Photo()
              .setNo(rs.getInt("tpno"))
              .setFilePath(rs.getString("path")));
        }
      }
      rs.close();
      if (teacher != null) {  // 업데이트 (파라미터가 유효한 값만 넘어올 것이라고 가정했었다..)
        teacher.setPhotoList(photoList);
      }
      
      return teacher;
      
    } finally {
      ds.returnConnection(con);
    }
  }
  //업데이트 된 부분
  public void update(Teacher teacher) throws Exception {
    Connection con = ds.getConnection();
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
        
        this.deletePhotoList(teacher);
        this.insertPhotoList(teacher);
       
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
  //업데이트 된 부분
  public void deletePhotoList(Teacher teacher) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "delete from tch_phot where tno=?"); ) {
      
      stmt.setInt(1, teacher.getMemberNo());
      
      stmt.executeUpdate();
      
    } finally {
      ds.returnConnection(con);
    }
  }
}

