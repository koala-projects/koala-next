package cn.koala.system.services;

import cn.koala.system.Role;
import cn.koala.system.repositories.RoleRepository;

/**
 * 角色服务实现类
 *
 * @author Houtaroy
 */
public class RoleServiceImpl extends BaseSystemService<Role> implements RoleService {
  /**
   * 字典服务实现类构造函数
   *
   * @param repository 字典仓库接口
   */
  public RoleServiceImpl(RoleRepository repository) {
    super(repository);
  }
}
