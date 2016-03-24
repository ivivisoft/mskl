package com.mskl.service.base.impl;

import com.mskl.dao.base.Dao;
import com.mskl.common.page.Page;
import com.mskl.common.page.QueryResult;
import com.mskl.service.base.BaseService;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<E, PK extends Serializable> implements BaseService<E, Serializable> {

    protected Dao<E, Serializable> baseDaoImpl;

    public Dao<E, Serializable> getBaseDaoImpl() {
        return baseDaoImpl;
    }

    public void setBaseDaoImpl(Dao<E, Serializable> baseDaoImpl) {
        this.baseDaoImpl = baseDaoImpl;
    }

    public E getObjectById(Serializable id) throws DataAccessException {
        return baseDaoImpl.getObjectById(id);
    }

    public E getUniqueByParam(String statementName, Serializable param) throws DataAccessException {
        return baseDaoImpl.getUniqueByParam(statementName, param);
    }

    public Long getCount(Object param) {
        return baseDaoImpl.getCount(param);
    }

    public Long getCount(String statementName, Object param) {
        return baseDaoImpl.getCount(statementName, param);
    }

    public int deleteObjectById(Serializable id) throws DataAccessException {
        return baseDaoImpl.deleteObjectById(id);
    }

    public int deleteObjectByObj(E entity) throws DataAccessException {
        return baseDaoImpl.deleteObjectByObj(entity);
    }

    public int saveObject(E entity) throws DataAccessException {
        return baseDaoImpl.saveObject(entity);
    }

    public int updateObject(E entity) throws DataAccessException {
        return baseDaoImpl.updateObject(entity);
    }

    public int updateObject(String statementName, E entity) throws DataAccessException {
        return baseDaoImpl.updateObject(statementName, entity);
    }

    ;

    public List<E> queryForList(Object param) {
        return baseDaoImpl.queryForList(param);
    }

    public List<E> queryForList(String statementName, Object param) {
        return baseDaoImpl.queryForList(statementName, param);
    }

    public QueryResult<E> queryForPage(Page page, Map param) {
        return baseDaoImpl.queryForPage(page, param);
    }

    public QueryResult<E> queryForPage(String statementName, Page page, Map param) {
        return baseDaoImpl.queryForPage(statementName, page, param);
    }

}
