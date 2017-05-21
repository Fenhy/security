package com.noryar.security.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noryar.security.framework.model.PageInfoModel;
import com.noryar.security.util.AgileUtils;
import com.noryar.security.util.transformer.CustomerAliasToBeanResultTransformer;
import com.noryar.security.util.transformer.CustomerAllStringAliasToBeanResultTransformer;
import com.noryar.security.util.transformer.CustomerAllStringToBeanResultTransformer;
import com.noryar.security.util.transformer.MapResultTransformer;

/**
 * 功能描述：查询及语句DAO操作接口.
 * @author Leon.
 */
@Repository
public class QueryDAO implements QueryDAOInterface {
    /**
     * sessionFactory.
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return Session.
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean checkIsHave(final String namedQuery, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        int c = Integer.parseInt(query.list().get(0).toString());
        if (c > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getCountBySQLNamedQuery(final String namedQuery, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        return Integer.parseInt(query.list().get(0).toString());
    }

    @Override
    public int getCountByHQLNamedQuery(final String namedQuery, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        Query query = getSession().createQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        return Integer.parseInt(query.list().get(0).toString());
    }

    @Override
    public int getCountBySQL(final String sql, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(sql, searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        return Integer.parseInt(query.list().get(0).toString());
    }

    @Override
    public int getCountByHQL(final String sql, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(sql, searchModel);
        Query query = getSession().createQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        return Integer.parseInt(query.list().get(0).toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryBySQLNamedQuery(final String namedQuery, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        query.setResultTransformer(MapResultTransformer.INSTANCE);
        // query.setCacheable(true);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryByHQLNamedQuery(final String namedQuery, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        Query query = getSession().createQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        // query.setCacheable(true);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryBySQLNamedQuery(final String namedQuery, final Object searchModel,
            final Class<?> customerDTO) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        ResultTransformer transformer = new CustomerAliasToBeanResultTransformer(customerDTO);
        query.setResultTransformer(transformer);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryBySQLNamedQuery(final String namedQuery, final Object searchModel,
            final Class<?> customerDTO, final ResultTransformer transformer) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        query.setResultTransformer(transformer);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryBySQLNamedQuery2(final String namedQuery, final Object searchModel,
            final Class<?> customerDTO) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        ResultTransformer transformer = new CustomerAllStringAliasToBeanResultTransformer(customerDTO);
        query.setResultTransformer(transformer);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryBySQLNamedQuery3(final String namedQuery, final Object searchModel,
            final Class<?> customerDTO) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        ResultTransformer transformer = new CustomerAllStringToBeanResultTransformer(customerDTO);
        query.setResultTransformer(transformer);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryByHQL(final String hql, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(hql, searchModel);
        Query query = getSession().createQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryByHQL(final String hql) throws Exception {
        Query query = getSession().createQuery(hql);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryBySQL(final String sql, final Class<?> customerDTO) throws Exception {
        SQLQuery query = getSession().createSQLQuery(sql);
        ResultTransformer transformer = new CustomerAliasToBeanResultTransformer(customerDTO);
        query.setResultTransformer(transformer);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryBySQL(final String sql, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(sql, searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        query.setResultTransformer(MapResultTransformer.INSTANCE);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> queryBySQL(final String sql, final Object searchModel, final Class<?> customerDTO)
            throws Exception {
        String queryString = AgileUtils.stringEvaluate(sql, searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        ResultTransformer transformer = new CustomerAliasToBeanResultTransformer(customerDTO);
        query.setResultTransformer(transformer);
        return query.list();
    }

    @Override
    public Criteria createCriteria(final Class<?> c) {
        return getSession().createCriteria(c);
    }

    @Override
    public SQLQuery getSQLQuery(final String sql, final Object searchModel) throws Exception {
        String queryString = null;
        if (searchModel == null) {
            queryString = sql;
        } else {
            queryString = AgileUtils.stringEvaluate(sql, searchModel);
        }
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        return query;
    }

    @Override
    public SQLQuery getSQLQueryByNamed(final String namedQuery, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        return query;
    }

    @Override
    public Query getQuery(final String hql, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(hql, searchModel);
        Query query = getSession().createQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        return query;
    }

    @Override
    public int getAdvancedCountByHQL(final String countHql, final Map<String, Object> qmap,
            final Map<String, String> qmapType) throws Exception {
        String queryString = AgileUtils.stringEvaluate(countHql, qmap);
        Query query = getSession().createQuery(queryString);
        wraperQuery(query, qmap, qmapType, queryString);
        return Integer.parseInt(query.list().get(0).toString());
    }

    @Override
    public int getAdvancedCountBySQL(final String countHql, final Map<String, Object> qmap,
            final Map<String, String> qmapType) throws Exception {
        String queryString = AgileUtils.stringEvaluate(countHql, qmap);
        SQLQuery query = getSession().createSQLQuery(queryString);
        wraperQuery(query, qmap, qmapType, queryString);
        return Integer.parseInt(query.list().get(0).toString());
    }

    @Override
    public Query getQuery(final String qString, final Map<String, Object> qmap, final Map<String, String> qmapType)
            throws Exception {
        String queryString = AgileUtils.stringEvaluate(qString, qmap);
        Query query = getSession().createQuery(queryString);
        wraperQuery(query, qmap, qmapType, queryString);
        return query;
    }

    @Override
    public SQLQuery getSQLQuery(final String sql, final Map<String, Object> qmap, final Map<String, String> qmapType)
            throws Exception {
        String queryString = AgileUtils.stringEvaluate(sql, qmap);
        SQLQuery query = getSession().createSQLQuery(queryString);
        wraperSqlQuery(query, qmap, qmapType, queryString);
        return query;
    }

    @Override
    public Query getQueryByNamed(final String namedQuery, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        Query query = getSession().createQuery(queryString);
        if (searchModel != null && !"".equals(searchModel)) {
            query.setProperties(searchModel);
        }
        return query;
    }

    @Override
    public String getCountQueryStringByNamed(final String namedQuery, final Object searchModel) throws Exception {
        String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
                searchModel);
        return AgileUtils.getCountQueryString(queryString);
    }

    @Override
    public String getNamedQueryString(final String namedQuery) throws Exception {
        return getSession().getNamedQuery(namedQuery).getQueryString();
    }

    @Override
    public SQLQuery getStoredProcedureSQLQuery(final String storedProcedureDef) throws Exception {
        SQLQuery query = getSession().createSQLQuery(storedProcedureDef);
        return query;
    }

    @Override
    public SQLQuery getStoredProcedureSQLQuery(final String storedProcedureDef,
            @SuppressWarnings("rawtypes") final Class cls) throws Exception {
        SQLQuery query = getSession().createSQLQuery(storedProcedureDef).addEntity(cls);
        return query;
    }

    @Override
    public Map<String, ClassMetadata> getAllClassMetadata() throws Exception {
        Map<String, ClassMetadata> allClassMetadata = getSession().getSessionFactory().getAllClassMetadata();
        return allClassMetadata;
    }

    /**
     * 重新构造query.
     * @param query query
     * @param qmap qmap
     * @param qmapType qmapType
     * @param queryString queryString
     * @throws Exception e
     */
    private void wraperQuery(final Query query, final Map<String, Object> qmap, final Map<String, String> qmapType,
            final String queryString) throws Exception {
        if (qmap != null && !qmap.isEmpty()) {
            Iterator<Entry<String, Object>> iteratorQmap = qmap.entrySet().iterator();
            while (iteratorQmap.hasNext()) {
                Entry<String, Object> o = iteratorQmap.next();
                String property = o.getKey();
                if (queryString.contains(":" + property)) {
                    Object value = o.getValue();
                    String type = qmapType.get(property);
                    if ("String".equals(type)) {
                        query.setString(property, value.toString());
                    } else if ("Date".equals(type)) {
                        if (value == null || value.toString().replaceAll(" ", "").equals("")) {
                            query.setDate(property, null);
                        } else {
                            query.setDate(property, DateUtils.parseDate(value.toString()));
                        }
                    } else if ("BigDecimal".equals(type)) {
                        if (value == null || value.toString().replaceAll(" ", "").equals("")) {
                            value = "0";
                        }
                        BigDecimal bd = new BigDecimal(value.toString());
                        // query.setBigDecimal(property, BigDecimal.valueOf(Long.parseLong(value.toString())));
                        query.setBigDecimal(property, bd);
                    } else if ("Long".equals(type)) {
                        query.setLong(property, Long.parseLong(value.toString()));
                    } else if ("Integer".equals(type)) {
                        query.setInteger(property, Integer.parseInt(value.toString()));
                    }
                }
            }
        }
    }

    /**
     * 重新构造query.
     * @param query query
     * @param qmap qmap
     * @param qmapType qmapType
     * @param queryString queryString
     * @throws Exception e
     */
    private void wraperSqlQuery(final SQLQuery query, final Map<String, Object> qmap,
            final Map<String, String> qmapType, final String queryString) throws Exception {
        if (qmap != null && !qmap.isEmpty()) {
            Iterator<Entry<String, Object>> iteratorQmap = qmap.entrySet().iterator();
            while (iteratorQmap.hasNext()) {
                Entry<String, Object> o = iteratorQmap.next();
                String property = o.getKey();
                if (queryString.contains(":" + property)) {
                    Object value = o.getValue();
                    String type = qmapType.get(property);
                    if ("String".equals(type)) {
                        query.setString(property, value.toString());
                    } else if ("Date".equals(type)) {
                        query.setDate(property, DateUtils.parseDate(value.toString()));
                    } else if ("BigDecimal".equals(type)) {
                        if (value == null || value.toString().replaceAll(" ", "").equals("")) {
                            value = "0";
                        }
                        query.setBigDecimal(property, new BigDecimal(value.toString()));
                    } else if ("Long".equals(type)) {
                        query.setLong(property, Long.parseLong(value.toString()));
                    } else if ("Integer".equals(type)) {
                        query.setInteger(property, Integer.parseInt(value.toString()));
                    }
                }
            }
        }
    }

    /**
     * 功能描述：分页命名查询,返回结果集为pageInfoModel.
     * @param <T> 泛型.
     * @param countQuery
     * @param namedQuery
     * @param pageInfoModel
     * @param customerDTO
     * @throws Exception e.
     */
	@SuppressWarnings("unchecked")
	public PageInfoModel pageQueryBySQLNamedQuery(String countQuery, String namedQuery, PageInfoModel pageInfoModel, Class<?> customerDTO) throws Exception {
		// 查询总记录数，设置分页信息.
		int cnt = getCountBySQLNamedQuery(countQuery, pageInfoModel); // 总记录数
		// init pageSize
		Integer pageSize = pageInfoModel.getPageSize();
		if (pageSize == null) {
			pageSize = 10;
			pageInfoModel.setPageSize(pageSize);
		}
		// init totalPage
		int totalPage = cnt !=0 && cnt % pageSize == 0 ? cnt / pageSize : cnt / pageSize + 1;
		pageInfoModel.setTotalPage(totalPage);
		// init currentPage
		Integer currentPage = pageInfoModel.getCurrentPage();
		boolean reSet = false;
		if (currentPage == null || currentPage <= 0) {
			currentPage = 1;
			reSet = true;
		} else if (currentPage > totalPage){
			currentPage = totalPage;
			reSet = true;
		}
		if(reSet)
			pageInfoModel.setCurrentPage(currentPage);
		// 查询query封装.
		int start = (currentPage - 1) * pageSize;
		String queryString = AgileUtils.stringEvaluate(getSession().getNamedQuery(namedQuery).getQueryString(),
				pageInfoModel);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (pageInfoModel != null && !"".equals(pageInfoModel)) {
            query.setProperties(pageInfoModel);
        }
        ResultTransformer transformer = new CustomerAliasToBeanResultTransformer(customerDTO);
        query.setResultTransformer(transformer);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		// 结果集封装.
		pageInfoModel.setDataList(query.list());
		pageInfoModel.setTotalCnt(cnt);
		return pageInfoModel;
	}
}
