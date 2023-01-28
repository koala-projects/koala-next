package cn.koala.mybatis.services;

import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.repositories.BaseRepository;
import lombok.Getter;

import java.util.Optional;
import java.util.function.Function;

/**
 * 基础智能服务抽象类
 * <p>
 * 在基础服务类上新增了部分功能: 代码生成ID
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
  public <S extends T> void save(S entity) {
    Optional<T> persist = repository.findById(entity.getId());
    if (persist.isEmpty()) {
      entity.setIdIfAbsent(idBuilder.apply(entity));
    }
    Function<T, Integer> operation = persist.isEmpty() ? repository::add : repository::update;
    if (operation.apply(entity) != 1) {
      throw new IllegalStateException("数据处理异常");
    }
  }

  @Override
  public <S extends T> void delete(S entity) {
    findById(entity.getId());
    if (repository.delete(entity) != 1) {
      throw new IllegalStateException("数据处理异常");
    }
  }
}
