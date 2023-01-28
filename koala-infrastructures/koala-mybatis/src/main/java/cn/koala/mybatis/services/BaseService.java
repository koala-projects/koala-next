package cn.koala.mybatis.services;

import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.repositories.BaseRepository;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

/**
 * 基础服务抽象类
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@Getter
public abstract class BaseService<T extends IdModel<ID>, ID> implements CrudService<T, ID>, PagingService<T> {
  protected final BaseRepository<T, ID> repository;

  @Override
  public List<T> findAll(Map<String, Object> parameters) {
    return repository.findAll(parameters);
  }

  @Override
  public T findById(ID id) {
    return repository.findById(id).orElseThrow(() -> new NoSuchElementException("数据不存在"));
  }

  @Override
  public <S extends T> void save(S entity) {
    Optional<T> persist = repository.findById(entity.getId());
    Function<T, Integer> operation = persist.isEmpty() ? repository::add : repository::update;
    operation.apply(entity);
  }

  @Override
  public <S extends T> void delete(S entity) {
    repository.delete(entity);
  }

  @Override
  public Page<T> findAll(Map<String, Object> parameters, Pageable pageable) {
    com.github.pagehelper.Page<T> result = PageHelper
      .startPage(Math.max(pageable.getPageNumber() + 1, 1), pageable.getPageSize())
      .doSelectPage(() -> repository.findAll(parameters, pageable));
    return new PageImpl<>(result, pageable, result.getTotal());
  }
}
