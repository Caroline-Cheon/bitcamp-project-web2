package bitcamp.java89.ems2.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import bitcamp.java89.ems2.dao.impl.ManagerMysqlDao;
import bitcamp.java89.ems2.dao.impl.MemberMysqlDao;
import bitcamp.java89.ems2.dao.impl.StudentMysqlDao;
import bitcamp.java89.ems2.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems2.util.DataSource;

//@WebListener  <--- 이 예제에서는 애노테이션 대신 web.xml에 리스너 설정 등록
public class ContextLoaderListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      ServletContext sc = sce.getServletContext();
      
      DataSource ds = new DataSource();
      
      MemberMysqlDao memberDao = new MemberMysqlDao();
      memberDao.setDataSource(ds);
      sc.setAttribute("memberDao", memberDao);
      
      ManagerMysqlDao managerDao = new ManagerMysqlDao();
      managerDao.setDataSource(ds);
      sc.setAttribute("managerDao", managerDao);
      
      TeacherMysqlDao teacherDao = new TeacherMysqlDao();
      teacherDao.setDataSource(ds);
      sc.setAttribute("teacherDao", teacherDao);
      
      StudentMysqlDao studentDao = new StudentMysqlDao();
      studentDao.setDataSource(ds);
      sc.setAttribute("studentDao", studentDao);
      
      System.out.println("ContextLoaderListener.init() 실행 완료!");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    
  }

}
