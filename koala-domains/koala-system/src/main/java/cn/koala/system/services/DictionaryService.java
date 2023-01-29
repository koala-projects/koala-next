package cn.koala.system.services;

import cn.koala.mybatis.services.CrudService;
import cn.koala.mybatis.services.PagingService;
import cn.koala.system.Dictionary;

/**
 * 字典服务接口
 *
 * @author Houtaroy
 */
public interface DictionaryService extends CrudService<Dictionary, Long>, PagingService<Dictionary> {
}
