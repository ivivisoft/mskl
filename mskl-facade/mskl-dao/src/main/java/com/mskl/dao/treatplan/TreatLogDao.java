package com.mskl.dao.treatplan;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklTreatLog;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface TreatLogDao extends Dao<MsklTreatLog,Serializable>{
    List<MsklTreatLog> getTreatLogByUserIdAndDate(Map param);

    List<MsklTreatLog> getTreatLogsByDate(Map paramToday);

    List<MsklTreatLog> getTreatLogsByDateAndPlanId(Map yestodayParam);
}
