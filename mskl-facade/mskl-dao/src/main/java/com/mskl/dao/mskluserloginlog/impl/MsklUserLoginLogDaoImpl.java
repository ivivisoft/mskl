package com.mskl.dao.mskluserloginlog.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklUserLoginLog;
import com.mskl.dao.mskluserloginlog.MsklUserLoginLogDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "mskluserloginlog.msklUserLoginLogDao")
public class MsklUserLoginLogDaoImpl extends MsklBaseDao<MsklUserLoginLog, Serializable> implements MsklUserLoginLogDao {
    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklUserLoginLogMapper";
    }
}
