package bitcamp.java89.ems2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DataSource {
  ArrayList<Connection> conPool = new ArrayList<>();

  // Singleton 패턴 - start
  public DataSource() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
  }

  public Connection getConnection() throws Exception {
    if (conPool.size() == 0) {
      System.out.println("DB conncetion 생성");
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", 
          "java89", "1111");
    } else {
      return conPool.remove(0);
    }
  }

  public void returnConnection(Connection con) {
    try {
      if (!con.isClosed() && con.isValid(5)) {
        conPool.add(con);
      }
    } catch (Exception e) {}
  }
}






