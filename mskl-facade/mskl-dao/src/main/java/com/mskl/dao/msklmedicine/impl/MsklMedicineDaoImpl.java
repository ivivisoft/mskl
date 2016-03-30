package com.mskl.dao.msklmedicine.impl;


import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklMedicine;
import com.mskl.dao.msklmedicine.MsklMedicineDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository(value = "msklmedicine.msklMedicineDao")
public class MsklMedicineDaoImpl extends MsklBaseDao<MsklMedicine, Serializable> implements MsklMedicineDao {
    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklMedicineMapper";
    }

    public MsklMedicine getMsklMedicineByBarCode(String barCode) {
        return (MsklMedicine) selectOneObject("getMsklMedicineByBarCode", barCode);
    }

    public MsklMedicine getMsklMedicineByNormalName(String normalName) {
        return (MsklMedicine) selectOneObject("getMsklMedicineByNormalName",normalName);
    }
}
