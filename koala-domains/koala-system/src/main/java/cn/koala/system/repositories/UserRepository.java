package cn.koala.system.repositories;

import cn.koala.mybatis.CrudRepository;
import cn.koala.system.User;

/**
 * 用户存储库类
 *
 * @author Houtaroy
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
