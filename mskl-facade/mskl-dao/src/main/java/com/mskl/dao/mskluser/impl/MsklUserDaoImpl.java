package com.mskl.dao.mskluser.impl;


import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklUser;
import com.mskl.dao.mskluser.MsklUserDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "mskluser.msklUserDao")
public class MsklUserDaoImpl extends MsklBaseDao<MsklUser, Serializable> implements MsklUserDao {
	@Override
	public String getIbatisSqlMapNamespace() {
		return ".MsklUserMapper";
	}
}
