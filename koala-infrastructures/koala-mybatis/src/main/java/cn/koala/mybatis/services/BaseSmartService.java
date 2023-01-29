package cn.koala.mybatis.services;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SystemModel;
import cn.koala.mybatis.YesNo;
import cn.koala.mybatis.repositories.BaseRepository;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 基础智能服务抽象类
 * <p>
 * 在基础服务类上新增了部分功能: 代码生成ID/数据存在检查/系统数据不可修改/数据操作校验
 *
 * @author Houtaroy
 */
@Getter
public abstract class BaseSmartService<T extends IdModel<ID>, ID, USER_ID> extends BaseService<T, ID> {
  protected final Function<T, ID> idBuilder;
  protected final Supplier<USER_ID> currentUserIdSupplier;

  public BaseSmartService(BaseRepository<T, ID> repository) {
    this(repository, (T entity) -> null, () -> null);
  }

  public BaseSmartService(BaseRepository<T, ID> repository, Function<T, ID> idBuilder,
                          Supplier<USER_ID> currentUserIdSupplier) {
    super(repository);
    this.idBuilder = idBuilder;
    this.currentUserIdSupplier = currentUserIdSupplier;
  }

  @Override
  public T findById(ID id) {
    return repository.findById(id).orElseThrow(() -> new NoSuchElementException("数据不存在"));
  }

  @Override
  public <S extends T> void save(S entity) {
    Optional<T> persist = repository.findById(entity.getId());
    if (persist.isEmpty()) {
      entity.setIdIfAbsent(idBuilder.apply(entity));
      if (entity instanceof AuditModel<?>) {
        AuditModel<USER_ID> auditModel = (AuditModel<USER_ID>) entity;
        auditModel.setCreateUserId(currentUserIdSupplier.get());
        auditModel.setCreateTime(LocalDateTime.now());
      }
    } else {
      Assert.isTrue(isNotSystem(persist.get()), "系统数据不允许修改");
      if (entity instanceof AuditModel<?>) {
        AuditModel<USER_ID> auditModel = (AuditModel<USER_ID>) entity;
        auditModel.setLastUpdateUserId(currentUserIdSupplier.get());
        auditModel.setLastUpdateTime(LocalDateTime.now());
      }
    }
    Function<T, Integer> operation = persist.isEmpty() ? repository::add : repository::update;
    if (operation.apply(entity) != 1) {
      throw new IllegalStateException("数据处理异常");
    }
  }

  @Override
  public <S extends T> void delete(S entity) {
    Assert.isTrue(isNotSystem(findById(entity.getId())), "系统数据不允许修改");
    if (entity instanceof AuditModel<?>) {
      AuditModel<USER_ID> auditModel = (AuditModel<USER_ID>) entity;
      auditModel.setDeleteUserId(currentUserIdSupplier.get());
      auditModel.setDeleteTime(LocalDateTime.now());
    }
    if (repository.delete(entity) != 1) {
      throw new IllegalStateException("数据处理异常");
    }
  }

  /**
   * 是否非系统数据
   *
   * @param entity 数据实体
   * @return 是否非系统数据
   */
  protected <S extends T> boolean isNotSystem(S entity) {
    if (entity instanceof SystemModel model) {
      return model.getIsSystem() == YesNo.NO;
    }
    return true;
  }
}
