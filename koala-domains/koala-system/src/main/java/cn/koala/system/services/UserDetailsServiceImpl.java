package cn.koala.system.services;

import cn.koala.mybatis.YesNo;
import cn.koala.system.User;
import cn.koala.system.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * UserDetailsService实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  protected final UserDetailsRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = repository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("未找到指定用户");
    }
    return new org.springframework.security.core.userdetails.User(
      user.get().getUsername(),
      user.get().getPassword(),
      user.get().getIsEnable() == YesNo.YES,
      true,
      true,
      true,
      List.of()
    );
  }
}
