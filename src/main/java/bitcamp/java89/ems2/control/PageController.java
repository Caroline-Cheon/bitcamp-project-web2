/* 역할: 한 화면을 처리할 객체가 지켜야할 규칙
 * 호출자 => DispatcherServlet 이라는 프론트 컨트롤러이다.
 * 피호출자 => 실제 작업을 수행하는 페이지 컨트롤러이다. 
 */
package bitcamp.java89.ems2.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageController {
  String service(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
