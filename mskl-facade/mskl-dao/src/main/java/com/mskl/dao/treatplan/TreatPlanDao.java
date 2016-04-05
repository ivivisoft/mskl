package com.mskl.dao.treatplan;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklTreatPlan;

import java.io.Serializable;
import java.util.List;

public interface TreatPlanDao extends Dao<MsklTreatPlan,Serializable>{
    List<MsklTreatPlan> getAllTreatPlanByUserId(Long userId);

    List<MsklTreatPlan> getAllTreatPlan();

    boolean checkMedicineInPlan(String msklMedicineId, Long userId);
}
