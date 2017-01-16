package bitcamp.java89.ems2.service;

import org.springframework.stereotype.Service;
import bitcamp.java89.ems2.domain.Member;

@Service
public interface AuthService {
  Member getmemberInfo(String email, String password, String userType) throws Exception;
}
