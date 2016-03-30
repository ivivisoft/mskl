package com.mskl.dao.msklmedicine;


import com.mskl.dao.base.Dao;
import com.mskl.dao.model.MsklMedicine;

import java.io.Serializable;

public interface MsklMedicineDao extends Dao<MsklMedicine,Serializable> {
    /**
     * 根据药品二维码获取药品信息
     * @param barCode
     * @return
     */
    MsklMedicine getMsklMedicineByBarCode(String barCode);

    /**
     * 根据药品通用名获取药品信息
     * @param normalName
     * @return
     */
    MsklMedicine getMsklMedicineByNormalName(String normalName);
}
