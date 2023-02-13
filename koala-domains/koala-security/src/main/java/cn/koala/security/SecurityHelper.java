package cn.koala.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全帮助类
 *
 * @author Houtaroy
 */
public abstract class SecurityHelper {
  /**
   * 获取当前操作用户ID, 如果不存在则为null
   *
   * @return 当前操作用户ID
   */
  public static Long getCurrentUserId() {
    Long result = null;
    if (SecurityContextHolder.getContext().getAuthentication() instanceof UserDetailsImpl userDetails) {
      result = userDetails.getId();
    }
    return result;
  }
}
