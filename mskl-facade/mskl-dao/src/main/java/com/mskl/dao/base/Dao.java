package com.mskl.dao.base;

import com.mskl.common.page.Page;
import com.mskl.common.page.QueryResult;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author andy
 *
 * @param <E>
 * @param <PK>
 */
public interface Dao<E,PK extends Serializable>{

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public E getObjectById(PK id) throws DataAccessException;
	
	/**
	 * 根据某个非主键查询单条记录
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public E getUniqueByParam(String statementName, PK id) throws DataAccessException;
	
	
	/**
	 * 
	 * 查询总数
	 * @return
	 */
	public Long getCount(Object param);
	
	/**
	 * 查询总数
	 * @param statementName
	 * @param param
	 * @return
	 */
	public Long getCount(String statementName, Object param);
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteObjectById(PK id) throws DataAccessException;
	
	/**
	 * 根据对象删除
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteObjectByObj(E entity) throws DataAccessException;
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	public int saveObject(E entity) throws DataAccessException;
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	public int updateObject(E entity) throws DataAccessException;
	
	/**
	 * 根据sqlId更新数据
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	public int updateObject(String statementName, E entity) throws DataAccessException;



	/**
	 * 查询
	 * @param param
	 * @return
	 */
	public List<E> queryForList(Object param);
	
	/**
	 * 查询,指定statementName
	 * @param statementName
	 * @param param
	 * @return
	 */
	public List<E> queryForList(String statementName, Object param);
	
	/**
	 * 分页查询
	 * @param page
	 * @param param
	 * @return
	 */
	public QueryResult<E> queryForPage(Page<E> page, Map param);
	
	/**
	 * 分页查询
	 * 指定statementName
	 * @param statementName
	 * @param page
	 * @param param
	 * @return
	 */
	public QueryResult<E> queryForPage(String statementName, Page<E> page, Map param);
	
	/**
	 * 更新数据
	 * @return
	 * @throws DataAccessException
	 */
	int updateByStatementName(String statementName, Object param);
	
}
