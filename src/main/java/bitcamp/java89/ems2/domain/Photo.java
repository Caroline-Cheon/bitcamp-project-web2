package bitcamp.java89.ems2.domain;

import java.io.Serializable;

public class Photo implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int no;
  protected String filePath;
  protected int ownerNo;

  public Photo() {}
  
  public Photo(String filePath) {
    this.filePath = filePath;
  }
  
  public int getNo() {
    return no;
  }
  public Photo setNo(int no) { //업데이트 (연쇄기법을 위함)
    this.no = no;
    return this;
  }
  public String getFilePath() {
    return filePath;
  }
  public Photo setFilePath(String filePath) { //업데이트
    this.filePath = filePath;
    return this;
  }
  public int getOwnerNo() {
    return ownerNo;
  }
  public Photo setOwnerNo(int ownerNo) { //업데이트
    this.ownerNo = ownerNo;
    return this;
  }
  
  
}
