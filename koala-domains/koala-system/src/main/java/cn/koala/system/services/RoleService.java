package cn.koala.system.services;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;
import cn.koala.system.Role;

/**
 * 角色服务接口
 *
 * @author Houtaroy
 */
public interface RoleService extends CrudService<Role, Long>, PagingService<Role, Long> {
}
