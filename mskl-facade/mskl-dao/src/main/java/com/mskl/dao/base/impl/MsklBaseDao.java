package com.mskl.dao.base.impl;

import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 
 * @author andy
 *
 * @param <E>
 * @param <PK>
 */
public abstract class MsklBaseDao<E , PK extends Serializable> extends BaseDao<E, Serializable> {
	@Resource(name="sqlSessionFactory")
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@PostConstruct  
    public void injectSqlMapClient() {   
        super.setSqlSessionFactory(sqlSessionFactory);
    }
	
}
