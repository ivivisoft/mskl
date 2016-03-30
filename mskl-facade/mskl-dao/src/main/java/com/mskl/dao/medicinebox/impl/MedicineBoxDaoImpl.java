package com.mskl.dao.medicinebox.impl;

import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.medicinebox.MedicineBoxDao;
import com.mskl.dao.model.MsklMedbox;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "medicineBox.medicineBoxDao")
public class MedicineBoxDaoImpl extends MsklBaseDao<MsklMedbox,Serializable> implements MedicineBoxDao{

    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklMedboxMapper";
    }


}
