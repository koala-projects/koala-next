package cn.koala.system.services;

import cn.koala.mybatis.services.BaseSmartService;
import cn.koala.system.Dictionary;
import cn.koala.system.repositories.DictionaryRepository;

/**
 * 字典服务实现类
 *
 * @author Houtaroy
 */
public class DictionaryServiceImpl extends BaseSmartService<Dictionary, Long, Long> implements DictionaryService {
  /**
   * TODO: 需要增加权限管理中的获取用户id供应器
   *
   * @param repository 字典仓库接口
   */
  public DictionaryServiceImpl(DictionaryRepository repository) {
    super(repository);
  }
}
