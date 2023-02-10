package cn.koala.system.services;

import cn.koala.system.User;
import cn.koala.system.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * 用户服务实现类
 *
 * @author Houtaroy
 */
public class UserServiceImpl extends BaseSystemService<User> implements UserService {
  protected final PasswordEncoder passwordEncoder;

  /**
   * 用户服务实现类构造函数
   *
   * @param repository      用户仓库接口
   * @param passwordEncoder 密码加密器
   */
  public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
    super(repository);
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public <S extends User> void save(S entity) {
    if (StringUtils.hasLength(entity.getPassword())) {
      entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    }
    super.save(entity);
  }
}
