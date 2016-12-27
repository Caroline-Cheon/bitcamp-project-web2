package bitcamp.java89.ems2.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MultipartUtil {
  static int count = 0;
  public static Map<String,String> parse(HttpServletRequest request) throws Exception {
    HashMap<String,String> map = new HashMap<>();
    
    if (!ServletFileUpload.isMultipartContent(request)) {
      throw new Exception("멀티파트 형식이 아닙니다.");
    }
    
    DiskFileItemFactory itemFactory = new DiskFileItemFactory();
    ServletFileUpload uploadParser = new ServletFileUpload(itemFactory);
    List<FileItem> items = uploadParser.parseRequest(request);
    
    for (FileItem item : items) {
      if (item.isFormField()) {
        map.put(item.getFieldName(), item.getString("UTF-8"));
      } else {
        /*
        String filename = System.currentTimeMillis() + "_" + count++;
        ServletContext sc = request.getServletContext();
        File file = new File(sc.getRealPath("/upload/" + filename));
        item.write(file);
        */ // 아래처럼 짧게 표현한다.
        
        if (item.getSize() == 0) {
          continue;
        }
        String filename = System.currentTimeMillis() + "_" + count++;
        // 클라이언트가 보낸 파일은 이미 임시 폴더에 저장되어 있다.
        // 다음 write() 임시 저장된 파일을 지정된 경로의 파일명으로 옮기는 것이다.
        item.write(new File(request.getServletContext().getRealPath("/upload/" + filename)));
        map.put(item.getFieldName(), filename);
      }
    }
    return map;
  }
}
