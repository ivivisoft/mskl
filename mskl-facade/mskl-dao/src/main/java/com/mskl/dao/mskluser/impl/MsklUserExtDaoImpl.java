package com.mskl.dao.mskluser.impl;


import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklUserExt;
import com.mskl.dao.mskluser.MsklUserExtDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "mskluser.msklUserExtDao")
public class MsklUserExtDaoImpl extends MsklBaseDao<MsklUserExt, Serializable> implements MsklUserExtDao {
	@Override
	public String getIbatisSqlMapNamespace() {
		return ".MsklUserExtMapper";
	}
}
