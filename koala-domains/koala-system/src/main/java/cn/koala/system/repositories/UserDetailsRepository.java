package cn.koala.system.repositories;

import cn.koala.system.User;

import java.util.Optional;

/**
 * UserDetails仓库接口
 *
 * @author Houtaroy
 */
public interface UserDetailsRepository {
  /**
   * 根据用户名查询用户
   *
   * @param username 用户名
   * @return 数据实体
   */
  Optional<User> findByUsername(String username);
}
