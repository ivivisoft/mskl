package com.mskl.dao.treatplan.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.dao.treatplan.TreatLogDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "treatLog.treatLogDao")
public class TreatLogDaoImpl extends MsklBaseDao<MsklTreatLog,Serializable> implements TreatLogDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklTreatLogMapper";
    }
}
