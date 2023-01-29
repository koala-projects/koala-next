package cn.koala.system;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.EnableModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.LogicDeleteModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.SystemModel;

/**
 * 用户数据实体接口
 *
 * @author Houtaroy
 */
public interface User extends IdModel<Long>, SortModel, EnableModel, SystemModel, LogicDeleteModel, AuditModel<Long> {
}
