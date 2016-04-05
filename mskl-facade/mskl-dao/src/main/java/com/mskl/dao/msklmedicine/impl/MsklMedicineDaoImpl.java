package com.mskl.dao.msklmedicine.impl;


import com.mskl.dao.base.impl.MsklBaseDao;
import com.mskl.dao.model.MsklMedicine;
import com.mskl.dao.msklmedicine.MsklMedicineDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "msklmedicine.msklMedicineDao")
public class MsklMedicineDaoImpl extends MsklBaseDao<MsklMedicine, Serializable> implements MsklMedicineDao {
    @Override
    public String getIbatisSqlMapNamespace() {
        return ".MsklMedicineMapper";
    }

    public MsklMedicine getMsklMedicineByBarCode(String barCode) {
        return (MsklMedicine) selectOneObject("getMsklMedicineByBarCode", barCode);
    }

    public List<MsklMedicine> getMsklMedicineByNormalName(String medicalName) {
        Map param = new HashMap();
        param.put("medicalName", medicalName);
        return queryForList("getMsklMedicineByNormalName", param);
    }

    public List<MsklMedicine> getAllMedicine() {

        return queryForList("getAllMedicine",null);
    }
}
