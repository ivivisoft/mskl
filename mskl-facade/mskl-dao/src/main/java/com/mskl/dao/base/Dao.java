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
	 E getObjectById(PK id) throws DataAccessException;
	
	/**
	 * 根据某个非主键查询单条记录
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	 E getUniqueByParam(String statementName, PK id) throws DataAccessException;
	
	
	/**
	 * 
	 * 查询总数
	 * @return
	 */
	 Long getCount(Object param);
	
	/**
	 * 查询总数
	 * @param statementName
	 * @param param
	 * @return
	 */
	 Long getCount(String statementName, Object param);
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	 int deleteObjectById(PK id) throws DataAccessException;
	
	/**
	 * 根据对象删除
	 * @return
	 * @throws DataAccessException
	 */
	 int deleteObjectByObj(E entity) throws DataAccessException;
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	 int saveObject(E entity) throws DataAccessException;
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	 int updateObject(E entity) throws DataAccessException;
	
	/**
	 * 根据sqlId更新数据
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	 int updateObject(String statementName, E entity) throws DataAccessException;



	/**
	 * 查询
	 * @param param
	 * @return
	 */
	 List<E> queryForList(Object param);
	
	/**
	 * 查询,指定statementName
	 * @param statementName
	 * @param param
	 * @return
	 */
	 List<E> queryForList(String statementName, Object param);
	
	/**
	 * 分页查询
	 * @param page
	 * @param param
	 * @return
	 */
	 QueryResult<E> queryForPage(Page<E> page, Map param);
	
	/**
	 * 分页查询
	 * 指定statementName
	 * @param statementName
	 * @param page
	 * @param param
	 * @return
	 */
	 QueryResult<E> queryForPage(String statementName, Page<E> page, Map param);
	
	/**
	 * 更新数据
	 * @return
	 * @throws DataAccessException
	 */
	int updateByStatementName(String statementName, Object param);
	
}
