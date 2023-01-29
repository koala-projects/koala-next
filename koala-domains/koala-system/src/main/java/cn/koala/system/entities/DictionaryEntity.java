package cn.koala.system.entities;

import cn.koala.system.Dictionary;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DictionaryEntity extends BaseSystemEntity implements Dictionary {
  protected String code;
  protected String name;
  protected String remark;
}
