package cn.koala.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * 安全帮助类
 *
 * @author Houtaroy
 */
public abstract class SecurityHelper {
  public static UserDetails getCurrentUserDetails() {
    return (UserDetails) Optional.ofNullable(SecurityContextHolder.getContext())
      .map(SecurityContext::getAuthentication)
      .map(Authentication::getPrincipal)
      .filter(principal -> principal instanceof UserDetails)
      .orElse(null);
  }

  /**
   * 获取当前操作用户ID, 如果不存在则为null
   *
   * @return 当前操作用户ID
   */
  public static Long getCurrentUserId() {
    return Optional.ofNullable(getCurrentUserDetails())
      .filter(principal -> principal instanceof UserDetailsImpl)
      .map(principal -> ((UserDetailsImpl) principal).getId())
      .orElse(null);
  }
}
