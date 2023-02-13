package cn.koala.security;

import cn.koala.security.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
    return repository.findByUsername(username).orElse(null);
    // Optional<UserDetailsImpl> user = repository.findByUsername(username);
    // if (user.isEmpty()) {
    //   throw new UsernameNotFoundException("未找到指定用户");
    // }
    // return new UserDetailsImpl(
    //   user.get().getId(),
    //   user.get().getUsername(),
    //   user.get().getPassword(),
    //   user.get().getIsEnable() == YesNo.YES,
    //   new ArrayList<>()
    // );
  }
}
