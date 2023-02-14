package cn.koala.system.services;

import cn.koala.system.Role;
import cn.koala.system.repositories.RoleRepository;
import org.springframework.util.Assert;

import java.util.List;

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

  @Override
  public List<Long> getPermissionIds(Long id) {
    return ((RoleRepository) repository).findAllPermissionIdById(id);
  }

  @Override
  public void setPermissionIds(Long id, List<Long> permissionIds) {
    Assert.isTrue(nonSystem(load(id)), "系统数据不允许修改");
    ((RoleRepository) repository).updatePermissionIdById(id, permissionIds);
  }
}
