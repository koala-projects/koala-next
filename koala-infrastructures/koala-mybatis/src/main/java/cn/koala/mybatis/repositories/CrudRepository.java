package cn.koala.mybatis.repositories;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 增删改查仓库接口
 *
 * @author Houtaroy
 */
public interface CrudRepository<T, ID> {
  /**
   * 查询全部
   *
   * @param parameters 查询参数
   * @return 数据列表
   */
  List<T> findAll(@Param("parameters") Map<String, Object> parameters);

  /**
   * 根据id查询
   *
   * @param id id
   * @return 数据实体
   */
  Optional<T> findById(ID id);

  /**
   * 新增
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> int add(S entity);

  /**
   * 更新
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> int update(S entity);

  /**
   * 删除
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> int delete(S entity);
}
