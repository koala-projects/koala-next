package cn.koala.mybatis;

/**
 * 可禁用模型
 *
 * @author Houtaroy
 */
public interface EnableModel {
  /**
   * 获取是否启用
   *
   * @return 是否启用
   */
  YesNo getIsEnable();

  /**
   * 设置是否启动
   *
   * @param isEnable 是否启用
   */
  void setIsEnable(YesNo isEnable);
}
