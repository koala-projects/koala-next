package cn.koala.mybatis.services;

import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SystemModel;
import cn.koala.mybatis.YesNo;
import cn.koala.mybatis.repositories.BaseRepository;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

/**
 * 基础智能服务抽象类
 * <p>
 * 在基础服务类上新增了部分功能: 代码生成ID/数据存在检查/系统数据不可修改/数据操作校验
 *
 * @author Houtaroy
 */
@Getter
public abstract class BaseSmartService<T extends IdModel<ID>, ID> extends BaseService<T, ID> {
  protected final Function<T, ID> idBuilder;

  public BaseSmartService(BaseRepository<T, ID> repository, Function<T, ID> idBuilder) {
    super(repository);
    this.idBuilder = idBuilder;
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
    } else {
      Assert.isTrue(isNotSystem(persist.get()), "系统数据不允许修改");
    }
    Function<T, Integer> operation = persist.isEmpty() ? repository::add : repository::update;
    if (operation.apply(entity) != 1) {
      throw new IllegalStateException("数据处理异常");
    }
  }

  @Override
  public <S extends T> void delete(S entity) {
    Assert.isTrue(isNotSystem(findById(entity.getId())), "系统数据不允许修改");
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
