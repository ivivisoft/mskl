package com.mskl.dao.medicinebox;

import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklMedbox;

import java.io.Serializable;
import java.util.Map;

public interface MedicineBoxDao extends Dao<MsklMedbox,Serializable>{
    MsklMedbox getBoxByMedicine(Long msklMedicineId);

    MsklMedbox getBoxByMedicineIdAndUserId(Map map);

    int updateBoxFinishDayByMedicineIdAndUserId(MsklMedbox msklMedbox);
}
