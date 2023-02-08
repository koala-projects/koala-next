package cn.koala.system.services;

import cn.koala.mybatis.services.CrudService;
import cn.koala.mybatis.services.PagingService;
import cn.koala.system.User;

/**
 * 用户服务接口
 *
 * @author Houtaroy
 */
public interface UserService extends CrudService<User, Long>, PagingService<User> {
  
}
