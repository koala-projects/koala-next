package cn.koala.mybatis;

import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 基础服务抽象类, 封装了部分业务逻辑:
 * <p>
 * 1. 主键配置, 用代码来控制主键, 而不是用SQL语句
 * <p>
 * 2. 分页查询
 * <p>
 * 3. 审计
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public abstract class BaseService<T extends IdModel<ID>, ID> implements CrudService<T, ID>, PagingService<T, ID> {
  protected final CrudRepository<T, ID> repository;
  protected final Function<T, ID> idSupplier;
  protected final Supplier<ID> currentUserIdSupplier;

  @Override
  public <S extends T> void add(S entity) {
    entity.setIdIfAbsent(idSupplier.apply(entity));
    if (entity instanceof StateModel state) {
      state.setIsEnable(YesNo.YES);
      state.setIsSystem(YesNo.NO);
      state.setIsDelete(YesNo.NO);
    }
    if (entity instanceof AuditModel<?> audit) {
      ((AuditModel<ID>) audit).setCreateUserId(currentUserIdSupplier.get());
      audit.setCreateTime(LocalDateTime.now());
    }
    repository.insert(entity);
  }

  @Override
  public <S extends T> void delete(S entity) {
    T persist = load(entity.getId());
    Assert.isTrue(nonSystem(persist), "系统数据不允许删除");
    if (entity instanceof AuditModel<?> audit) {
      ((AuditModel<ID>) audit).setDeleteUserId(currentUserIdSupplier.get());
      audit.setDeleteTime(LocalDateTime.now());
    }
    repository.deleteById(entity.getId());
  }

  @Override
  public <S extends T> void update(S entity) {
    T persist = load(entity.getId());
    Assert.notNull(persist, "数据不存在");
    Assert.isTrue(nonSystem(persist), "系统数据不允许修改");
    if (entity instanceof StateModel state) {
      state.setIsSystem(YesNo.NO);
      state.setIsDelete(YesNo.NO);
    }
    if (entity instanceof AuditModel<?> audit) {
      ((AuditModel<ID>) audit).setLastUpdateUserId(currentUserIdSupplier.get());
      audit.setLastUpdateTime(LocalDateTime.now());
    }
    repository.updateById(entity);
  }

  @Override
  public T load(ID id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public List<T> list(Map<String, Object> parameters) {
    return repository.findAll(parameters);
  }

  @Override
  public Page<T> page(Map<String, Object> parameters, Pageable pageable) {
    com.github.pagehelper.Page<T> page = PageHelper
      .startPage(Math.max(pageable.getPageNumber() + 1, 1), pageable.getPageSize())
      .doSelectPage(() -> repository.findAll(parameters));
    return new PageImpl<>(page.getResult(), pageable, page.getTotal());
  }

  @Override
  public <S extends T> void save(S entity) {
    if (Objects.nonNull(entity.getId())) {
      update(entity);
    } else {
      add(entity);
    }
  }

  /**
   * 是否非系统数据
   *
   * @param entity 数据实体
   * @return 是否非系统数据
   */
  protected <S extends T> boolean nonSystem(S entity) {
    if (entity instanceof StateModel state) {
      return state.getIsSystem() == YesNo.NO;
    }
    return true;
  }
}