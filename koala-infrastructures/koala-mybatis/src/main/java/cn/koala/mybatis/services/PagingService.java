package cn.koala.mybatis.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 分页服务接口
 *
 * @author Houtaroy
 */
public interface PagingService<T> {
  /**
   * 分页查询
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 查询结果
   */
  Page<T> findAll(Map<String, Object> parameters, Pageable pageable);
}
