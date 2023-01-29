package cn.koala.mybatis;

/**
 * 审计模型
 *
 * @param <ID> 用户id类型
 * @author Houtaroy
 */
public interface AuditModel<ID> {
  /**
   * 获取创建用户ID
   *
   * @return 创建用户ID
   */
  ID getCreateUserId();

  /**
   * 设置创建用户ID
   *
   * @param id 创建用户ID
   */
  void setCreateUserId(ID id);

  /**
   * 获取最后更新用户ID
   *
   * @return 创建用户ID
   */
  ID getLastUpdateUserId();

  /**
   * 设置最后更新用户ID
   *
   * @param id 最后更新用户ID
   */
  void setLastUpdateUserId(ID id);

  /**
   * 获取删除用户ID
   *
   * @return 删除用户ID
   */
  ID getDeleteUserId();

  /**
   * 设置删除用户ID
   *
   * @param id 删除用户ID
   */
  void setDeleteUserId(ID id);
}
