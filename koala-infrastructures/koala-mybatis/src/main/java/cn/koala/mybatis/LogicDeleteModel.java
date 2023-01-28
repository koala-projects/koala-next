package cn.koala.mybatis;

/**
 * 逻辑删除模型
 *
 * @author Houtaroy
 */
public interface LogicDeleteModel {
  /**
   * 获取是否删除
   *
   * @return 是否删除
   */
  YesNo getIsDelete();

  /**
   * 设置是否删除
   *
   * @param isDelete 是否删除
   */
  void setIsDelete(YesNo isDelete);
}
