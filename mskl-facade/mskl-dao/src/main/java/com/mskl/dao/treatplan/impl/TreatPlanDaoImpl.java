package com.mskl.dao.treatplan.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklTreatPlan;
import com.mskl.dao.treatplan.TreatPlanDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "treatPlan.treatPlanDao")
public class TreatPlanDaoImpl extends MsklBaseDao<MsklTreatPlan,Serializable> implements TreatPlanDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklTreatPlanMapper";
    }








}