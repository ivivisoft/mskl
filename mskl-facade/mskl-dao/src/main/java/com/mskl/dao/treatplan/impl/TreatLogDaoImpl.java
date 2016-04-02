package com.mskl.dao.treatplan.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklTreatLog;
import com.mskl.dao.treatplan.TreatLogDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Repository(value = "treatLog.treatLogDao")
public class TreatLogDaoImpl extends MsklBaseDao<MsklTreatLog,Serializable> implements TreatLogDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklTreatLogMapper";
    }

    public List<MsklTreatLog> getTreatLogByUserIdAndDate(Map param) {
        return queryForList("getTreatLogByUserIdAndDate",param);
    }

    public List<MsklTreatLog> getTreatLogsByDate(Map paramToday) {
        return queryForList("getTreatLogsByDate",paramToday);
    }
}
