package com.mskl.service.medicinebox;

import com.mskl.dao.model.MsklMedbox;
import com.mskl.service.base.BaseService;

import java.io.Serializable;

public interface MedicineBoxService extends BaseService<MsklMedbox,Serializable>{
    /**
     * 通过药品id获取医药箱
     * @param msklMedicineId
     * @return
     */
    MsklMedbox getBoxByMedicine(Long msklMedicineId);
}
