package cn.koala.system.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * UserDetails实现类
 *
 * @author Houtaroy
 */
@Getter
public class UserDetailsImpl extends User {
  private final Long id;

  public UserDetailsImpl(Long id, String username, String password, boolean enabled,
                         Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, true, true, true, authorities);
    this.id = id;
  }
}
