package com.mskl.dao.medicinebox.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.medicinebox.MedicineBoxDao;
import com.mskl.dao.model.MsklMedbox;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

@Repository(value = "medicineBox.medicineBoxDao")
public class MedicineBoxDaoImpl extends MsklBaseDao<MsklMedbox,Serializable> implements MedicineBoxDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklMedboxMapper";
    }


    public MsklMedbox getBoxByMedicine(Long msklMedicineId) {
        return (MsklMedbox) this.selectOneObject("getBoxByMedicine",msklMedicineId);
    }

    public MsklMedbox getBoxByMedicineIdAndUserId(Map map) {
        return (MsklMedbox) this.selectOneObject("getBoxByMedicineIdAndUserId",map);
    }

    public int updateBoxFinishDayByMedicineIdAndUserId(MsklMedbox msklMedbox) {
        return this.updateObject("updateBoxFinishDayByMedicineIdAndUserId",msklMedbox);
    }
}
