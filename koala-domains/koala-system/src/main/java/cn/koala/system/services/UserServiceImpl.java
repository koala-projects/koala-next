package cn.koala.system.services;

import cn.koala.mybatis.services.BaseSmartService;
import cn.koala.system.User;
import cn.koala.system.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * 用户服务实现类
 *
 * @author Houtaroy
 */
public class UserServiceImpl extends BaseSmartService<User, Long, Long> implements UserService {

  protected final UserRepository repository;
  protected final PasswordEncoder passwordEncoder;

  /**
   * TODO: 需要增加权限管理中的获取用户id供应器
   *
   * @param repository 字典仓库接口
   */
  public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
    super(repository);
    this.repository = repository;
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
