package com.noryar.security.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;

/**
 * 功能描述：查询及语句DAO操作实现.
 * @author 蒋文武
 */
public interface QueryDAOInterface {

    /**
     * 功能描述：通过sql获得个数.
     * @param namedQuery 命名查询id.
     * @param searchModel 参数条件类.
     * @return boolean 返回类型.
     * @throws Exception 异常.
     */
    boolean checkIsHave(String namedQuery, Object searchModel) throws Exception;

    /**
     * 功能描述：通过sql获得个数.
     * @param namedQuery 命名查询id.
     * @param searchModel 参数条件类.
     * @return int 返回类型.
     * @throws Exception 异常.
     */
    int getCountBySQLNamedQuery(String namedQuery, Object searchModel) throws Exception;

    /**
     * 功能描述：通过sql获得个数.
     * @param namedQuery 命名查询id.
     * @param searchModel 参数条件类.
     * @return int 返回类型.
     * @throws Exception 异常.
     */
    int getCountByHQLNamedQuery(String namedQuery, Object searchModel) throws Exception;

    /**
     * 功能描述：通过sql获得个数.
     * @param sql sql语句.
     * @param searchModel 参数条件类.
     * @return SQLQuery 返回类型.
     * @throws Exception 异常.
     */
    int getCountBySQL(String sql, Object searchModel) throws Exception;

    /**
     * 功能描述：通过hql获得个数.
     * @param hql hql语句.
     * @param searchModel 参数条件类.
     * @return SQLQuery 返回类型.
     * @throws Exception 异常.
     */
    int getCountByHQL(String hql, Object searchModel) throws Exception;

    /**
     * 功能描述：命名查询,返回结果集为list,list中的子项为map.
     * @param namedQuery 命名查询id.
     * @param searchModel 查询条件类.
     * @param <T> 返回类型泛型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryBySQLNamedQuery(String namedQuery, Object searchModel) throws Exception;

    /**
     * 功能描述：命名查询,返回结果集为list,list中的子项为customerDTO.
     * @param namedQuery 命名查询id.
     * @param searchModel 查询条件类.
     * @param <T> 返回类型泛型对象.
     * @param customerDTO 返回类型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryBySQLNamedQuery(String namedQuery, Object searchModel, Class<?> customerDTO) throws Exception;

    /**
     * 功能描述：直接通过sql查询,返回结果集为list,list中的子项为map.
     * @param sql 查询语句字符串.
     * @param searchModel 查询条件类.
     * @param <T> 返回类型泛型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryBySQL(String sql, Object searchModel) throws Exception;

    /**
     * 功能描述：直接通过sql查询,返回结果集为list,list中的子项为customerDTO.
     * @param sql 查询语句字符串.
     * @param searchModel 查询条件类.
     * @param <T> 返回类型泛型对象.
     * @param customerDTO 返回类型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryBySQL(String sql, Object searchModel, Class<?> customerDTO) throws Exception;

    /**
     * 功能描述：命名查询,返回结果集为list,list中的子项为hql语句中的查询对象.
     * @param namedQuery 命名查询id.
     * @param searchModel 查询条件类.
     * @param <T> 返回类型泛型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryByHQLNamedQuery(String namedQuery, Object searchModel) throws Exception;

    /**
     * 功能描述：直接通过hql查询,返回结果集为list,list中的子项为hql语句中的查询对象.
     * @param hql hql查询语句.
     * @param searchModel 查询条件类.
     * @param <T> 返回类型泛型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryByHQL(String hql, Object searchModel) throws Exception;

    /**
     * 功能描述：直接通过hql查询,返回结果集为list,list中的子项为hql语句中的查询对象.
     * @param hql hql查询语句.
     * @param <T> 返回类型泛型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryByHQL(String hql) throws Exception;

    /**
     * 功能描述：直接通过sql查询,返回结果集为list,list中的子项为customerDTO.
     * @param sql 查询语句字符串.
     * @param searchModel 查询条件类.
     * @param <T> 返回类型泛型对象.
     * @param customerDTO 返回类型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryBySQL(String sql, Class<?> customerDTO) throws Exception;

    /**
     * 功能描述：通过sql获得SQLQuery对象.
     * @param sql sql语句,可以是增删改查语句.
     * @param searchModel 参数条件类.
     * @return SQLQuery 返回类型.
     * @throws Exception 异常.
     */
    SQLQuery getSQLQuery(String sql, Object searchModel) throws Exception;

    /**
     * 功能描述：通过sql获得SQLQuery对象. SQLQuery query =
     * session.createSQLQuery("{CALL storedProcedureName(?,?)}").addEntity(xxx.class); query.setLong(0, 2);
     * query.setLong(1, 4); List<xxxEntity> list =query.list();
     * @param storedProcedureDef 存储过程定义-{CALL storedProcedureName(?,?)}.
     * @return SQLQuery 返回类型.
     * @throws Exception 异常.
     */
    SQLQuery getStoredProcedureSQLQuery(String storedProcedureDef) throws Exception;

    /**
     * 功能描述：通过sql获得SQLQuery对象. SQLQuery query =
     * session.createSQLQuery("{CALL storedProcedureName(?,?)}").addEntity(xxx.class); query.setLong(0, 2);
     * query.setLong(1, 4); List<xxxEntity> list =query.list();
     * @param storedProcedureDef 存储过程定义-{CALL storedProcedureName(?,?)}.
     * @param cls 返回结果集对象定义
     * @return SQLQuery 返回类型.
     * @throws Exception 异常.
     */
    SQLQuery getStoredProcedureSQLQuery(String storedProcedureDef, @SuppressWarnings("rawtypes") Class cls)
            throws Exception;

    /**
     * 功能描述：通过命名对象语句获得SQLQuery对象.
     * @param namedQuery 命名id.
     * @param searchModel 参数条件类.
     * @return SQLQuery 返回类型.
     * @throws Exception 异常.
     */
    SQLQuery getSQLQueryByNamed(String namedQuery, Object searchModel) throws Exception;

    /**
     * 功能描述：通过hql获得Query对象.
     * @param hql hql语句.
     * @param searchModel 参数条件类.
     * @return Query 返回类型.
     * @throws Exception 异常.
     */
    Query getQuery(String hql, Object searchModel) throws Exception;

    /**
     * 功能描述：通过命名对象语句获得Query对象.
     * @param namedQuery 命名id.
     * @param searchModel 参数条件类.
     * @return Query 返回类型.
     * @throws Exception 异常.
     */
    Query getQueryByNamed(String namedQuery, Object searchModel) throws Exception;

    /**
     * 功能描述：根据命名获得查询语句的count语句.
     * @param namedQuery 命名查询ID.
     * @param searchModel 查询条件.
     * @return String 返回的count查询语句.
     * @throws Exception 异常.
     */
    String getCountQueryStringByNamed(String namedQuery, Object searchModel) throws Exception;

    /**
     * 功能描述：通过命名对象语句获得Query对象.
     * @param namedQuery 命名id.
     * @return Query 返回类型.
     * @throws Exception 异常.
     */
    String getNamedQueryString(String namedQuery) throws Exception;

    /**
     * @param c 查询的类.
     * @return Criteria 对象.
     */
    Criteria createCriteria(Class<?> c);

    /**
     * @return AllClassMetadata 对象.
     * @throws Exception 异常.
     */
    Map<String, ClassMetadata> getAllClassMetadata() throws Exception;

    /**
     * @param countHql countHql.
     * @param qmap qmap.
     * @param qmapType qmapType.
     * @return int 对象.
     * @throws Exception 异常.
     */
    int getAdvancedCountByHQL(String countHql, Map<String, Object> qmap, Map<String, String> qmapType) throws Exception;
    /**
     * @param countSql countSql.
     * @param qmap qmap.
     * @param qmapType qmapType.
     * @return int 对象.
     * @throws Exception 异常.
     */
    int getAdvancedCountBySQL(String countHql, Map<String, Object> qmap, Map<String, String> qmapType) throws Exception;

    /**
     * @param qString qString.
     * @param qmap qmap.
     * @param qmapType qmapType.
     * @return Query 对象.
     * @throws Exception 异常.
     */
    SQLQuery getSQLQuery(String qString, Map<String, Object> qmap, Map<String, String> qmapType) throws Exception;

    /**
     * @param qString qString.
     * @param qmap qmap.
     * @param qmapType qmapType.
     * @return Query 对象.
     * @throws Exception 异常.
     */
    Query getQuery(String qString, Map<String, Object> qmap, Map<String, String> qmapType) throws Exception;

    /**
     * 功能描述：命名查询,返回结果集为list,list中的子项为map.
     * @param namedQuery 命名查询id.
     * @param searchModel 查询条件类.
     * @param customerDTO 查询条件.
     * @param <T> 返回类型泛型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryBySQLNamedQuery2(String namedQuery, Object searchModel, final Class<?> customerDTO)
            throws Exception;

    /**
     * 功能描述：命名查询,返回结果集为list,list中的子项为map.
     * @param namedQuery 命名查询id.
     * @param searchModel 查询条件类.
     * @param customerDTO 查询条件.
     * @param <T> 返回类型泛型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryBySQLNamedQuery3(String namedQuery, Object searchModel, Class<?> customerDTO) throws Exception;

    /**
     * 功能描述：命名查询,返回结果集为list,list中的子项为map.
     * @param namedQuery 命名查询id.
     * @param searchModel 查询条件类.
     * @param customerDTO 查询条件.
     * @param transformer 查询条件.
     * @param <T> 返回类型泛型对象.
     * @return List<T> 返回结果集.
     * @throws Exception 异常.
     */
    <T> List<T> queryBySQLNamedQuery(String namedQuery, Object searchModel, Class<?> customerDTO,
            ResultTransformer transformer) throws Exception;
}
