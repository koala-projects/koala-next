package cn.koala.mybatis.repositories;

/**
 * 基础仓库接口
 *
 * @author Houtaroy
 */
public interface BaseRepository<T, ID> extends CrudRepository<T, ID>, PagingRepository<T> {
}
