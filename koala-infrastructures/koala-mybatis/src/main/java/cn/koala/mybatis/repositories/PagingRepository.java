package cn.koala.mybatis.repositories;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 分页仓库接口
 *
 * @author Houtaroy
 */
public interface PagingRepository<T> {
  /**
   * 分页查询
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 数据列表
   */
  List<T> findAll(@Param("parameters") Map<String, Object> parameters, Pageable pageable);
}
