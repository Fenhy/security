package com.noryar.security.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 功能描述：基础DAO操作实现.
 * @param <T> 泛型.
 * @param <PK> 主键泛型.
 * @author Leon.
 */
@Repository
public class BaseDAO<T extends Serializable, PK extends Serializable> implements BaseDAOInterface<T, PK> {
	
    /**
     * 日志.
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * sessionFactory接口.
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 功能描述：获取hibernate会话(currentSession).
     * @return 会话.
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 实体类类型(由构造方法自动赋值).
     */
    private Class<T> entityClass = null;

    /**
     * 构造方法，根据实例类自动获取实体类类型.
     */
    @SuppressWarnings("unchecked")
    public BaseDAO() {
        Class<?> c = (Class<?>) getClass();
        Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.entityClass = (Class<T>) p[0];
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final T get(final PK id) {
        return (T) getSession().get(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final T load(final PK id) {
        return (T) getSession().load(entityClass, id);
    }

    @Override
    public final void update(final T entity) {
        getSession().update(entity);
    }

    @Override
    public final void updateList(final List<T> entityList) {
        for (T obj : entityList) {
            getSession().update(obj);
        }
    }

    @Override
    public final void save(final T entity) {
        getSession().save(entity);
    }

    @Override
    public final void saveList(final List<T> entityList) {
        for (T obj : entityList) {
            getSession().save(obj);
        }
    }

    @Override
    public final void saveOrUpdate(final T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public final void delete(final T entity) {
        getSession().delete(entity);
    }

    @Override
    public final void deleteByKey(final PK id) {
        getSession().delete(this.load(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public final List<T> getAll() {
        return getSession().createCriteria(entityClass).setCacheable(true).list();
    }
}
