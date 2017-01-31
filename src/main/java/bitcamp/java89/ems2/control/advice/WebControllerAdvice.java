package bitcamp.java89.ems2.control.advice;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class WebControllerAdvice {
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  
  @InitBinder
  public void initBinder(WebDataBinder binder) {   // 일관성을 주기위해서 메서드이름을 바인더라고 지정한 것뿐
    System.out.println("MyBindingInitializer.initBinder()...");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); // 문자열을 date 객체로
  }
}
