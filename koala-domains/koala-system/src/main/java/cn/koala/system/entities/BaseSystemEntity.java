package cn.koala.system.entities;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.EnableModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.LogicDeleteModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.SystemModel;
import cn.koala.mybatis.YesNo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 基础系统数据实体抽象类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseSystemEntity implements IdModel<Long>, SortModel, EnableModel, SystemModel, LogicDeleteModel,
  AuditModel<Long> {
  protected Long id;
  protected Long sortIndex;
  protected YesNo isEnable;
  protected YesNo isSystem;
  protected YesNo isDelete;
  protected UserEntity createUser;
  protected LocalDateTime createTime;
  protected UserEntity lastUpdateUser;
  protected LocalDateTime lastUpdateTime;
  protected UserEntity deleteUser;
  protected LocalDateTime deleteTime;

  @Override
  public Long getCreateUserId() {
    return getUserId(this::getCreateUser);
  }

  @Override
  public void setCreateUserId(Long id) {
    setUserId(this::getCreateUser, this::setCreateUser, id);
  }

  @Override
  public Long getLastUpdateUserId() {
    return getUserId(this::getLastUpdateUser);
  }

  @Override
  public void setLastUpdateUserId(Long id) {
    setUserId(this::getLastUpdateUser, this::setLastUpdateUser, id);
  }

  @Override
  public Long getDeleteUserId() {
    return getUserId(this::getDeleteUser);
  }

  @Override
  public void setDeleteUserId(Long id) {
    setUserId(this::getDeleteUser, this::setDeleteUser, id);
  }

  /**
   * 通用获取用户id方法, 处理用户实体与用户ID的属性交叉问题
   *
   * @param getter 获取用户数据实体方法
   * @return 用户id
   */
  protected Long getUserId(Supplier<UserEntity> getter) {
    return Optional.ofNullable(getter.get()).map(UserEntity::getId).orElse(null);
  }

  /**
   * 通用设置用户id方法, 处理用户实体与用户ID的属性交叉问题
   * <p>
   * 如果用户不存在, 则设置ID时自动创建一个新的
   *
   * @param getter 获取用户数据实体方法
   * @param setter 设置用户数据实体方法
   * @param id     用户id
   */
  protected void setUserId(Supplier<UserEntity> getter, Consumer<UserEntity> setter, Long id) {
    UserEntity user = getter.get();
    if (user != null) {
      user.setId(id);
    } else {
      setter.accept(UserEntity.builder().id(id).build());
    }
  }
}
