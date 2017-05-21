package com.noryar.security.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：基础DAO操作接口.
 * @param <T> 泛型.
 * @param <PK> 主键泛型.
 * @author Leon.
 */
public interface BaseDAOInterface<T extends Serializable, PK extends Serializable> {

    /**
     * 功能描述：根据主键获得对象.
     * @param id 主键.
     * @return T.
     */
    T get(PK id);

    /**
     * 功能描述：根据主键从缓存中获取实体。如果没有相应的实体，抛出异常.
     * @param id 主键.
     * @return T.
     */
    T load(PK id);

    /**
     * 功能描述：更新实体.
     * @param entity 要更新的对象.
     */
    void update(T entity);

    /**
     * 功能描述：存储实体到数据库.
     * @param entity 要保存的对象.
     */
    void save(T entity);

    /**
     * 功能描述：存储实体集合到数据库.
     * @param entityList 要保存的对象集合.
     */
    void saveList(List<T> entityList);

    /**
     * 功能描述：更新实体集合到数据库.
     * @param entityList 要更新的对象集合.
     */
    void updateList(List<T> entityList);

    /**
     * 功能描述：增加或更新实体.
     * @param entity 要更新或保存的对象.
     */
    void saveOrUpdate(T entity);

    /**
     * 功能描述：删除指定的实体.
     * @param entity 要删除的对象.
     */
    void delete(T entity);

    /**
     * 功能描述：根据主键删除指定实体.
     * @param id 要删除的对象的主键.
     */
    void deleteByKey(PK id);

    /**
     * 功能描述：获得指定类的所有实体集合.
     * @return List<T>.
     */
    List<T> getAll();
}
