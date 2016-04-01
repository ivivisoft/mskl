package com.mskl.dao.msklmedicine.impl;


import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklMedicine;
import com.mskl.dao.msklmedicine.MsklMedicineDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository(value = "msklmedicine.msklMedicineDao")
public class MsklMedicineDaoImpl extends MsklBaseDao<MsklMedicine, Serializable> implements MsklMedicineDao {
    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklMedicineMapper";
    }

    public MsklMedicine getMsklMedicineByBarCode(String barCode) {
        return (MsklMedicine) selectOneObject("getMsklMedicineByBarCode", barCode);
    }

    public List<MsklMedicine> getMsklMedicineByNormalName(String normalName) {
        return  queryForList("getMsklMedicineByNormalName",normalName);
    }
}
