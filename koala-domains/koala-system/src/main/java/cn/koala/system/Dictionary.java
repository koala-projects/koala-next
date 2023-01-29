package cn.koala.system;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.EnableModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.LogicDeleteModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.SystemModel;

/**
 * 字典数据实体接口
 *
 * @author Houtaroy
 */
public interface Dictionary extends IdModel<Long>, SortModel, EnableModel, SystemModel, LogicDeleteModel, AuditModel<Long> {
  /**
   * 获取字典代码
   *
   * @return 字典代码
   */
  String getCode();

  /**
   * 设置字典代码
   *
   * @param code 字典代码
   */
  void setCode(String code);

  /**
   * 获取字典名称
   *
   * @return 字典名称
   */
  String getName();

  /**
   * 设置字典名称
   *
   * @param code 字典名称
   */
  void setName(String code);

  /**
   * 获取字典备注
   *
   * @return 字典备注
   */
  String getRemark();

  /**
   * 设置字典备注
   *
   * @param code 字典备注
   */
  void setRemark(String code);
}
