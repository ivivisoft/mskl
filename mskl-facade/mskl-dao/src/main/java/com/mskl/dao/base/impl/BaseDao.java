package com.mskl.dao.base.impl;

import com.mskl.dao.base.Dao;
import com.mskl.common.constant.SqlMapStatementEnum;
import com.mskl.common.page.Page;
import com.mskl.common.page.QueryResult;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis 基础dao类 注意：子类一定要重写getIbatisSqlMapNamespace()
 * 
 * @author andy
 * @param <E>
 * @param <PK>
 */
public abstract class BaseDao<E, PK extends Serializable> extends SqlSessionDaoSupport implements Dao<E, PK> {
	

	public E getObjectById(PK primaryKey) throws DataAccessException {
		String str = getFindByPrimaryKeyStatement();
		E object = (E) super.getSqlSession().selectOne(getFindByPrimaryKeyStatement(), primaryKey);
		return object;
	}
	
	public E getUniqueByParam(String statementName,PK param) throws DataAccessException {
		E object = null;
		
		List<E> objs = super.getSqlSession().selectList(getFindByPrimaryKeyStatement(statementName), param);
		if(objs != null && objs.size() > 0){
			object = objs.get(0);
		}
		return object;
	}

	public int deleteObjectById(PK primaryKey) throws DataAccessException {
		return super.getSqlSession().delete(getDeleteStatement(), primaryKey);
	}

	public int deleteObjectByObj(E entity) throws DataAccessException {
		return super.getSqlSession().delete(getDeleteStatement(), entity);
	}

	public int saveObject(E entity) throws DataAccessException {
		prepareObjectBeforeSave(entity);
		int i = super.getSqlSession().insert(getInsertStatement(), entity);
		return i;
	}

	public int updateObject(E entity) throws DataAccessException {
		prepareObjectBeforeUpdate(entity);
		int i = super.getSqlSession().update(getUpdateStatement(), entity);
		return i;

	}
	
	public int updateObject(String statementName ,E entity) throws DataAccessException {
		prepareObjectBeforeUpdate(entity);
		int i = super.getSqlSession().update(getUpdateStatement(statementName), entity);
		return i;

	}


	public List<E> queryForList(Object param) {
		return this.queryForList(null, param);
	}

	public List<E> queryForList(String statementName, Object param) {
		if (statementName != null && !"".equals(statementName)) {
			return super.getSqlSession().selectList(statementName, param);
		} else {
			return super.getSqlSession().selectList(getListStatement(), param);
		}

	}
	
	/**
	 * 根据传入参数删除数据
	 * @param statementName
	 * @param param
	 * @return
	 */
	public int deleteByStatementName(String statementName, Object param) {
		if (statementName != null && !"".equals(statementName)) {
			return super.getSqlSession().delete(statementName, param);
		} else {
			return super.getSqlSession().delete(getDeleteStatement(), param);
		}

	}
	
	/**
	 * 根据传入参数更新数据
	 * @param statementName
	 * @param param
	 * @return
	 */
	public int updateByStatementName(String statementName, Object param) {
		if (statementName != null && !"".equals(statementName)) {
			return super.getSqlSession().update(statementName, param);
		} else {
			return super.getSqlSession().update(getUpdateStatement(), param);
		}

	}

	public QueryResult<E> queryForPage(Page<E> page, Map param) {
		return this.queryForPage(getListStatementForPage(), page, param);
	}

	public QueryResult<E> queryForPage(String statementName, Page<E> page, Map param) {
		QueryResult<E> queryResult = new QueryResult<E>();
		Map otherFilters = new HashMap();
		otherFilters.put("offset", page.getFirst());
		otherFilters.put("pageSize", page.getPageSize());
		otherFilters.put("lastRows", page.getFirst() + page.getPageSize());
		// 组装page跟param为同一个map,如果otherFilters取值为NULL的时候再在param中取值,param
		// Map parameterObject = new MapAndObject(otherFilters,(Serializable) param);
		otherFilters.putAll(param);
		List<E> resultList = super.getSqlSession().selectList(statementName, otherFilters);
		Long totalCount = new Long(0);
		if (null != otherFilters.get("totalCount")) {
			totalCount = (Long) otherFilters.get("totalCount");
		} else if (resultList.size() >= 0) {
			totalCount = new Long((long) resultList.size());
		}
		queryResult.setResultList(resultList);
		queryResult.setTotalCount(totalCount);

		return queryResult;
	}

	public Long getCount(Object param) {
		return this.getCount(null, param);
	}

	public Long getCount(String statementName, Object param) {
		if (statementName != null && !"".equals(statementName)) {
			return (Long) super.getSqlSession().selectOne(statementName, param);
		} else {
			return (Long) super.getSqlSession().selectOne(getCountStatement(), param);
		}
	}
	
	public Object selectOneObject(String statementName, Object param) {
		if (statementName != null && !"".equals(statementName)) {
			return super.getSqlSession().selectOne(statementName, param);
		} else {
			return super.getSqlSession().selectOne(selectOneObjectStatement(), param);
		}
	}


	private String getFindByPrimaryKeyStatement() {
		return getIbatisSqlMapNamespace() + SqlMapStatementEnum.GET_BY_ID.getStatementId();
	}
	
	private String getFindByPrimaryKeyStatement(String keyId) {
		return getIbatisSqlMapNamespace() + "."+keyId;
	}

	private String getDeleteStatement() {
		return getIbatisSqlMapNamespace() + SqlMapStatementEnum.DELETE_OBJECT.getStatementId();
	}

	private String getInsertStatement() {
		return getIbatisSqlMapNamespace() + SqlMapStatementEnum.INSERT_OBJECT.getStatementId();
	}

	private String getUpdateStatement() {
		return getIbatisSqlMapNamespace() + SqlMapStatementEnum.UPDATE_OBJECT.getStatementId();
	}
	
	private String getUpdateStatement(String statementName) {
		return getIbatisSqlMapNamespace() + "." + statementName;
	}

	private String getCountStatement() {
		return getIbatisSqlMapNamespace() + SqlMapStatementEnum.COUNT_OBJECT.getStatementId();
	}

	private String getListStatement() {
		return getIbatisSqlMapNamespace() + SqlMapStatementEnum.LIST_OBJECT.getStatementId();
	}

	private String getListStatementForPage() {
		return getIbatisSqlMapNamespace() + SqlMapStatementEnum.LIST_PAGE_OBJECT.getStatementId();
	}

	private String selectOneObjectStatement() {
		return getIbatisSqlMapNamespace() + SqlMapStatementEnum.SELECT_ONE_OBJECT.getStatementId();
	}
	
	/**
	 * 得到ibatis的Namespace 子类一定要重写此类
	 * 
	 * @return
	 */
	public String getIbatisSqlMapNamespace() {
		throw new RuntimeException("not yet implement");
	}

	/**
	 * 用于子类覆盖,在update之后调用
	 * 
	 * @param o
	 */

	protected void prepareObjectAfterUpdate(E o) {
	}
	/**
	 * 用于子类覆盖,在update之前调用
	 * 
	 * @param o
	 */
	protected void prepareObjectBeforeUpdate(E o) {
	}
	/**
	 * 用于子类覆盖,在insert之前调用
	 * 
	 * @param o
	 */
	protected void prepareObjectBeforeSave(E o) {
	}
	/**
	 * 用于子类覆盖,在insert之后调用
	 * 
	 * @param o
	 */
	protected void prepareObjectAfterSave(E o) {
	}
}
