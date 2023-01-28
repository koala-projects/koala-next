package cn.koala.mybatis.services;

import java.util.List;
import java.util.Map;

/**
 * 增删改查服务接口
 *
 * @author Houtaroy
 */
public interface CrudService<T, ID> {
  /**
   * 列表查询
   *
   * @param parameters 查询参数
   * @return 查询结果
   */
  List<T> findAll(Map<String, Object> parameters);

  /**
   * 根据id查询
   *
   * @param id id
   * @return 查询结果
   */
  T findById(ID id);

  /**
   * 保存
   *
   * @param entity    数据实体
   * @param <S>数据实体类型
   */
  <S extends T> void save(S entity);

  /**
   * 删除
   *
   * @param entity    实体
   * @param <S>数据实体类型
   */
  <S extends T> void delete(S entity);
}
