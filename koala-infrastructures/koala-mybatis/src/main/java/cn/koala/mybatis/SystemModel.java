package cn.koala.mybatis;

/**
 * 系统模型
 *
 * @author Houtaroy
 */
public interface SystemModel {
  /**
   * 获取是否系统
   *
   * @return 是否系统
   */
  YesNo getIsSystem();

  /**
   * 设置是否启动
   *
   * @param isSystem 是否系统
   */
  void setIsSystem(YesNo isSystem);
}
