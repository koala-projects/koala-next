package cn.koala.system.repositories;

import cn.koala.mybatis.CrudRepository;
import cn.koala.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色仓库接口
 *
 * @author Houtaroy
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
  List<Long> findAllPermissionIdById(Long id);

  void updatePermissionIdById(@Param("id") Long id, @Param("permissionIds") List<Long> permissionIds);
}
